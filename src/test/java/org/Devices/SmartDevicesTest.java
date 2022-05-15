package org.Devices;

import org.Exceptions.NegativeDeviceIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDevicesTest
{
    @Test
    public void BulbBuilderTest() throws NegativeDeviceIdException
    {
        SmartBulb bulbA = new SmartBulb();

        assertSame(bulbA.getColor(), SmartBulb.Tone.NEUTRAL);
        // assertTrue(bulbA.getSize() == 5.0d);

        SmartBulb bulbB = new SmartBulb(0, "Phillips Hue", SmartDevice.State.OFF, 5.34,
                                        SmartBulb.Tone.NEUTRAL, 5.0F);

        assertSame(bulbB.getDeviceId(), 0);
        assertSame(bulbB.getDeviceName(), "Phillips Hue");
        assertSame(bulbB.getDeviceState(), SmartDevice.State.OFF);
        // assertSame(bulbB.getSize(), 5.0d);
        assertSame(bulbB.getColor(), SmartBulb.Tone.NEUTRAL);


        SmartBulb bulbC = new SmartBulb(bulbB);

        assertSame(bulbC.getDeviceId(), bulbB.getDeviceId());
        assertSame(bulbC.getDeviceName(), bulbB.getDeviceName());
        assertSame(bulbC.getDeviceState(), bulbB.getDeviceState());
        //assertSame(bulbC.getSize(), bulbB.getSize());
        assertSame(bulbC.getColor(), bulbB.getColor());
    }
}