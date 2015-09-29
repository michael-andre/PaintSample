package com.ecp.sio.paintsample.sample;

/**
 * Created by Michaël on 28/09/2015.
 */
public class Complex {

    private double r;
    private double i;

    public double getR() {
        return r;
    }

    public double getI() {
        return i;
    }

    public double getMod() {
        return Math.sqrt(Math.pow(r, 2) + Math.pow(i, 2));
    }

    public double getArg() {
        return Math.atan(i / r);
    }


}
