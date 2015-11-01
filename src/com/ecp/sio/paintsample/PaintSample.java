package com.ecp.sio.paintsample;

// On top of any Java file, imports reference classes in other packages
// Then the class can be referenced only by its short name
// You can import all classes from a package with a * wildcard
// Classes on the same package or in java.lang.* doesn't need to be imported
import com.ecp.sio.paintsample.gson.ColorDeserializer;
import com.ecp.sio.paintsample.gson.DrawableDeserializer;
import com.ecp.sio.paintsample.gson.PointDeserializer;
import com.ecp.sio.paintsample.model.Circle;
import com.ecp.sio.paintsample.model.Drawable;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.ecp.sio.paintsample.ui.DrawablesPanel;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the declared main class of our program.
 * To be a "main class", the class only needs to have a public static method main()
 */
public class PaintSample {

    // Constants are always static (to be shared across instances) & final (to get a unique value and never change)
    // Constants car be strings (like TAG), numbers, but also full objects (like LOG)
    public static final String TAG = PaintSample.class.getName();
    public static final Logger LOG = Logger.getLogger(TAG);

    /**
     * This method is the entry point of the program.
     * The PaintSample class is NOT instanciated. Instead, this method is "static", ie it is called directly on the class itself.
     * @param args The parameters provided to the program (ie command line)
     */
    public static void main(String[] args) {

        // Our program gracefully deals with threads:
        // We create and show all the UI on the main thread

        // Create and show a window
        JFrame window = new JFrame("Photoshop");
        window.setSize(640, 480);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        // Add a panel to the window
        DrawablesPanel panel = new DrawablesPanel(null);
        panel.setBackground(Color.DARK_GRAY);
        window.add(panel);

        // Now, start a new Thread for asynchronous execution
        // We have to provide an object implementing "Runnable"
        // Runnable is an interface, but we can use an anonymous class
        new Thread(new Runnable() {

            /**
             * The run() method will be executed on the new thread when we call start() on it.
             * We don't call run() ourselves!
             */
            @Override
            public void run() {

                // Get the JSON definition from URL
                // We can either use our native implementation, or a library
                String json = null;
                try {
                    URL url = new URL("http://pastebin.com/raw.php?i=3h37X9Ag");
                    // json = getJsonNative(url);
                    json = IOUtils.toString(url);
                    // We can simulate a "long" download making the current thread sleep
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    // Here we have catch both exception types
                    LOG.log(Level.SEVERE, "Failed to download JSON", e);
                }

                // Convert string to object model
                // We can use our custom parser, or take benefits from Gson library
                List<Drawable> drawables;
                //drawables = getStaticShapes();
                //drawables = parseJsonCustom(json);
                // The following syntax use the "builder" pattern
                // We can chain calls on the GsonBuilder to add configurations
                // Eventually we call create() to obtain an immutable instance
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Drawable.class, new DrawableDeserializer())
                        .registerTypeAdapter(Color.class, new ColorDeserializer())
                        .registerTypeAdapter(Polygon.Point.class, new PointDeserializer())
                        .create();
                // The TypeToken generic class is an utility provided by the Gson library
                // It bypasses the limitation of generics which prevents from using List<Drawable>.class
                drawables = gson.fromJson(json, new TypeToken<List<Drawable>>(){}.getType());

                panel.setDrawables(drawables);
            }

        }).start();

    }

    /**
     * The method downloads the content of an URL and returns it as a String
     * @param url The URL to be retrieved
     * @return The JSON content
     * @throws IOException if something goes wrong during download
     */
    private static String getJsonNative(URL url) throws IOException {
        // URL.openStream() establish a connection to the URL ("open a pipe")
        // InputStreamReader prepares the reading on the above stream
        // BufferedReader wraps InputStreamReader adding the buffering capability
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openStream()
                )
        );
        // StringBuilder will receive the lines and concatenate in a more efficient way than "+"
        StringBuilder response = new StringBuilder();

        // Until now, nothing has been read!
        // Here we loop: the single line inside the while reads a line and checks if the end of the stream is reached
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // Always close streams after reading...
        in.close();

        return response.toString();
    }

    // Our custom JSON parser needs a mapping between the "type" key in the JSON and the actual class
    // We define it using a constant dictionary (in java, a Map)
    // The values are of type Class<? extends Drawable>, ie an instance of Class the represents any descendant of Drawable
    // In this context, we use "extends" although Drawable is an interface
    private static final Map<String, Class<? extends Drawable>> SHAPE_CLASSES = new HashMap<>();
    static {
        SHAPE_CLASSES.put("circle", Circle.class);
        SHAPE_CLASSES.put("rectangle", Rectangle.class);
        SHAPE_CLASSES.put("polygon", Polygon.class);
    }

    /**
     * This parser is half-custom: we user a JsonParser from Gson library to get an object representation of the String
     * Then we create shapes using our dictionary of types
     * @param json The JSON string
     * @return The shapes parsed from the JSON string
     */
    private static List<Drawable> parseJsonCustom(String json) {

        // Create an empty list of shapes.
        // List is an interface, we must use a class implementing it: ArrayList
        List<Drawable> shapes = new ArrayList<>();
        try {
            // The JsonParser can convert a string into generic JsonElement objects
            // Then we have methods like getAsJsonArray() witch avoid casting
            // These methods will fail if the underlying data is not of the good type!
            JsonArray root = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement e : root) {

                // We know that every element in the top-level list will be a JSON object
                JsonObject obj = e.getAsJsonObject();

                // The following was our first non-generic parser: we had to explicitly list all types
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

                // Lookup for a Class in our types dictionary
                Class<? extends Drawable> objectClass = SHAPE_CLASSES.get(obj.get("type").getAsString());
                if (objectClass != null) {
                    // Let the CustomJsonParser create a Drawable from the configuration, using the Class
                    shapes.add(CustomJsonParser.parse(obj, objectClass));
                }
            }
        } catch (ReflectiveOperationException e) {
            // Parsing involves reflection, so we must take car of all exceptions of this type
            LOG.log(Level.SEVERE, "Reflection error", e);
        }
        return shapes;
    }

    /**
     * This was our very first try with drawables: use some static shapes
     * @return A fixed list of shapes
     */
    private static List<Drawable> getStaticShapes() {

        List<Drawable> shapes = new ArrayList<>();

        Rectangle rect1 = new Rectangle(10, 20, 50, 100);
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

        shapes.add(circ1);
        shapes.add(rect1);
        shapes.add(poly1);

        return shapes;
    }

}
