package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * Created by Michaël on 28/09/2015.
 */
public abstract class Shape implements Drawable {

    private int x, y;
    private Color color;

    /*public Shape() {
    }*/

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shape(JsonObject conf) {
        this.x = conf.get("x").getAsInt();
        this.y = conf.get("y").getAsInt();
        if (conf.has("color")) {
            this.color = Color.decode(conf.get("color").getAsString());
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Shape {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
     public boolean equals(Object obj) {
        if (obj instanceof Shape) {
            Shape shape = ((Shape) obj);
            return shape.x == x && shape.y == y;
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
        if (color != null) g.setColor(color);
        else g.setColor(Color.WHITE);
        doDraw(g);
    }

    protected abstract void doDraw(Graphics g);

}