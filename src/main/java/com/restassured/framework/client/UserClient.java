package com.restassured.framework.client;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restassured.framework.constants.Endpoints;
import com.restassured.framework.pojo.user.User;

/**
 * Client for user-related API operations
 */
public class UserClient extends AuthClient {
    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);

    /**
     * Get all users
     * 
     * @return Response
     */
    public Response getAllUsers() {
        logger.info("Getting all users");
        return withAuth().get(Endpoints.USERS);
    }

    /**
     * Get user by ID
     * 
     * @param userId User ID
     * @return Response
     */
    public Response getUserById(String userId) {
        logger.info("Getting user with ID: {}", userId);
        
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        
        return withAuth()
                .addPathParams(pathParams)
                .get(Endpoints.USER_BY_ID);
    }

    /**
     * Create a new user
     * 
     * @param user User object
     * @return Response
     */
    public Response createUser(User user) {
        logger.info("Creating new user: {}", user.getName());
        
        return withAuth()
                .setBody(user)
                .post(Endpoints.USERS);
    }

    /**
     * Update user
     * 
     * @param userId User ID
     * @param user   User object
     * @return Response
     */
    public Response updateUser(String userId, User user) {
        logger.info("Updating user with ID: {}", userId);
        
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        
        return withAuth()
                .addPathParams(pathParams)
                .setBody(user)
                .put(Endpoints.USER_BY_ID);
    }

    /**
     * Delete user
     * 
     * @param userId User ID
     * @return Response
     */
    public Response deleteUser(String userId) {
        logger.info("Deleting user with ID: {}", userId);
        
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        
        return withAuth()
                .addPathParams(pathParams)
                .delete(Endpoints.USER_BY_ID);
    }
}
