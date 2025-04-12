package com.restassured.framework.tests.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.framework.client.UserClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Test class demonstrating JSON Schema validation
 */
public class SchemaValidationTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SchemaValidationTest.class);
    private UserClient userClient;

    @BeforeClass
    public void init() {
        logger.info("Initializing SchemaValidationTest");
        userClient = new UserClient();
    }

    @Test
    public void testUserSchemaValidation() {
        logger.info("Testing user schema validation");
        
        Response response = userClient.getUserById("1");
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        
        // Validate response against JSON schema
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
        
        logger.info("Schema validation successful");
    }
}
