package com.restassured.framework.client;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restassured.framework.constants.Endpoints;
import com.restassured.framework.pojo.auth.LoginRequest;
import com.restassured.framework.pojo.auth.RegisterRequest;

/**
 * Client for authentication-related API operations
 */
public class AuthClient extends RestAssuredClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthClient.class);
    private String authToken;

    /**
     * Login to the application
     * 
     * @param username Username
     * @param password Password
     * @return Response
     */
    public Response login(String username, String password) {
        logger.info("Logging in with username: {}", username);
        
        LoginRequest loginRequest = new LoginRequest(username, password);
        setBody(loginRequest);
        
        Response response = post(Endpoints.LOGIN);
        
        if (response.getStatusCode() == 200) {
            authToken = response.jsonPath().getString("token");
            logger.info("Login successful, token received");
        } else {
            logger.warn("Login failed with status code: {}", response.getStatusCode());
        }
        
        return response;
    }

    /**
     * Register a new user
     * 
     * @param username Username
     * @param email    Email
     * @param password Password
     * @return Response
     */
    public Response register(String username, String email, String password) {
        logger.info("Registering new user with username: {}", username);
        
        RegisterRequest registerRequest = new RegisterRequest(username, email, password);
        setBody(registerRequest);
        
        return post(Endpoints.REGISTER);
    }

    /**
     * Get authentication token
     * 
     * @return Authentication token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set authentication token
     * 
     * @param authToken Authentication token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Add authentication header to request
     * 
     * @return AuthClient instance
     */
    public AuthClient withAuth() {
        if (authToken != null && !authToken.isEmpty()) {
            addHeader("Authorization", "Bearer " + authToken);
        } else {
            logger.warn("Auth token is null or empty");
        }
        return this;
    }
}
