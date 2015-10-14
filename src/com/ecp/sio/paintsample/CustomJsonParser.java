package com.ecp.sio.paintsample;

import com.google.gson.JsonObject;

import java.lang.reflect.Constructor;

/**
 * Created by Micha�l on 14/10/2015.
 */
public class CustomJsonParser {

    public static <T> T parse(JsonObject config, String name)
            throws ReflectiveOperationException {
        /*Class objectClass = Class.forName(name);
        Constructor constructor = objectClass.getConstructor(JsonObject.class);
        return (T) constructor.newInstance(config);*/
        Class<T> objectClass = (Class<T>) Class.forName(name);
        Constructor<T> constructor = objectClass.getConstructor(JsonObject.class);
        return constructor.newInstance(config);
    }

    public static <T> T parse(JsonObject config, Class<T> objectClass)
            throws ReflectiveOperationException {
        Constructor<T> constructor = objectClass.getConstructor(JsonObject.class);
        return constructor.newInstance(config);
    }

}