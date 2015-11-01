package com.ecp.sio.paintsample;

import com.google.gson.JsonObject;

import java.lang.reflect.Constructor;

/**
 * This very basic custom JSON parser will create objects from a JSON configuation.
 * It knows nothing about our model classes (no imports).
 * BUT it requires any object we may parse to have a constructor accepting the configuration.
 */
public class CustomJsonParser {

    /**
     * This parse() method takes the name of the class as a string parameter.
     * The instantiation will obviously fail if the name is invalid.
     * In addition, nothing guaranties that the actual class will be of the generic type T.
     * That's why we have a warning about un "unchecked cast"
     * @param config The configuration object received
     * @param name The name of the class to instantiate, as a bare String
     * @param <T> This method is generic: the return type (T) will vary
     * @return A new instance of T
     * @throws ReflectiveOperationException
     */
    public static <T> T parse(JsonObject config, String name)
            throws ReflectiveOperationException {
        // Get the class from its name (quite unsafe...)
        Class<T> objectClass = (Class<T>) Class.forName(name);
        // Find the constructor that accept a single argument of type JsonObject
        Constructor<T> constructor = objectClass.getConstructor(JsonObject.class);
        // Use it to return new instance
        return constructor.newInstance(config);
    }

    /**
     * This parse() method is more robust, it receives the target class as a Class<T> instance.
     * It is still generic, but the return type T is safely determined by the Class<T> parameter.
     * @param config The configuration object received
     * @param objectClass An instance of Class representing the type T of the object to create (usually AnyClass.class).
     * @param <T> The return type T is the same as the parameter class
     * @return A new instance of T
     * @throws ReflectiveOperationException
     */
    public static <T> T parse(JsonObject config, Class<T> objectClass)
            throws ReflectiveOperationException {
        Constructor<T> constructor = objectClass.getConstructor(JsonObject.class);
        return constructor.newInstance(config);
    }

}