package com.ecp.sio.paintsample.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.awt.*;
import java.lang.reflect.Type;

/**
 * A deserializer to help the Gson library to decode fields of type java.awt.Color
 */
public class ColorDeserializer implements JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Color.decode(jsonElement.getAsString());
    }

}
