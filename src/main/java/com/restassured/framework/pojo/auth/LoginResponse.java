package com.restassured.framework.pojo.auth;

/**
 * POJO for login response
 */
public class LoginResponse {
    private String token;
    private String refreshToken;
    private String userId;
    private String username;

    /**
     * Default constructor
     */
    public LoginResponse() {
    }

    /**
     * Constructor with all fields
     *
     * @param token Token
     * @param refreshToken Refresh token
     * @param userId User ID
     * @param username Username
     */
    public LoginResponse(String token, String refreshToken, String userId, String username) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
    }

    /**
     * Get token
     *
     * @return Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set token
     *
     * @param token Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get refresh token
     *
     * @return Refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Set refresh token
     *
     * @param refreshToken Refresh token
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Get user ID
     *
     * @return User ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set user ID
     *
     * @param userId User ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get username
     *
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
