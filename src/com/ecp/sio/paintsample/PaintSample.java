package com.ecp.sio.paintsample;

import com.ecp.sio.paintsample.model.*;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.ecp.sio.paintsample.ui.DrawablesPanel;
import com.google.gson.*;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Micha�l on 21/09/2015.
 */
public class PaintSample {

    public static final String TAG = PaintSample.class.getName();
    public static final Logger LOG = Logger.getLogger(TAG);

    // Single line comment
    public static void main(String[] args) {

        /*try {
            URL url = new URL("http://pastebin.com/raw.php?i=dzf3A5gp");
            //BufferedInputStream in = new BufferedInputStream(url.openStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()
                    )
            );
            StringBuilder response = new StringBuilder();

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            json = response.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String json = null;
        try {
            URL url = new URL("http://pastebin.com/raw.php?i=3h37X9Ag");
            json = IOUtils.toString(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Drawable> shapes = new ArrayList<>();

        JsonArray root = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement e : root) {
            JsonObject obj = e.getAsJsonObject();
            String type = obj.get("type").getAsString();
            switch (type) {
                case "circle":
                    shapes.add(new Circle(obj));
                    break;
                case "rectangle":
                    shapes.add(new Rectangle(obj));
                    break;
                case "polygon":
                    shapes.add(new Polygon(obj));
                    break;
                default:
                    //throw new IllegalStateException("Unknown shape");
                    LOG.warning("Unknown shape");
            }
        }

        JFrame window = new JFrame("Photoshop");
        window.setSize(640, 480);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*Rectangle rect1 = new Rectangle(10, 20, 50, 100);
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
        //Polygon poly1 = new Polygon(30, 40, points);
        Polygon poly1 = new Polygon(30, 40, null);*/


        /*shapes.add(circ1);
        shapes.add(rect1);
        shapes.add(poly1);*/

        JPanel panel = new DrawablesPanel(shapes);
        panel.setBackground(Color.DARK_GRAY);
        window.add(panel);

        /*while (true) {

        }*/

        window.setVisible(true);

    }


}
