package org.house;

import org.devices.SmartDevice;
import org.exceptions.ExistingIdException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class Room {

    private String name;
    private HashMap<Integer, SmartDevice> devices;

    public Room(String name)
    {
        this.name = name;
        this.devices = new HashMap<>();
    }

    public Room()
    {
        this.name = "";
        this.devices = new HashMap<>();
    }

    public Room(@NotNull Room input)
    {
        this.name = input.getName();
        this.devices = input.getDevices();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public void setDevices(HashMap<Integer, SmartDevice> input) { this.devices = input; }

    public HashMap<Integer, SmartDevice> getDevices() { return devices; }

    public boolean deviceExists(SmartDevice input) { return (this.devices.get(input) != null); }

    public void insertDevice(SmartDevice input) throws ExistingIdException {
        if(this.devices.containsKey(input))
            throw new ExistingIdException("Input's ID already found in another device.");

        else
            this.devices.put(input.getDeviceId(), input);
    }




    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) && Objects.equals(devices, room.devices);
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
