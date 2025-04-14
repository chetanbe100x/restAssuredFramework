package com.restassured.framework.pojo.auth;

/**
 * POJO for register request
 */
public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    /**
     * Default constructor
     */
    public RegisterRequest() {
    }

    /**
     * Constructor with all fields
     *
     * @param username Username
     * @param email Email
     * @param password Password
     */
    public RegisterRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
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
     * Get email
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
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
