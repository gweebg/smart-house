package org.devices;

import org.exceptions.NegativeDeviceIdException;

import java.util.Objects;

public class SmartSpeaker extends SmartDevice
{

    public static final int MAX = 100;

    private int speakerVolume;
    private String channel;
    private String speakerBrand;

    public SmartSpeaker()
    {
        super();
        this.speakerVolume = 50;
        this.channel = "";
        this.speakerBrand = "";
    }

    public SmartSpeaker(int deviceId, String deviceName, State state, int speakerVolume, String channel, String speakerBrand) throws NegativeDeviceIdException
    {

        super(deviceId, deviceName, state);
        this.channel = channel;
        this.speakerBrand = speakerBrand;
        this.setSpeakerVolume(speakerVolume);
    }

    public SmartSpeaker(SmartSpeaker other) throws NegativeDeviceIdException
    {
        super(other);
        this.speakerVolume = other.getSpeakerVolume();
        this.channel = other.getChannel();
        this.speakerBrand = other.getSpeakerBrand();
    }

    public int getSpeakerVolume() { return this.speakerVolume; }
    public void setSpeakerVolume(int speakerVolume)
    {
        if(speakerVolume < 0) this.speakerVolume = 0;
        else if(speakerVolume > MAX) this.speakerVolume = 100;
        else this.speakerVolume = speakerVolume;
    }

    public String getSpeakerBrand() { return this.speakerBrand; }
    public void setSpeakerBrand(String brand) { this.speakerBrand = brand;
    }
    public String getChannel() { return this.channel; }
    public void setChannel(String channel) { this.channel = channel; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return speakerVolume == that.speakerVolume && Objects.equals(channel, that.channel) && Objects.equals(speakerBrand, that.speakerBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speakerVolume, channel, speakerBrand);
    }

    @Override
    public String toString()
    {
        return "SmartSpeaker{" +
                "speakerVolume=" + speakerVolume +
                ", channel='" + channel + '\'' +
                ", speakerBrand='" + speakerBrand + '\'' +
                '}';
    }

    @Override
    public SmartDevice clone()
    {
        try {
            return new SmartSpeaker(this);
        } catch (NegativeDeviceIdException e) {
            throw new RuntimeException(e);
        }
    }

}
