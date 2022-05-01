package org.devices;

import org.exceptions.NegativeDeviceIdException;
import org.jetbrains.annotations.NotNull;

public class SmartBulb extends SmartDevice {

    public enum Tone{
        Neutral,
        Cold,
        Warm
    }

    private Tone color;
    private double size;

    public SmartBulb()
    {
        super();
        this.color = Tone.Neutral;
        this.size = (double) 5.0;
    }

    public SmartBulb(int id, String name, State state, Tone tone, float num) throws NegativeDeviceIdException
    {
        super(id, name, state);
        this.color = tone;
        this.size = num;
    }

    public SmartBulb(@NotNull SmartBulb input) throws NegativeDeviceIdException
    {
        super(input.getDeviceId(), input.getDeviceName(), input.getDeviceState());
        this.color = input.getColor();
        this.size = input.getSize();
    }

    public void setColor(Tone newTone) {
        this.color = newTone;
    }

    public void setSize(double newSize){
        this.size = newSize;
    }

    public Tone getColor(){
        return this.color;
    }

    public double getSize(){
        return this.size;
    }

    /* Faltam métodos toString, equals, clone e o da hash. Todas as classes têm de ter isso
    * com exceção da SmartDevice que é pretty much classe abstrata. */

}
