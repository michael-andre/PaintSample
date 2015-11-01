package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;

import java.awt.*;

/**
 * An interface to be implemented by any object that can by drawn by a DrawablePanel
 */
public interface Drawable {

    /**
     * The draw() method will be called in DrawablePanel.paint()
     * @param g The graphic context to use on drawing
     * @throws InvalidMetricsException if the drawable can not be drawn due to a custom condition not satisfied
     */
    void draw(Graphics g) throws InvalidMetricsException;

}