package com.ecp.sio.paintsample;

import com.ecp.sio.paintsample.model.*;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.ecp.sio.paintsample.model.Shape;
import com.ecp.sio.paintsample.ui.DrawablesPanel;
import com.google.gson.*;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michaël on 21/09/2015.
 */
public class PaintSample {

    private static final Map<String, Class<? extends Drawable>> SHAPE_CLASSES = new HashMap<>();
    static {
        SHAPE_CLASSES.put("circle", Circle.class);
        SHAPE_CLASSES.put("rectangle", Rectangle.class);
        SHAPE_CLASSES.put("polygon", Polygon.class);
    }

    public static final String TAG = PaintSample.class.getName();
    public static final Logger LOG = Logger.getLogger(TAG);

    // Single line comment
    public static void main(String[] args) {

        // Get the JSON definition from URL
        String json = null;
        try {
            URL url = new URL("http://pastebin.com/raw.php?i=dzf3A5gp");
            // json = getJsonNative(url);
            json = IOUtils.toString(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert string to object model
        List<Drawable> drawables;
        drawables = parseShapesCustom(json);

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

    private static String getJsonNative(URL url) throws IOException {
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
        return response.toString();
    }

    private static List<Drawable> parseShapesCustom(String json) {
        List<Drawable> shapes = new ArrayList<>();
        try {
            JsonArray root = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement e : root) {
                JsonObject obj = e.getAsJsonObject();
                /*String type = obj.get("type").getAsString();
                switch (type) {
                    case "circle":
                        Circle circle = new CustomJsonParser<Circle>()
                                .parse(obj, Circle.class);
                        shapes.add(circle);
                        break;
                    case "rectangle":
                        Rectangle rectangle = new CustomJsonParser<Rectangle>()
                                .parse(obj, Rectangle.class.getName());
                        shapes.add(rectangle);
                        break;
                    case "polygon":
                        shapes.add(new Polygon(obj));
                        break;
                    default:
                        //throw new IllegalStateException("Unknown shape");
                        LOG.warning("Unknown shape");
                }*/
                Class<? extends Drawable> objectClass = SHAPE_CLASSES.get(obj.get("type").getAsString());
                if (objectClass != null) {
                    shapes.add(CustomJsonParser.parse(obj, objectClass));
                }
            }
        } catch (ReflectiveOperationException e) {
            LOG.log(Level.SEVERE, "Reflection error", e);
        }
        return shapes;
    }

}
