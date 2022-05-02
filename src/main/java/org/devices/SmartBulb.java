package org.devices;

import org.exceptions.NegativeDeviceIdException;
import org.jetbrains.annotations.NotNull;

public class SmartBulb extends SmartDevice {

    public enum Tone
    {
        NEUTRAL,
        COLD,
        WARM
    }

    private Tone color;
    private double size;

    public SmartBulb()
    {
        super();
        this.color = Tone.NEUTRAL;
        this.size = (double) 5.0;
    }

    @Override
    public SmartDevice clone() {
        return null;
    }

    public SmartBulb(int id, String name, State state, Tone tone, double size) throws NegativeDeviceIdException
    {
        super(id, name, state);
        this.color = tone;
        this.size = size;
    }

    public SmartBulb(@NotNull SmartBulb input) throws NegativeDeviceIdException
    {
        super(input);
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
