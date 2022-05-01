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
        this.channel = channel;
        this.speakerBrand = speakerBrand;
        this.setSpeakerVolume(speakerVolume);   // Set já contêm limitadores implementados

    }

    public void setSpeakerVolume(int speakerVolume){
        if(speakerVolume < 0)
            this.speakerVolume = 0;
        else if(speakerVolume > MAX)
            this.speakerVolume = 100;
        else
            this.speakerVolume = speakerVolume;
    }

    public void setSpeakerBrand(String brand){
        this.speakerBrand = brand;
    }

    public void setChannel(String channel){
        this.channel = channel;
    }

    public int getSpeakerVolume(){
        return this.speakerVolume;
    }

    public String getChannel(){
        return this.channel;
    }

    public String getSpeakerBrand(){
        return this.speakerBrand;
    }
}
