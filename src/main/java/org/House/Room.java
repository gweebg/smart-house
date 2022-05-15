package org.House;

import org.Devices.SmartDevice;
import org.Exceptions.ExistingIdException;
import org.Exceptions.NonexistentDeviceException;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room
{
    /* Class Variables */

    private String name;
    private HashMap<Integer, SmartDevice> devices;

    /* Constructors */

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

    /* Getters/Setters */

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

    /* Class Methods */

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

    public double getRoomConsumption(double baseCost, double tax, String formula, long days) throws ScriptException
    {
        /*
            3 * x + 2 * y - ( 0.9 * z )
            x: baseCost
            y: deviceConsumption
            z: tax
        */

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");

        double totalRoomCost = 0;
        for (SmartDevice device : this.devices.values())
        {
            if (device.getDeviceState() == SmartDevice.State.ON)
            {
                double deviceConsumption = device.getConsumptionPerDay();

                String goodFormula = formula.replaceAll("x", String.valueOf(baseCost))
                                            .replaceAll("y", String.valueOf(deviceConsumption))
                                            .replaceAll("z", String.valueOf(tax));

                // System.out.println(goodFormula);

                Object result = engine.eval(goodFormula);
                totalRoomCost += (double) result * days;
            }
        }

        return totalRoomCost;
    }

    /* Common Methods */

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
