package org.devices;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Resolution {

    private int height;
    private int width;

    public Resolution()
    {
        this.height = 600;
        this.width  = 480;
    }

    public Resolution(int height, int width)
    {
        this.height = height;
        this.width  = width;
    }

    public Resolution(@NotNull Resolution res)
    {
        this.height = res.getHeight();
        this.width  = res.getWidth();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Resolution{" +
                "height=" + this.height +
                ", width=" + this.width +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resolution that = (Resolution) o;
        return height == that.getHeight() && width == that.getWidth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override
    public Resolution clone()
    {
        Resolution clone = (Resolution) super.clone();

        clone.setHeight(this.height);
        clone.setWidth(this.width);

        return clone;
    }
}
