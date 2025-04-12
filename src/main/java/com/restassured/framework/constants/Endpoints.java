package com.restassured.framework.constants;

/**
 * Constants class to store API endpoints
 */
public class Endpoints {
    // Base URLs
    public static final String BASE_URL = "baseUrl";
    
    // User endpoints
    public static final String USERS = "/users";
    public static final String USER_BY_ID = "/users/{id}";
    
    // Post endpoints
    public static final String POSTS = "/posts";
    public static final String POST_BY_ID = "/posts/{id}";
    public static final String POSTS_BY_USER = "/users/{userId}/posts";
    
    // Comment endpoints
    public static final String COMMENTS = "/comments";
    public static final String COMMENTS_BY_POST = "/posts/{postId}/comments";
    
    // Authentication endpoints
    public static final String LOGIN = "/auth/login";
    public static final String REGISTER = "/auth/register";
    
    private Endpoints() {
        // Private constructor to prevent instantiation
    }
}
