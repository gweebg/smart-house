package org.House;

import org.Devices.SmartDevice;
import org.Exceptions.NonexistentDeviceException;
import org.Exceptions.NonexistentRoomException;
import org.jetbrains.annotations.NotNull;
import org.Suppliers.EnergyProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House implements Serializable
{
    /* Class variables */

    private String ownerName;
    private int ownerNIF;
    private EnergyProvider energyProvider;
    private final List<Room> rooms;

    /* Class Constructors */

    public House()
    {
        this.ownerName = "Unknown";
        this.ownerNIF = 0;
        this.rooms = new ArrayList<>();
        this.energyProvider = new EnergyProvider();
    }

    public House(String owner, int nif, @NotNull List<Room> rooms, @NotNull EnergyProvider provider)
    {
        this.ownerName = owner;
        this.ownerNIF = nif;

        this.rooms = new ArrayList<>();
        for (Room r : rooms)
        {
            if (r != null) this.rooms.add(r.clone());
        }

        this.energyProvider = provider;
    }

    public House(@NotNull House other)
    {
        this.energyProvider = other.getEnergyProvider();

        this.rooms = new ArrayList<>();
        for (Room r : other.getRooms())
        {
            if (r != null) this.rooms.add(r.clone());
        }

        this.ownerName = other.getOwnerName();
        this.ownerNIF = other.getOwnerNIF();
    }

    /* Class Getters/Setters */

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public int getOwnerNIF() { return ownerNIF; }
    public void setOwnerNIF(int ownerNIF) { this.ownerNIF = ownerNIF; }

    public List<Room> getRooms()
    {
        List<Room> result = new ArrayList<>();
        for (Room r : this.rooms) result.add(r.clone());
        return result;
    }
    public void setRoom(@NotNull Room room) { this.rooms.add(room.clone()); }

    public EnergyProvider getEnergyProvider() { return (this.energyProvider); }
    public void setEnergyProvider(@NotNull EnergyProvider provider) { this.energyProvider = provider; }

    public long getTotalDevices() { return this.rooms.stream().mapToLong(Room::getTotalDevices).sum(); }

    public int getTotalRooms() { return this.rooms.size(); }

    public Room getRoom(String roomName) throws NonexistentRoomException
    {
        return this.rooms.stream().filter(room -> room.getName().equals(roomName)).findAny().orElseThrow(NonexistentRoomException::new);
    }

    /* Class Methods */

    public void setRoomOn(String roomName, SmartDevice.State state) throws NonexistentRoomException
    {
        Room currentRoom = this.rooms.stream().filter(room -> roomName.equals(room.getName())).findAny().orElse(null);
        if (currentRoom == null) throw new NonexistentRoomException("The room " + roomName + " does not exist in the house: " + this.ownerNIF);

        currentRoom.setAllDevicesState(state);
    }

    public void setDeviceStateOnRoom(int deviceId, SmartDevice.State state) throws NonexistentDeviceException
    {
        for (Room r : this.rooms)
            if (r.deviceExists(deviceId)) r.setDeviceState(state, deviceId);
    }

    public void setDeviceStateByRoomName(int deviceId, String roomName, SmartDevice.State state) throws NonexistentDeviceException
    {
        for (Room r : this.rooms)
        {
            if (r.getName().equals(roomName)) r.setDeviceState(state, deviceId);
        }
    }

    public double calculateBill(long days)
    {
        double houseConsumption = 0;
        for (Room r : this.rooms)
        {
            double con =  r.getRoomConsumption(this.energyProvider.getBaseCost(),
                                               this.energyProvider.getTaxMargin(),
                                               this.energyProvider.getFormula(), days);

            houseConsumption += con;
        }

        return houseConsumption;
    }

    public double calculatePowerUsed(long days)
    {
        return this.rooms.stream().mapToDouble(room -> room.getPowerConsumption(days)).sum();
    }

    /* Common Methods */

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House that = (House) o;
        return this.energyProvider.equals(that.getEnergyProvider()) &&
               this.ownerNIF == that.getOwnerNIF()                  &&
               this.ownerName.equals(that.getOwnerName())           &&
               this.rooms.equals(that.getRooms());
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("[House] ");

        result.append("Owner Name: ").append(this.ownerName);
        result.append(" NIF: ").append(this.ownerNIF);
        result.append(" Energy Provider: ").append(this.energyProvider).append("\n");

        return result.toString();
    }

    @Override
    public int hashCode() { return Objects.hash(ownerName, ownerNIF); }

    @Override
    public House clone() { return new House(this); }

}
