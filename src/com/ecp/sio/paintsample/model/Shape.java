package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * A base class for all geometric shapes handled by our program.
 * The class is abstract (cannot be instantiated).
 * Sub-classes must provide implementation for getArea() and doDraw().
 */
public abstract class Shape implements Drawable {

    // Use a different naming convention here:
    // The fields should be named according to the JSON keys for the Gson parser to work
    private int x, y;
    private Color color;

    // There is no no-args constructor.
    /*public Shape() {
    }*/

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * This constructor is supposed to be used when the shape is instantiated from our CustomJsonParser.
     * @param conf The JSON object for configuration
     */
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

    /**
     * toString() is a method from the parent class Object.
     * We override it to provide a readable string description for the object
     * @return A human-friendly description
     */
    @Override
    public String toString() {
        return "Shape {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * equals() is also a method from Object.
     * By default, two objects are equals only if they are the same instance (same address in memory).
     * We override it to provide a more "geometric" equality
     * @param obj An objet to test for equality
     * @return true if obj is a shape and have the same x and y
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shape) {
            Shape shape = ((Shape) obj);
            return shape.x == x && shape.y == y;
        } else {
            return false;
        }
    }

    /**
     * Sub-classes must implement this method to compute the area of the shape.
     * @return The area
     */
    public abstract double getArea();

    /**
     * Actual drawing will happen in the sub-classes.
     * But we want to perform the area check and the coloring in this method.
     * That's why we implement draw() here.
     * Sub-classes could have overridden draw() and call super.draw() first.
     * But we prefer to declare it final (not override-able), and provide another doDraw() method.
     * @param g The graphic context
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

    /**
     * Instead of implementing draw(), sub-classes must implement doDraw().
     * @param g The graphic context
     */
    protected abstract void doDraw(Graphics g);

}