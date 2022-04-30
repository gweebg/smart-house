package org.devices;

import org.exceptions.NegativeDeviceIdException;

public class SmartBulb extends SmartDevice {

    public enum Tone{
        Neutral,
        Cold,
        Warm
    }

    private Tone color;
    private float size;

    public SmartBulb(){
        super();
        this.color = Tone.Neutral;
        this.size = (float)5.0;
    }

    public SmartBulb(int id, String name, State state, Tone tone, float num) throws NegativeDeviceIdException {
        super(id, name, state);
        this.color = tone;
        this.size = num;
    }

    public SmartBulb(SmartBulb input) throws NegativeDeviceIdException{
        super(input.getDeviceId(), input.getDeviceName(), input.getDeviceState());
        this.color = input.getColor();
        this.size = input.getSize();
    }

    public void setColor(Tone input) {
        this.color = input;
    }

    public void setSize (float input){
        this.size = input;
    }

    public Tone getColor(){
        return this.color;
    }

    public float getSize(){
        return this.size;
    }

    public void printBulb(){
        System.out.println("Smart Bulb " + this.getDeviceName() + " ID : " + this.getDeviceId());
        System.out.println("\nSize : " + this.getSize() + "   Colour : " + this.getColor());
    }


}
