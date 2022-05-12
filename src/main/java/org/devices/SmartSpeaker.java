package org.devices;

import org.exceptions.NegativeDeviceIdException;

import java.util.Objects;

public class SmartSpeaker extends SmartDevice
{

    /* Class Variables */
    /* SmartSpeaker:42,Cidade FM,Goodis,5.16 */

    public static final int MAX = 100;
    private int speakerVolume;
    private String channel;
    private String speakerBrand;

    /* Constructors */

    public SmartSpeaker()
    {
        super();
        this.speakerVolume = 50;
        this.channel = "";
        this.speakerBrand = "";
    }

    public SmartSpeaker(int deviceId, String deviceName, State state, double baseCost, int speakerVolume, String channel, String speakerBrand) throws NegativeDeviceIdException
    {

        super(deviceId, deviceName, state, baseCost);
        this.channel = channel;
        this.speakerBrand = speakerBrand;
        this.setSpeakerVolume(speakerVolume);
    }

    public SmartSpeaker(SmartSpeaker other)
    {
        super(other);
        this.speakerVolume = other.getSpeakerVolume();
        this.channel = other.getChannel();
        this.speakerBrand = other.getSpeakerBrand();
    }

    /* Getters/Setters */

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

    public double getConsumptionPerDay()
    {
        /*TODO Introduzir fórmula fixe aqui que relacione o volume e o baseCost. */
        return this.getBaseCost();
    }

    /* Common Methods */

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;

        SmartSpeaker that = (SmartSpeaker) o;
        return speakerVolume == that.speakerVolume &&
               this.channel.equals(that.channel)   &&
               this.speakerBrand.equals(that.speakerBrand);
    }

    @Override
    public int hashCode() { return Objects.hash(speakerVolume, channel, speakerBrand); }

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
    public SmartDevice clone() { return new SmartSpeaker(this); }
}
