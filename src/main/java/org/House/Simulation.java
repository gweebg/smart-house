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

    private final Map<String, EnergyProvider> energyProviders;
    private final Map<Integer, House> houses;
    LocalDate currentDate;

    /* Constructor */

    public Simulation(@NotNull List<EnergyProvider> providers, @NotNull List<House> houses)
    {
        this.energyProviders = new HashMap<>();
        for (EnergyProvider e : providers) this.energyProviders.put(e.getNameId(), e);

        this.houses = new HashMap<>();
        for (House h : houses) this.houses.put(h.getOwnerNIF(), h);

        this.currentDate = LocalDate.now();
    }

    /* Getters/Setters */

    public LocalDate getCurrentDate()
    {
        return this.currentDate;
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
            else house.setDeviceStateByRoomName(deviceId, roomName, setState);
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

    /* Lists */

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
            s.append(e.getNameId()).append(" - ")
             .append(e.getBaseCost()).append(" - ")
             .append(e.getTaxMargin()).append(" - ")
             .append(e.getFormula()).append("\n");

        return s.toString();
    }

    public String listBillsFromProvider(String providerName)
    {
        String result;

        if (this.energyProviders.containsKey(providerName)) result = this.energyProviders.get(providerName).getBillsAsString();
        else result = "Provider name does not existent: " + providerName + "\n";

        return result;
    }

    /* Availables */

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
                            .append(device.getClass()).append(" - ")
                            .append(device.getDeviceState()).append("\n");
        }

        availableDevices.append("[>] Device id (int, if 0 switch the room): ");
        return availableDevices.toString();
    }

    public String getAvailableProvidersAsString()
    {
        StringBuilder availableProviders = new StringBuilder("\n");

        for (EnergyProvider provider : this.energyProviders.values())
        {
            availableProviders.append("   [*] ")
                              .append(provider.getNameId())
                              .append("\n");
        }

        availableProviders.append("[>] Provider name (string): ");
        return availableProviders.toString();
    }

    /* Class Methods */

    public void changeBaseCostProvider(String providerName, Double baseCost) throws NonexistentProviderException
    {
        EnergyProvider provider = this.energyProviders.get(providerName);

        if (provider == null) throw new NonexistentProviderException("Provider named " + providerName + " does not exist.\n");
        else
        {
            provider.setBaseCost(baseCost);
        }
    }

    public void changeTaxProvider(String providerName, Double tax) throws NonexistentProviderException
    {
        EnergyProvider provider = this.energyProviders.get(providerName);

        if (provider == null) throw new NonexistentProviderException("Provider named " + providerName + " does not exist.\n");
        else
        {
            provider.setTaxMargin(tax);
        }
    }

    public void changeFormulaProvider(String providerName, String formula) throws NonexistentProviderException
    {
        EnergyProvider provider = this.energyProviders.get(providerName);

        if (provider == null) throw new NonexistentProviderException("Provider named " + providerName + " does not exist.\n");
        else
        {
            provider.setFormula(formula);
        }
    }

    public void setHouseProviderById(int houseId, String energyProvider) throws NonexistentProviderException, NonExistentHouseException
    {
        House currentHouse = this.houses.get(houseId);
        EnergyProvider provider = this.energyProviders.get(energyProvider);

        if (currentHouse == null) throw new NonExistentHouseException("House with NIF " + houseId + " does not exist.\n");
        if (provider == null) throw new NonexistentProviderException("Provider " + energyProvider + " does not exist.\n");

        currentHouse.setEnergyProvider(provider);
    }

    /* Queries */

    public House queryHouseSpentMore()
    {
        /*
          [?] Qual é a casa que mais gastou naquele perı́odo?
          1º Iterar por todas as bills e guardar os valores dos custos acumulados para cada casa.
             Map<Integer, Double> -> Integer: NIF da casa ; Double: Soma dos custos ao longo dos providers.
          2º Sacar o máximo do map.
        */

        Map<Integer, Double> houseCostMap = new HashMap<>();

        for (EnergyProvider provider : this.energyProviders.values())
        {
            for (Bill bill : provider.getBills())
            {
                if (!houseCostMap.containsKey(bill.getHouseOwner())) houseCostMap.put(bill.getHouseOwner(), bill.getTotalCost());
                else houseCostMap.put(bill.getHouseOwner(), houseCostMap.get(bill.getHouseOwner()) + bill.getTotalCost());
            }
        }

        int maxValueNIF = Collections.max(houseCostMap.entrySet(), (a, b) -> (int) (a.getValue() - b.getValue())).getKey();
        return this.houses.get(maxValueNIF);
    }

    public EnergyProvider getProviderMostBills()
    {
        /*
          [?] Qual o comercializador com maior volume de facturação ?
          1º Povoar um Map<String, Double> que contém o nome do provider e o total dos seus ganhos.
          2º Ver qual o máximo do map.
        */

        Map<String, Double> providersSumValuesMap = new HashMap<>();
        for (EnergyProvider provider : this.energyProviders.values())
        {
            double sumTotals = provider.getBills()
                                       .stream()
                                       .mapToDouble(Bill::getTotalCost)
                                       .sum();

            providersSumValuesMap.put(provider.getNameId(), sumTotals);
        }

        String providerName = Collections.max(providersSumValuesMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
        return this.energyProviders.get(providerName);
    }

    /* Dar uma ordenação dos maiores consumidores de energia durante um período a determinar. */
    public Map<Integer, Double> largestConsumerOnTimeInterval(@NotNull LocalDate start, @NotNull LocalDate finish) throws InvalidDateIntervalException
    {
        /*
          [?] Dar uma ordenação dos maiores consumidores de energia durante um perı́odo a determinar.
          1º Intervalo de datas tem de ser anterior à data atual.
         */

        if (start.isBefore(currentDate) && finish.isBefore(currentDate) && start.isBefore(finish) && finish.isAfter(start))
        {
            Map<Integer, Double> housesCosts = new HashMap<>();
            for (EnergyProvider provider : this.energyProviders.values())
            {
                for (Bill bill : provider.getBills())
                {
                    if (bill.isBetweenDates(start, finish))
                    {
                        if (!housesCosts.containsKey(bill.getHouseOwner())) housesCosts.put(bill.getHouseOwner(), bill.getTotalCost());
                        else housesCosts.put(bill.getHouseOwner(), housesCosts.get(bill.getHouseOwner()) + bill.getTotalCost());
                    }
                }
            }

            List<Map.Entry<Integer, Double>> tempList = new LinkedList<Map.Entry<Integer, Double>>(housesCosts.entrySet());
            Collections.sort(tempList, new Comparator<Map.Entry<Integer, Double>>()
            {
                @Override
                public int compare(Map.Entry<Integer, Double> a, Map.Entry<Integer, Double> b)
                {
                    return b.getValue().compareTo(a.getValue());
                }
            });

            Map<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
            for (Map.Entry<Integer, Double> entry : tempList)
            {
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            return sortedMap;
        }
        else
            throw new InvalidDateIntervalException("Date interval is not valid, either the start is before current date or finish is before start.\n");
    }

    public String displayQueryResultOne(@NotNull House resultHouse)
    {
        StringBuilder result = new StringBuilder("[!] Results for query 'House that spent the most until now.':\n");

        result.append("   House:\n").append("     * Owner: ").append(resultHouse.getOwnerName()).append("\n")
              .append("     * NIF: ").append(resultHouse.getOwnerNIF()).append("\n");

        return result.toString();
    }

    public String displayQueryResultTwo(@NotNull EnergyProvider provider)
    {
        StringBuilder result = new StringBuilder("[!] Results for query 'Provider that cashed more.':\n");

        result.append("   Energy Provider:\n").append("      * Name: ").append(provider.getNameId()).append("\n");

        return result.toString();
    }

    public String displayQueryResultThree(@NotNull Map<Integer, Double> resultMap)
    {
        StringBuilder result = new StringBuilder("[!] Results for query 'List of consumers ordered by most spent.':\n");

        int counter = 1;
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet())
        {
            result.append("   [").append(counter).append("º] ");
            result.append("House of: ")
                  .append(this.houses.get(entry.getKey()).getOwnerName())
                  .append(" - ").append(entry.getValue()).append("€\n");

            counter++;
        }

        return result.toString();
    }

}
