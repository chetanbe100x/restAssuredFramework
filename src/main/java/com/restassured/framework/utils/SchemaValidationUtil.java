package com.restassured.framework.utils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;

/**
 * Utility class for JSON schema validation
 */
public class SchemaValidationUtil {
    private static final Logger logger = LoggerFactory.getLogger(SchemaValidationUtil.class);

    private SchemaValidationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Validate response against JSON schema
     * 
     * @param response   Response to validate
     * @param schemaPath Path to schema file in classpath
     * @return true if validation passes, false otherwise
     */
    public static boolean validateSchema(Response response, String schemaPath) {
        try {
            response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
            logger.info("Schema validation successful for schema: {}", schemaPath);
            return true;
        } catch (AssertionError e) {
            logger.error("Schema validation failed for schema: {}", schemaPath, e);
            return false;
        }
    }
}
