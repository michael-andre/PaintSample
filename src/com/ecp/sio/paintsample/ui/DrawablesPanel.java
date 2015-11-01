package com.ecp.sio.paintsample.ui;

import javax.swing.*;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ecp.sio.paintsample.InvalidMetricsException;
import com.ecp.sio.paintsample.model.*;

/**
 * DrawablePanel is a custom panel (part of UI) that can display any kind of Drawables.
 */
public class DrawablesPanel extends JPanel {

    private static final Logger LOG = Logger.getLogger(DrawablesPanel.class.getSimpleName());

    // Encapsulation: this field is private, not visible from outside
    // We provide a setter to edit the value after instantiation
    private Iterable<Drawable> mDrawables;

    /**
     * @param drawables The initial Drawables elements to be displayed by the panel. May be null.
     */
    public DrawablesPanel(Iterable<Drawable> drawables) {
        mDrawables = drawables;
    }

    /**
     * Because of the condition preceding the assignation a mDrawables, this code is not thread safe.
     * We don't want multiple threads to enter the method at the same time.
     * We mark it as "synchronized" to avoid it
     * @param drawables The new drawables to display
     */
    public synchronized void setDrawables(Iterable<Drawable> drawables) {
        if (mDrawables == null && drawables != null) {
            LOG.info("First non-null drawable list given to our panel");
        }
        mDrawables = drawables;
        // Storing the drawable is not enough
        // We must request the layout manager that we need to be re-displayed when it is possible
        repaint();
    }

    /**
     * The paint() method is automatically called when it is time for us to draw on the screen.
     * We never call this method ourselves.
     * It is called, for instance, when resizing then panel, or if we have requested it with repaint().
     * @param g The "graphic context" we are in, ie an object we can use to draw something on screen
     */
    @Override
    public void paint(Graphics g) {
        // Call super implementation first, so that the default appearance of the panel can be drawn (ig the background color)
        super.paint(g);

        // We may have no drawable yet
        if (mDrawables == null) return;

        for (Drawable drawable : mDrawables) {
            try {
                drawable.draw(g);
            } catch (InvalidMetricsException e) {
                // The draw() method can throw this custom exception, we must handle it
                LOG.info("Shape with no area");
            } catch (Exception e) {
                // Any other type of error goes here
                LOG.log(
                        Level.SEVERE,
                        "Failed to draw shape",
                        e
                );
            }
        }

    }

}
