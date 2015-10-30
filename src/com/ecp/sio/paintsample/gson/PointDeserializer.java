package com.ecp.sio.paintsample.gson;

import com.ecp.sio.paintsample.model.Polygon;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Michaël on 30/10/2015.
 */
public class PointDeserializer implements JsonDeserializer<Polygon.Point> {

    @Override
    public Polygon.Point deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (jsonElement.isJsonObject()) {
            int x = jsonElement.getAsJsonObject().get("x").getAsInt();
            int y = jsonElement.getAsJsonObject().get("y").getAsInt();
            return new Polygon.Point(x, y);
        } else if (jsonElement.isJsonArray()) {
            int x = jsonElement.getAsJsonArray().get(0).getAsInt();
            int y = jsonElement.getAsJsonArray().get(1).getAsInt();
            return new Polygon.Point(x, y);
        } else {
            return null;
        }
    }

}
