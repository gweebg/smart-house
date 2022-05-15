package org.House;

import org.Devices.SmartBulb;
import org.Devices.SmartCamera;
import org.Devices.SmartDevice;
import org.Devices.SmartSpeaker;
import org.Exceptions.ExistingIdException;
import org.Exceptions.NegativeDeviceIdException;
import org.junit.jupiter.api.Test;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest
{

    @Test
    void getRoomConsumption() throws NegativeDeviceIdException, ExistingIdException, ScriptException
    {
        double baseCost = 27.3;
        double tax      = 12;
        String formula  = "x + y + z";
        int days        = 10;

        Room myRoom = new Room();
        myRoom.setName("Quarto");

        SmartDevice dev1 = new SmartBulb(0, "Bulb 1", SmartDevice.State.ON, 10, SmartBulb.Tone.NEUTRAL, 5);
        SmartDevice dev2 = new SmartSpeaker(1, "Speaker", SmartDevice.State.ON, 10, 85, "RFM", "JBL");
        SmartDevice dev3 = new SmartBulb(2, "Bulb 2", SmartDevice.State.ON, 12, SmartBulb.Tone.NEUTRAL, 7);

        myRoom.insertDevice(dev1);
        myRoom.insertDevice(dev2);
        myRoom.insertDevice(dev3);

        assertTrue(myRoom.deviceExists(0) && myRoom.deviceExists(1) && myRoom.deviceExists(2));

        double cost = myRoom.getRoomConsumption(baseCost, tax, formula, days);
        System.out.println(cost);

        //assertEquals(cost, );
    }
}