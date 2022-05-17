package org.Devices;

import org.Exceptions.NegativeDeviceIdException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class SmartCamera extends SmartDevice implements Serializable
{
    /* Class Variables */

    private Resolution resolution;
    private int fileSize;

    /* Constructor Methods */

    public SmartCamera()
    {
        super();
        this.resolution = new Resolution(600, 480);
        this.fileSize = 128;
    }

    public SmartCamera(int id, String name, State state, double baseCost, @NotNull Resolution resolution, int filesize) throws NegativeDeviceIdException
    {
        super(id, name, state, baseCost);
        this.resolution = resolution.clone();
        this.fileSize = filesize;
    }

    public SmartCamera(@NotNull SmartCamera input)
    {
        super(input);
        this.resolution = input.getResolution().clone();
        this.fileSize = input.getFileSize();
    }

    /* Getters/Setters */

    public Resolution getResolution() { return this.resolution.clone(); }
    public void setResolution(@NotNull Resolution resolution) { this.resolution = resolution.clone(); }

    public int getFileSize() { return this.fileSize; }
    public void setFileSize(int kbs) { this.fileSize = kbs; }

    public double getConsumptionPerDay()
    {
        return (double) ((resolution.getWidth() / resolution.getHeight()) + (fileSize + getBaseCost() / 100));
    }

    /* Common Methods */

    @Override
    public SmartCamera clone()
    {
        return new SmartCamera(this);
    }

    @Override
    public String toString()
    {
        return "SmartCamera{" +
                "resolution=" + resolution +
                ", fileSize=" + fileSize +
                '}';
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;

        if (!super.equals(other)) return false;

        SmartCamera that = (SmartCamera) other;
        return this.resolution.equals(that.getResolution()) &&
               this.fileSize == that.getFileSize();
    }

}
