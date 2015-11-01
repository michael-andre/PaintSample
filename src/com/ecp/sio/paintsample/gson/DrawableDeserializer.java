package com.ecp.sio.paintsample.gson;

import com.ecp.sio.paintsample.model.Circle;
import com.ecp.sio.paintsample.model.Drawable;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A deserializer to help the Gson library to decode instances of type Drawable
 */
public class DrawableDeserializer implements JsonDeserializer<Drawable> {

    // The deserializer needs the mapping between "type" key and the actual class to use
    private static final Map<String, Class<? extends Drawable>> SHAPE_CLASSES = new HashMap<>();
    static {
        SHAPE_CLASSES.put("circle", Circle.class);
        SHAPE_CLASSES.put("rectangle", Rectangle.class);
        SHAPE_CLASSES.put("polygon", Polygon.class);
    }

    @Override
    public Drawable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

        // The JSON representing the shape will be an object: get the "type" key
        String key = jsonElement.getAsJsonObject().get("type").getAsString();

        // Find the corresponding class in the dictionary
        Class<? extends Drawable> drawableType = SHAPE_CLASSES.get(key);
        if (drawableType != null) {

            // Now we know which concrete class to use for the Drawable.
            // We can let GSON deserialize it using the JsonDeserializationContext parameter
            return context.deserialize(jsonElement, drawableType);

        } else {

            // Unknown types will yield null values in the list (possible, but risky...)
            return null;
        }
    }

}