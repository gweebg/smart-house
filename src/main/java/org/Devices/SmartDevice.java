package org.Devices;

import org.jetbrains.annotations.NotNull;
import org.Exceptions.NegativeDeviceIdException;

import java.io.Serializable;

/**
 * Super class that represents the base of a smart device allowing the user
 * to set/change the device id, name or state (on | off).
 * @author Guilherme
 * @version 27/04/2022
 */
public abstract class SmartDevice implements Serializable {

    /* Class Variables */

    public enum State
    {
        ON,
        OFF
    }

    private int deviceId;
    private String deviceName;
    private State deviceState;
    private double baseCost;

    /* Constructors */

    public SmartDevice()
    {
        this.deviceId = 0;
        this.deviceName = "empty";
        this.deviceState = State.OFF;
        this.baseCost = 0;
    }

    public SmartDevice(int id, String name, State state, double baseCost) throws NegativeDeviceIdException
    {
        this.deviceState = state;
        this.deviceName = name;

        if (baseCost < 0) baseCost = Math.abs(baseCost);
        this.baseCost = baseCost;

        if (id < 0) throw new NegativeDeviceIdException("Device id must be a positive integer.");
        else this.deviceId = id;
    }

    public SmartDevice(@NotNull SmartDevice device)
    {
        this.deviceId = device.getDeviceId();
        this.deviceName = device.getDeviceName();
        this.deviceState = device.getDeviceState();
        this.baseCost = device.getBaseCost();
    }

    /* Getters/Setters */

    public State getDeviceState() {
        return deviceState;
    }
    public void setDeviceState(State newState) {
        this.deviceState = newState;
    }

    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int newId) {
        this.deviceId = newId;
    }

    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String newName) {
        this.deviceName = newName;
    }

    public double getBaseCost() {
        return baseCost;
    }
    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    /* Common Methods */

    @Override
    public String toString()
    {
        return "SmartDevice{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", deviceState=" + deviceState +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartDevice that = (SmartDevice) o;
        return deviceId == that.getDeviceId() &&
               this.deviceName.equals(that.getDeviceName()) &&
               deviceState == that.getDeviceState() &&
               baseCost == that.getBaseCost();
    }

    /* Abstract Methods */

    @Override
    public abstract SmartDevice clone();
    public abstract double getConsumptionPerDay();
}
