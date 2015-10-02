package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * Created by Michaël on 28/09/2015.
 */
public abstract class Shape implements Drawable {

    private int mX, mY;
    private Color mColor = Color.WHITE;

    /*public Shape() {
    }*/

    public Shape(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public Shape(JsonObject conf) {
        this.mX = conf.get("x").getAsInt();
        this.mY = conf.get("y").getAsInt();
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        this.mColor = color;
    }

    @Override
    public String toString() {
        return "Shape {" +
                "x=" + mX +
                ", y=" + mY +
                '}';
    }

    @Override
     public boolean equals(Object obj) {
        if (obj instanceof Shape) {
            Shape shape = ((Shape) obj);
            return shape.mX == mX && shape.mY == mY;
        } else {
            return false;
        }
    }

    public abstract double getArea();

    /**
     * Subclasses must call super before drawing
     * @param g
     */
    @Override
    public final void draw(Graphics g) throws InvalidMetricsException {
        if (getArea() == 0) {
            throw new InvalidMetricsException();
        }
        g.setColor(mColor);
        doDraw(g);
    }

    protected abstract void doDraw(Graphics g);

}