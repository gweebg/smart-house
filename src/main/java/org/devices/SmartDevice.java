package org.devices;

import org.jetbrains.annotations.NotNull;
import org.exceptions.InvalidIdException;

/**
 * Super class that represents the base of a smart device allowing the user
 * to set/change the device id, name or state (on | off).
 * @author Guilherme
 * @version 27/04/2022
 */
public class SmartDevice {

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

    public SmartDevice(int id, String name, State state) throws InvalidIdException {
        this.deviceState = state;
        this.deviceName = name;

        if (id < 0) throw new InvalidIdException("Device id must be a positive integer.");
        else this.deviceId = id;
    }

    public SmartDevice(@NotNull SmartDevice device) {
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
}
