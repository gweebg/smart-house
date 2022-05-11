package org.house;

import org.devices.SmartDevice;
import org.exceptions.ExistingIdException;
import org.exceptions.NonexistentDeviceException;
import org.exceptions.NonexistentRoomException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Iterator;

public class Room
{
    private String name;
    private HashMap<Integer, SmartDevice> devices;

    public Room()
    {
        this.name = "";
        this.devices = new HashMap<>();
    }

    public Room(String name)
    {
        this.name = name;
        this.devices = new HashMap<>();
    }

    public Room(@NotNull Room room)
    {
        this.name = room.getName();
        this.devices = room.getDevices();
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public void setDevices(@NotNull HashMap<Integer, SmartDevice> input)
    {
        for (Map.Entry<Integer, SmartDevice> entry : input.entrySet())
        {
            if (entry.getValue() != null) this.devices.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public HashMap<Integer, SmartDevice> getDevices()
    {
        HashMap<Integer, SmartDevice> devices = new HashMap<>();
        for (Map.Entry<Integer, SmartDevice> entry : this.devices.entrySet())
        {
            if (entry.getValue() != null) devices.put(entry.getKey(), entry.getValue().clone());
        }
        return devices;
    }

    public boolean deviceExists(Integer input) { return (this.devices.get(input) != null); }

    public boolean deviceExists(SmartDevice input)
    {
        for (SmartDevice device : this.devices.values())
            if (device.equals(input)) return true;

        return false;
    }

    public void insertDevice(@NotNull SmartDevice device) throws ExistingIdException
    {
        if(this.devices.containsKey(device.getDeviceId()))
            throw new ExistingIdException("Device id" + "(" + device.getDeviceId()+ ")" + "already existing in room" + this.name);
        else
            this.devices.put(device.getDeviceId(), device);
    }

    public void setAllDevicesState(SmartDevice.State state)
    {
        this.devices.forEach((id, device) -> device.setDeviceState(state));
    }

    public void setDeviceState(SmartDevice.State state, int deviceId) throws NonexistentDeviceException
    {
        SmartDevice device = this.devices.get(deviceId);
        if (device == null) throw new NonexistentDeviceException("Device " + "(" + deviceId + ")" + "does not exist.");

        device.setDeviceState(state);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;
        return this.name.equals(room.getName()) &&
               this.devices.equals(room.devices);
    }

    @Override
    public int hashCode() { return Objects.hash(name, devices); }

    @Override
    public String toString()
    {
        return "Room{" +
                "name='" + name + '\'' +
                ", devices=" + devices +
                '}';
    }

    public Room clone() { return new Room(this); }
}
