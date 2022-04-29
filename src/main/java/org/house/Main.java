package org.house;

import org.devices.SmartDevice;
import org.exceptions.NegativeDeviceIdException;

public class Main {
    public static void main(String[] args) throws NegativeDeviceIdException {
        SmartDevice device = new SmartDevice(1, "Penis", SmartDevice.State.ON);
    }
}