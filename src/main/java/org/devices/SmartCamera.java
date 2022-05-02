package org.devices;

import org.exceptions.NegativeDeviceIdException;
import org.jetbrains.annotations.NotNull;

public class SmartCamera extends SmartDevice
{

    private Resolution resolution;
    private double fileSize;

    public SmartCamera()
    {
        super();
        this.resolution = new Resolution(600, 480);
        this.fileSize = 128;
    }

    public SmartCamera(int id, String name, State state, @NotNull Resolution resolution, int filesize) throws NegativeDeviceIdException
    {
        super(id, name, state);
        this.resolution = resolution.clone();
        this.fileSize = filesize;
    }

    public SmartCamera(@NotNull SmartCamera input) throws NegativeDeviceIdException
    {
        super(input);
        this.resolution = input.getResolution().clone();
        this.fileSize = input.getFileSize();
    }

    public Resolution getResolution() { return this.resolution; }

    public void setResolution(@NotNull Resolution resolution) { this.resolution = resolution.clone(); }

    public double getFileSize(){
        return this.fileSize;
    }

    public void setFileSize(double kbs){
        this.fileSize = kbs;
    }

    @Override
    public SmartDevice clone()
    {
        try {
            return new SmartCamera(this);
        } catch (NegativeDeviceIdException e) {
            throw new RuntimeException(e);
        }
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
