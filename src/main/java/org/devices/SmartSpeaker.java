package org.devices;

import org.exceptions.NegativeDeviceIdException;

public class SmartSpeaker extends SmartDevice {

    public static final int MAX = 100;

    private int speakerVolume;
    private String channel;
    private String speakerBrand;

    public SmartSpeaker() {
        super();
        this.speakerVolume = 50;
        this.channel = "";
        this.speakerBrand = "";
    }

    public SmartSpeaker(int deviceId, String deviceName, State state, int speakerVolume, String channel, String speakerBrand)
            throws NegativeDeviceIdException {

        super(deviceId, deviceName, state);

    }

}
