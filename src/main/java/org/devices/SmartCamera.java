package org.devices;

import org.exceptions.NegativeDeviceIdException;

public class SmartCamera extends SmartDevice{

    private int resX;
    private int resY;           //resolucao standard é (ResX, ResY)
    private double fileSize;    //KB, I guess, idk

    public SmartCamera(){
        super();
        this.resX = 600;
        this.resY = 480;
        this.fileSize = 120; //KB
    }

    public SmartCamera(int id, String name, State state, int resx, int resy, int filesize) throws NegativeDeviceIdException {
        super(id, name, state);
        this.resX = resx;
        this.resY = resy;
        this.fileSize = filesize;
    }

    public SmartCamera(SmartCamera input)throws NegativeDeviceIdException{
        super(input.getDeviceId(), input.getDeviceName(), input.getDeviceState());
        this.resX = input.getResX();
        this.resY = input.getResY();
        this.fileSize = input.getFileSize();
    }

    public int getResX(){
        return this.resX;
    }

    public int getResY(){
        return this.resY;
    }

    public double getFileSize(){
        return this.fileSize;
    }

    public void setResX(int res){
        this.resX = res;
    }

    public void setResY(int res){
        this.resY = res;
    }

    public void setFileSize(double kbs){
        this.fileSize = kbs;
    }
}
