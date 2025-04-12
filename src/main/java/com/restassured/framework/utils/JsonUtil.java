package com.restassured.framework.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Utility class for JSON operations
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Convert object to JSON string
     * 
     * @param object Object to convert
     * @return JSON string
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON", e);
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    /**
     * Convert JSON string to object
     * 
     * @param <T>        Type of object
     * @param jsonString JSON string
     * @param valueType  Class of object
     * @return Object of type T
     */
    public static <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (IOException e) {
            logger.error("Error converting JSON to object", e);
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }

    /**
     * Parse JSON string to JsonNode
     * 
     * @param jsonString JSON string
     * @return JsonNode
     */
    public static JsonNode parseJson(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    /**
     * Create empty JSON object
     * 
     * @return ObjectNode
     */
    public static ObjectNode createJsonObject() {
        return objectMapper.createObjectNode();
    }

    /**
     * Pretty print JSON string
     * 
     * @param jsonString JSON string
     * @return Formatted JSON string
     */
    public static String prettyPrint(String jsonString) {
        try {
            Object json = objectMapper.readValue(jsonString, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            logger.error("Error formatting JSON", e);
            return jsonString;
        }
    }
}
