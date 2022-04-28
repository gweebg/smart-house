package org.house;

import org.devices.SmartDevice;
import org.exceptions.InvalidIdException;

public class Main {
    public static void main(String[] args) throws InvalidIdException {
        SmartDevice device = new SmartDevice(-1, "Penis", SmartDevice.State.ON);
    }
}