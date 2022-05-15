package org.House;

import org.Devices.SmartDevice;
import org.Exceptions.NonexistentDeviceException;
import org.Exceptions.NonexistentRoomException;
import org.jetbrains.annotations.NotNull;
import org.Suppliers.EnergyProvider;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House
{
    /* Class variables */

    private String ownerName;
    private int ownerNIF;
    private EnergyProvider energyProvider;
    private List<Room> rooms;

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

        this.energyProvider = provider.clone();
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

    public EnergyProvider getEnergyProvider() { return (this.energyProvider.clone()); }
    public void setEnergyProvider(@NotNull EnergyProvider provider) { this.energyProvider = provider.clone(); }

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

    public double calculateBill(double baseCost, double tax, long days) throws ScriptException
    {
        double houseConsumption = 0;
        for (Room r : this.rooms)
            houseConsumption += r.getRoomConsumption(baseCost, tax, this.energyProvider.getFormula(), days);

        return houseConsumption;
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
        StringBuilder result = new StringBuilder();

        result.append("House of: ");
        result.append(this.ownerName);
        result.append("\nNIF: ");
        result.append(this.ownerNIF);
        result.append("\nEnergy Provider: ");
        result.append(this.energyProvider.getNameId());
        result.append("\nRooms:\n");

        for (Room r : this.rooms)
        {
            result.append(r.getName());
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public int hashCode() { return Objects.hash(ownerName, ownerNIF); }

    @Override
    public House clone() { return new House(this); }
}
