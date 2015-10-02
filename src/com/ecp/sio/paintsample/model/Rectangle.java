package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * Created by Micha�l on 21/09/2015.
 */
public class Rectangle extends Shape {

    private int width, height;

    /*public Rectangle() {
    }*/

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public Rectangle(JsonObject conf) {
        super(conf);
        this.width = conf.get("width").getAsInt();
        this.height = conf.get("height").getAsInt();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Rectangle {" +
                "x=" + getX() +
                ", y=" + getY() +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Rectangle) {
            Rectangle rect = ((Rectangle) obj);
            return rect.width == width &&
                rect.height == height;
        }
        return false;
    }

    @Override
    protected void doDraw(Graphics g) {
        g.drawRect(
                getX(),
                getY(),
                width,
                height
        );
    }
}
