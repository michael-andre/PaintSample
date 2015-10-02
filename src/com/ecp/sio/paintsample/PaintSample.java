package com.ecp.sio.paintsample;

import com.ecp.sio.paintsample.model.*;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.ecp.sio.paintsample.ui.DrawablesPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Michaël on 21/09/2015.
 */
public class PaintSample {

    // Single line comment

    public static void main(String[] args) {

        JFrame window = new JFrame("Photoshop");
        window.setSize(640, 480);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Rectangle rect1 = new Rectangle(10, 20, 200, 100);
        rect1.setColor(Color.GREEN);
        Circle circ1 = new Circle(150, 120, 100);
        circ1.setColor(Color.RED);

        List<Polygon.Point> points = new ArrayList<>(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            points.add(new Polygon.Point(
                    (int) (random.nextDouble() * 400),
                    (int) (random.nextDouble() * 300)
            ));
        }
        Polygon poly1 = new Polygon(30, 40, points);

        List<Drawable> shapes = new ArrayList<>();
        shapes.add(rect1);
        shapes.add(circ1);
        shapes.add(poly1);

        JPanel panel = new DrawablesPanel(shapes);
        panel.setBackground(Color.DARK_GRAY);
        window.add(panel);

        /*while (true) {

        }*/

        window.setVisible(true);

    }


}
