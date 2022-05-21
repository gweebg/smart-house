package org.House;

import org.Devices.SmartDevice;
import org.Exceptions.*;
import org.Suppliers.Bill;
import org.jetbrains.annotations.NotNull;
import org.Suppliers.EnergyProvider;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Simulation implements Serializable
{
    /* Class Variables */

    private Map<String, EnergyProvider> energyProviders;
    private Map<Integer, House> houses;
    LocalDate currentDate;

    /* Constructor */

    public Simulation()
    {
        this.energyProviders = new HashMap<>();
        this.houses = new HashMap<>();
        this.currentDate = LocalDate.now()
;    }

    public Simulation(@NotNull List<EnergyProvider> providers, @NotNull List<House> houses)
    {
        this.energyProviders = new HashMap<>();
        for (EnergyProvider e : providers) this.energyProviders.put(e.getNameId(), e);

        this.houses = new HashMap<>();
        for (House h : houses) this.houses.put(h.getOwnerNIF(), h.clone());

        this.currentDate = LocalDate.now();
    }

    /* Getters/Setters */

    public LocalDate getCurrentDate()
    {
        return this.currentDate;
    }
    public void setCurrentDate(LocalDate formattedDate)
    {
        this.currentDate = formattedDate;
    }
    public void setCurrentDate(String date)
    {
        this.currentDate = LocalDate.parse(date);
    }

    public EnergyProvider getEnergyProviderByName(String name) throws NonexistentProviderException
    {
        EnergyProvider result = this.energyProviders.get(name);
        if (result == null) throw new NonexistentProviderException("Provider " + name + " does not exist.\n");
        else return result;
    }

    public void setDeviceToStateByHouse(int houseOwnerNIF, int deviceId, SmartDevice.State setState)
            throws NonExistentHouseException, NonexistentDeviceException
    {
        House house = this.houses.get(houseOwnerNIF);

        if (house == null) throw new NonExistentHouseException("House id (NIF) was not found or is not valid.\n");
        else house.setDeviceStateOnRoom(deviceId, setState);
    }

    public void setDeviceToStateByHouse(int houseOwnerNIF, String roomName, int deviceId, SmartDevice.State setState)
            throws NonExistentHouseException,
                   NonexistentDeviceException,
                   NonexistentRoomException
    {
        House house = this.houses.get(houseOwnerNIF);

        if (house == null) throw new NonExistentHouseException("House id (NIF) was not found or is not valid.\n");
        else
        {
            if (deviceId == 0) // Se isto acontece é porque queremos ligar mudar o quarto inteiro.
            {
                house.setRoomOn(roomName, setState);
            }
            house.setDeviceStateByRoomName(deviceId, roomName, setState);
        }
    }

    /* Class Methods */

    public void simulate(@NotNull LocalDate jumpTo) throws InvalidDateIntervalException
    {
        if (jumpTo.isBefore(currentDate)) throw new InvalidDateIntervalException("Given date is prior to current date.\n Make sure that the given that is after the current date.\n");

        long daysDifference = ChronoUnit.DAYS.between(this.currentDate, jumpTo);

        for (House house : houses.values())
        {
            Bill houseBill = new Bill();

            houseBill.setDeviceNum (house.getTotalDevices());
            houseBill.setHouseOwner(house.getOwnerNIF());
            houseBill.setFromDate  (this.currentDate);
            houseBill.setIssueDate (jumpTo);
            houseBill.setTotalCost (house.calculateBill(daysDifference));
            houseBill.setPowerUsed (house.calculatePowerUsed(daysDifference));

            this.energyProviders
                .get(house.getEnergyProvider().getNameId())
                .addBill(houseBill);
        }

        this.currentDate = jumpTo;
    }


    public String listAll()
    {
        StringBuilder s = new StringBuilder("Available Houses (Showing Rooms):\n");

        for (House h : this.houses.values())
        {
            s.append(h.getOwnerName()).append(" - ").append(h.getOwnerNIF()).append("\n");
            for (Room r : h.getRooms())
            {
                s.append("   * ").append(r.getName()).append("\n");
                for (SmartDevice d : r.getDevices().values())
                {
                    s.append("      + ").append(d.getDeviceId()).append(" ").append(d.getClass())
                            .append(" ").append(d.getDeviceState()).append("\n");
                }
            }
        }

        return s.toString();
    }

    public String listHouses()
    {
        StringBuilder s = new StringBuilder("Available Houses (id:total_rooms total_devices):\n");

        for (House h : this.houses.values())
        {
            s.append(h);
        }

        return s.toString();
    }

    public String listHousesRooms()
    {
        StringBuilder s = new StringBuilder("Available Houses (Showing Rooms):\n");

        for (House h : this.houses.values())
        {
            s.append(h.getOwnerName()).append(" - ").append(h.getOwnerNIF()).append("\n");
            for (Room r : h.getRooms())
            {
                s.append("   * ").append(r.getName()).append("\n");
            }
        }

        return s.toString();
    }

    public String listEnergyProviders()
    {
        StringBuilder s = new StringBuilder("Available Energy Providers:\n");

        for (EnergyProvider e : this.energyProviders.values())
            s.append(e.getNameId()).append("\n");

        return s.toString();
    }

    public String listBillsFromProvider(String providerName)
    {
        String result = null;

        if (this.energyProviders.containsKey(providerName)) result = this.energyProviders.get(providerName).getBillsAsString();
        else result = "Provider name does not existent: " + providerName + "\n";

        return result;
    }

    public String getAvailableHousesAsString()
    {
        StringBuilder availableHouses = new StringBuilder("[?] Please choose from one the houses bellow:\n");

        for (House house : this.houses.values())
        {
            availableHouses.append("   [*] ")
                           .append(house.getOwnerName()).append(" ")
                           .append(house.getOwnerNIF()).append("\n");
        }

        availableHouses.append("[>] House NIF (int): ");
        return availableHouses.toString();
    }

    public String getAvailableRoomsFromHouseAsString(int houseIdentification) throws NonExistentHouseException
    {
        House currentHouse = this.houses.get(houseIdentification);

        if (currentHouse == null) throw new NonExistentHouseException("House id (NIF) was not found or is not valid.\n");
        else
        {
            StringBuilder availableRooms = new StringBuilder("[?] Please enter a room name:\n");

            for (Room room : currentHouse.getRooms())
            {
                availableRooms.append("   [*] ")
                              .append(room.getName())
                              .append("\n");
            }

            availableRooms.append("[>] Room name (string): ");
            return availableRooms.toString();
        }
    }

    public String getAvailableDevicesFromRoom(int houseId, String roomName) throws NonexistentRoomException
    {
        Room currentRoom = this.houses.get(houseId).getRoom(roomName);

        StringBuilder availableDevices = new StringBuilder("[?] Please enter a device to switch:\n");

        for (SmartDevice device : currentRoom.getDevices().values())
        {
            availableDevices.append("   [*] ")
                            .append(device.getDeviceId()).append(" - ")
                            .append(device.getClass()).append("\n");
        }

        availableDevices.append("[>] Device id (int, if 0 switch the room): ");
        return availableDevices.toString();
    }

    /* Queries */

    public House getHouseSpentMore()
    {
        List<Bill> tempValues = new ArrayList<>();
        for (EnergyProvider provider : this.energyProviders.values())
        {
            Bill max = Collections.max(provider.getBills(), Comparator.comparingDouble(Bill::getTotalCost));
            tempValues.add(max);
        }

        Bill maxBillOverall = Collections.max(tempValues, Comparator.comparingDouble(Bill::getTotalCost));
        return this.houses.get(maxBillOverall.getHouseOwner());
    }

    public EnergyProvider getProviderMostBills()
    {
        Map<EnergyProvider, Double> toCompare = new HashMap<>();
        for (EnergyProvider provider : this.energyProviders.values())
        {
            double sumTotals = provider.getBills()
                                       .stream()
                                       .mapToDouble(Bill::getTotalCost)
                                       .sum();

            toCompare.put(provider, sumTotals);
        }

        return Collections.max(toCompare.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    /* Dar uma ordenação dos maiores consumidores de energia durante um período a determinar. */
    public Set<Bill> largestConsumerOnTimeInterval(@NotNull LocalDate start, @NotNull LocalDate finish) throws InvalidDateIntervalException
    {
        if (start.isAfter(finish) || finish.isBefore(start)) throw new InvalidDateIntervalException("Date interval is not valid, either the start is before current date or finish is before start.\n");

        Set<Bill> totalBills = new TreeSet<>();
        for (EnergyProvider provider : this.energyProviders.values())
        {
            provider.getBills()
                    .stream()
                    .filter(bill -> bill.isBetweenDates(start, finish))
                    .forEach(b -> totalBills.add(b.clone()));
        }

        return totalBills;
    }

}
