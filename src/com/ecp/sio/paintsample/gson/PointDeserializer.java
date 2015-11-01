package com.ecp.sio.paintsample.gson;

import com.ecp.sio.paintsample.model.Polygon;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * A deserializer to help the Gson library to decode Point for the polygons
 */
public class PointDeserializer implements JsonDeserializer<Polygon.Point> {

    @Override
    public Polygon.Point deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

        // Configuration for a point can be an object or an array, we check
        if (jsonElement.isJsonObject()) {

            // We can't use the JsonDeserializationContext here, because this would yield to a round-trip.
            // Instead, we have to parse the config and instantiate manually.
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
