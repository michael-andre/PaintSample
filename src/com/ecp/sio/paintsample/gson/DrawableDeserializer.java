package com.ecp.sio.paintsample.gson;

import com.ecp.sio.paintsample.model.Circle;
import com.ecp.sio.paintsample.model.Drawable;
import com.ecp.sio.paintsample.model.Polygon;
import com.ecp.sio.paintsample.model.Rectangle;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michaël on 30/10/2015.
 */
public class DrawableDeserializer implements JsonDeserializer<Drawable> {

    private static final Map<String, Class<? extends Drawable>> SHAPE_CLASSES = new HashMap<>();
    static {
        SHAPE_CLASSES.put("circle", Circle.class);
        SHAPE_CLASSES.put("rectangle", Rectangle.class);
        SHAPE_CLASSES.put("polygon", Polygon.class);
    }

    @Override
    public Drawable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        String key = jsonElement.getAsJsonObject().get("type").getAsString();
        Class<? extends Drawable> drawableType = SHAPE_CLASSES.get(key);
        if (drawableType != null) {
            return context.deserialize(jsonElement, drawableType);
        } else {
            return null;
        }
    }

}