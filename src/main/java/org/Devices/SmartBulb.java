package org.Devices;

import org.Exceptions.NegativeDeviceIdException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SmartBulb extends SmartDevice
{
    /* Class Variables */
    public enum Tone
    {
        NEUTRAL,
        COLD,
        WARM
    }

    private Tone color;
    private double size;

    /* Constructors */

    public SmartBulb()
    {
        super();
        this.color = Tone.NEUTRAL;
        this.size = 5.0;
    }

    public SmartBulb(int id, String name, State state, double baseCost, Tone tone, float num) throws NegativeDeviceIdException
    {
        super(id, name, state, baseCost);
        this.color = tone;
        this.size = num;
    }

    public SmartBulb(@NotNull SmartBulb input)
    {
        super(input);
        this.color = input.getColor();
        this.size = input.getSize();
    }

    /* Getters/Setters */

    public void setColor(Tone newTone) { this.color = newTone; }

    public void setSize(double newSize) { this.size = newSize; }

    public Tone getColor() { return this.color; }

    public double getSize() { return this.size; }

    @Override
    public double getConsumptionPerDay()
    {
        if (this.color == Tone.NEUTRAL) return (getBaseCost() * 0.3 + size / 10);
        if (this.color == Tone.COLD   ) return (getBaseCost() * 0.5 + size / 10);
        else return (getBaseCost() * 0.7 + size / 10);
    }

    /* Common Methods */

    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;

        if (!super.equals(other)) return false;

        SmartBulb that = (SmartBulb) other;
        return this.color == that.color &&
                this.size == that.size;
    }

    @Override
    public String toString()
    {
        return "SmartBulb{" +
                "color=" + color +
                ", size=" + size +
                '}';
    }

    @Override
    public int hashCode() { return Objects.hash(color, size); }

    public SmartBulb clone() { return new SmartBulb(this); }
}
