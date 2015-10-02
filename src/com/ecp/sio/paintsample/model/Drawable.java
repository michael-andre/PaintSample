package com.ecp.sio.paintsample.model;

import com.ecp.sio.paintsample.InvalidMetricsException;

import java.awt.*;

/**
 * Created by Michaël on 29/09/2015.
 */
public interface Drawable {

    void draw(Graphics g) throws InvalidMetricsException;

}