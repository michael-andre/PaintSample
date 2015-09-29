package com.ecp.sio.paintsample.ui;

import javax.swing.*;
import java.awt.Graphics;

import com.ecp.sio.paintsample.model.*;

/**
 * Created by Michaël on 29/09/2015.
 */
public class DrawablesPanel extends JPanel {

    private Iterable<Drawable> mDrawables;

    public DrawablesPanel(Iterable<Drawable> drawables) {
        mDrawables = drawables;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Drawable drawable : mDrawables) {
            drawable.draw(g);
        }

        //Logger.getLogger("DrawablesPanel").info("paint");
    }

}
