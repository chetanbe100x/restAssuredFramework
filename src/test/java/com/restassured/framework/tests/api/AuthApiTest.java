package com.restassured.framework.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.framework.client.AuthClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Test class for Authentication API endpoints
 */
public class AuthApiTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthApiTest.class);
    private AuthClient authClient;
    private String username;
    private String password;
    private String email;

    @BeforeClass
    public void init() {
        logger.info("Initializing AuthApiTest");
        authClient = new AuthClient();
        
        // Test data
        username = "testuser_" + System.currentTimeMillis();
        password = "Password123!";
        email = username + "@example.com";
    }

    @Test(priority = 1)
    public void testRegister() {
        logger.info("Testing register API");
        
        Response response = authClient.register(username, email, password);
        
        // Note: In a real API, this would return 201 CREATED
        // For demonstration, we're checking for 200 OK
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("username")).isEqualTo(username);
    }

    @Test(priority = 2, dependsOnMethods = "testRegister")
    public void testLogin() {
        logger.info("Testing login API");
        
        Response response = authClient.login(username, password);
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("token")).isNotEmpty();
        
        // Verify token is stored in client
        assertThat(authClient.getAuthToken()).isNotEmpty();
    }
}
