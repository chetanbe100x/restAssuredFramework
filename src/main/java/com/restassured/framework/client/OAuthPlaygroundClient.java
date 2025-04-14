package com.restassured.framework.client;

import io.restassured.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client for OAuth Playground API operations
 * API documentation: https://www.oauth.com/playground/
 */
public class OAuthPlaygroundClient extends RestAssuredClient {
    private static final Logger logger = LoggerFactory.getLogger(OAuthPlaygroundClient.class);
    private static final String BASE_URL = "https://authorization-server.com";

    // OAuth client credentials (these are example values from the playground)
    private String clientId = "sample-client-id";
    private String clientSecret = "sample-client-secret";
    private String redirectUri = "https://www.oauth.com/playground/authorization-code.html";
    private String authorizationCode;
    private String accessToken;
    private String refreshToken;

    public OAuthPlaygroundClient() {
        super();
        setBaseUrl(BASE_URL);
    }

    /**
     * Set OAuth client credentials
     *
     * @param clientId Client ID
     * @param clientSecret Client Secret
     * @param redirectUri Redirect URI
     */
    public void setOAuthCredentials(String clientId, String clientSecret, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    /**
     * Set authorization code
     *
     * @param authorizationCode Authorization code
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    /**
     * Get authorization URL for Authorization Code flow
     *
     * @param state State parameter for CSRF protection
     * @return Authorization URL
     */
    public String getAuthorizationUrl(String state) {
        return BASE_URL + "/authorize" +
               "?response_type=code" +
               "&client_id=" + clientId +
               "&redirect_uri=" + redirectUri +
               "&scope=photo+offline_access" +
               "&state=" + state;
    }

    /**
     * Get authorization URL with default state parameter
     *
     * @return Authorization URL
     */
    public String getAuthorizationUrl() {
        return getAuthorizationUrl("pqr25H_ncvhykD8H");
    }

    /**
     * Exchange authorization code for access token
     *
     * @return Response
     */
    public Response exchangeCodeForToken() {
        logger.info("Exchanging authorization code for access token");

        if (authorizationCode == null || authorizationCode.isEmpty()) {
            throw new IllegalStateException("Authorization code is not set");
        }

        // Create form parameters as a JSON object
        String formParams = "{"
            + "\"grant_type\": \"authorization_code\","
            + "\"code\": \"" + authorizationCode + "\","
            + "\"client_id\": \"" + clientId + "\","
            + "\"client_secret\": \"" + clientSecret + "\","
            + "\"redirect_uri\": \"" + redirectUri + "\""
            + "}";

        // Set the body with the form parameters
        setBody(formParams);

        Response response = post("/token");

        // Extract tokens from response
        if (response.getStatusCode() == 200) {
            this.accessToken = response.jsonPath().getString("access_token");
            this.refreshToken = response.jsonPath().getString("refresh_token");
        }

        return response;
    }

    /**
     * Refresh access token using refresh token
     *
     * @return Response
     */
    public Response refreshAccessToken() {
        logger.info("Refreshing access token");

        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalStateException("Refresh token is not set");
        }

        // Create form parameters as a JSON object
        String formParams = "{"
            + "\"grant_type\": \"refresh_token\","
            + "\"refresh_token\": \"" + refreshToken + "\","
            + "\"client_id\": \"" + clientId + "\","
            + "\"client_secret\": \"" + clientSecret + "\""
            + "}";

        // Set the body with the form parameters
        setBody(formParams);

        Response response = post("/token");

        // Update access token
        if (response.getStatusCode() == 200) {
            this.accessToken = response.jsonPath().getString("access_token");
        }

        return response;
    }

    /**
     * Get user profile using access token
     *
     * @return Response
     */
    public Response getUserProfile() {
        logger.info("Getting user profile");

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not set");
        }

        addHeader("Authorization", "Bearer " + accessToken);

        return get("/profile");
    }

    /**
     * Get user photos using access token
     *
     * @return Response
     */
    public Response getUserPhotos() {
        logger.info("Getting user photos");

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not set");
        }

        addHeader("Authorization", "Bearer " + accessToken);

        return get("/photos");
    }

    /**
     * Revoke access token
     *
     * @return Response
     */
    public Response revokeToken() {
        logger.info("Revoking token");

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not set");
        }

        // Create form parameters as a JSON object
        String formParams = "{"
            + "\"token\": \"" + accessToken + "\","
            + "\"client_id\": \"" + clientId + "\","
            + "\"client_secret\": \"" + clientSecret + "\""
            + "}";

        // Set the body with the form parameters
        setBody(formParams);

        return post("/revoke");
    }

    /**
     * Get access token
     *
     * @return Access token
     */
    public String getAccessToken() {
        return accessToken;
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
     * Set access token directly
     *
     * @param accessToken Access token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Set refresh token directly
     *
     * @param refreshToken Refresh token
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
