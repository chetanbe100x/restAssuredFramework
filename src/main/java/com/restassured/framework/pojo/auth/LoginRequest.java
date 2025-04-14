package com.restassured.framework.pojo.auth;

/**
 * POJO for login request
 */
public class LoginRequest {
    private String username;
    private String password;

    /**
     * Default constructor
     */
    public LoginRequest() {
    }

    /**
     * Constructor with all fields
     *
     * @param username Username
     * @param password Password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

    /**
     * Get password
     *
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     *
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
