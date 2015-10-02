package com.ecp.sio.paintsample.ui;

import javax.swing.*;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ecp.sio.paintsample.InvalidMetricsException;
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
            try {
                drawable.draw(g);
            } catch (InvalidMetricsException e) {
                Logger.getLogger("DrawablesPanel").info("Shape with no area");
            } catch (Exception e) {
                Logger.getLogger("DrawablesPanel").log(
                        Level.SEVERE,
                        "Failed to draw shape",
                        e
                );
            }
        }

        /*try {
            for (Drawable drawable : mDrawables) {
                drawable.draw(g);
            }
        } catch (InvalidMetricsException e) {
            Logger.getLogger("DrawablesPanel").info("Shape with no area");
        } catch (Exception e) {
            Logger.getLogger("DrawablesPanel").log(
                    Level.SEVERE,
                    "Failed to draw shape",
                    e
            );
        }*/

        //Logger.getLogger("DrawablesPanel").info("paint");
    }

}
