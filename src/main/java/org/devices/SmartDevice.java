package org.devices;

import org.jetbrains.annotations.NotNull;
import org.exceptions.NegativeDeviceIdException;

import java.util.Objects;

/**
 * Super class that represents the base of a smart device allowing the user
 * to set/change the device id, name or state (on | off).
 * @author Guilherme
 * @version 27/04/2022
 */
public abstract class SmartDevice {

    /** Enum that represents the state of a device. */
    public enum State {
        ON,
        OFF
    }

    /** Device numeral identification. */
    private int deviceId;

    /** Device string identification. */
    private String deviceName;

    /** Device state, can be on (State.ON) or off (State.OFF). */
    private State deviceState;

    public SmartDevice() {
        this.deviceId = 0;
        this.deviceName = "empty";
        this.deviceState = State.OFF;
    }

    public SmartDevice(int id, String name, State state) throws NegativeDeviceIdException
    {
        this.deviceState = state;
        this.deviceName = name;

        if (id < 0) throw new NegativeDeviceIdException("Device id must be a positive integer.");
        else this.deviceId = id;
    }

    public SmartDevice(@NotNull SmartDevice device)
    {
        this.deviceId = device.getDeviceId();
        this.deviceName = device.getDeviceName();
        this.deviceState = device.getDeviceState();
    }

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
               deviceState == that.getDeviceState();
    }

    @Override
    public abstract SmartDevice clone();
}
