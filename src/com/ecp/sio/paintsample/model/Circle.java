package com.ecp.sio.paintsample.model;

import com.google.gson.JsonObject;

import java.awt.*;

/**
 * A model class for a circle
 */
public class Circle extends Shape {

    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public Circle(JsonObject conf) {
        super(conf);
        this.radius = conf.get("radius").getAsInt();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle {" +
                "x=" + getX() +
                ", y=" + getY() +
                ", radius=" + radius +
                '}';
    }

    @Override
    protected void doDraw(Graphics g) {
        int size = radius * 2;
        g.drawOval(
                getX(),
                getY(),
                size,
                size
        );
    }

}
