package com.ecp.sio.paintsample.model;

import java.awt.*;

/**
 * Created by Micha�l on 28/09/2015.
 */
public abstract class Shape implements Drawable {

    public static final int BLACK = 0;

    private int mX, mY;
    private Color mColor = Color.WHITE;

    /*public Shape() {
    }*/

    public Shape(int x, int y) {
        this.mX = x;
        this.mY = y;
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

    @Override
    public void draw(Graphics g) {
        g.setColor(mColor);
    }
}
