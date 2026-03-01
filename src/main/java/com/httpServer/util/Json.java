package com.httpServer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    /*
    To convert our information in the JSON file into the Java Class, we implement ObjectMapper
    using dependencies from Maven (FasterXML's Jackson)
     */
    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        if (myObjectMapper == null) {
            myObjectMapper = new ObjectMapper();
        }
        myObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return myObjectMapper;
    }

    /* JsonNode is a class from Jackson library used to represent JSON data as a tree structure.
     */

    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }

    /*
    To move this JsonNode into the Configuration class, we implement the following method
     */

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    /*
    Now to get the configuration file into the JsonNode, we implement the following method
     */

    public static JsonNode toJson(Object object) throws JsonProcessingException {
        return myObjectMapper.valueToTree(object);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyFlag(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true  );
    }

    private static String generateJson(Object object, boolean flag) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (flag){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(object);
    }
}
