package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * Created by Micha�l on 28/09/2015.
 */
public class Circle extends Shape {

    private int mRadius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.mRadius = radius;
    }

    public Circle(JsonObject conf) {
        super(conf);
        this.mRadius = conf.get("radius").getAsInt();
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
    }

    @Override
    public double getArea() {
        return Math.pow(mRadius, 2) * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle {" +
                "x=" + getX() +
                ", y=" + getY() +
                ", radius=" + mRadius +
                '}';
    }

    @Override
    protected void doDraw(Graphics g) {
        int size = mRadius * 2;
        g.drawOval(
                getX(),
                getY(),
                size,
                size
        );
    }

}
