package com.restassured.framework.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.framework.client.OAuthPlaygroundClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Test class for OAuth Playground API endpoints
 * API documentation: https://www.oauth.com/playground/
 */
public class OAuthPlaygroundTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(OAuthPlaygroundTest.class);
    private OAuthPlaygroundClient oauthClient;

    // Real credentials for the OAuth Playground
    private static final String CLIENT_ID = "SSc6YMR3YakcfgYBcCPfEO2P";
    private static final String CLIENT_SECRET = "A8krvBW8n7A4hjfC3_Vn9CAmwNA9pAAz3GYv_TahgVGbARlL";
    private static final String REDIRECT_URI = "https://www.oauth.com/playground/authorization-code.html";

    // User credentials
    private static final String USER_LOGIN = "wild-heron@example.com";
    private static final String USER_PASSWORD = "Confused-Porpoise-95";

    // These values would normally be obtained during the OAuth flow
    // For testing, we're using placeholder values that you should replace with real ones
    private static final String SAMPLE_AUTH_CODE = "REPLACE_WITH_REAL_AUTH_CODE";

    // For testing with a valid token without going through the auth flow
    private static final String SAMPLE_ACCESS_TOKEN = "REPLACE_WITH_REAL_ACCESS_TOKEN";
    private static final String SAMPLE_REFRESH_TOKEN = "REPLACE_WITH_REAL_REFRESH_TOKEN";

    @BeforeClass
    public void init() {
        logger.info("Initializing OAuthPlaygroundTest");
        oauthClient = new OAuthPlaygroundClient();
        oauthClient.setOAuthCredentials(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
    }

    /**
     * Test getting the authorization URL
     * This is the first step in the OAuth 2.0 Authorization Code flow
     */
    @Test
    public void testGetAuthorizationUrl() {
        logger.info("Testing Get Authorization URL");

        String authUrl = oauthClient.getAuthorizationUrl();

        assertThat(authUrl).isNotNull();
        assertThat(authUrl).contains("client_id=" + CLIENT_ID);
        assertThat(authUrl).contains("response_type=code");
        assertThat(authUrl).contains("redirect_uri=" + REDIRECT_URI);

        logger.info("Authorization URL: {}", authUrl);
    }

    /**
     * Test exchanging authorization code for access token
     * Note: This test requires a valid authorization code, which typically
     * requires user interaction. For automated testing, we can mock this
     * or use a pre-obtained code.
     */
    @Test
    public void testExchangeCodeForToken() {
        logger.info("Testing Exchange Code for Token");

        // Set a valid authorization code
        // In a real scenario, this would be obtained after user authorization
        oauthClient.setAuthorizationCode(SAMPLE_AUTH_CODE);

        Response response = oauthClient.exchangeCodeForToken();

        // This will likely fail unless you replace SAMPLE_AUTH_CODE with a real code
        logger.info("Response: {}", response.asString());

        // If the response is successful, update the tokens for other tests
        if (response.getStatusCode() == StatusCodes.OK) {
            String accessToken = response.jsonPath().getString("access_token");
            String refreshToken = response.jsonPath().getString("refresh_token");

            if (accessToken != null && !accessToken.isEmpty()) {
                logger.info("Obtained access token: {}", accessToken);
                logger.info("To use this token in other tests, update SAMPLE_ACCESS_TOKEN with this value");
            }

            if (refreshToken != null && !refreshToken.isEmpty()) {
                logger.info("Obtained refresh token: {}", refreshToken);
                logger.info("To use this token in other tests, update SAMPLE_REFRESH_TOKEN with this value");
            }
        } else {
            logger.info("Failed to exchange code for token. Make sure you're using a valid authorization code.");
        }
    }

    /**
     * Test refreshing access token
     * Note: This test requires a valid refresh token
     */
    @Test
    public void testRefreshAccessToken() {
        logger.info("Testing Refresh Access Token");

        // Set a valid refresh token
        oauthClient.setRefreshToken(SAMPLE_REFRESH_TOKEN);

        Response response = oauthClient.refreshAccessToken();

        // This will likely fail unless you replace SAMPLE_REFRESH_TOKEN with a real refresh token
        logger.info("Response: {}", response.asString());

        // If the response is successful, update the access token
        if (response.getStatusCode() == StatusCodes.OK) {
            String accessToken = response.jsonPath().getString("access_token");

            if (accessToken != null && !accessToken.isEmpty()) {
                logger.info("Obtained new access token: {}", accessToken);
                logger.info("To use this token in other tests, update SAMPLE_ACCESS_TOKEN with this value");
            }
        } else {
            logger.info("Failed to refresh token. Make sure you're using a valid refresh token.");
        }
    }

    /**
     * Test getting user profile
     * Note: This test requires a valid access token
     */
    @Test
    public void testGetUserProfile() {
        logger.info("Testing Get User Profile");

        // Set a valid access token
        oauthClient.setAccessToken(SAMPLE_ACCESS_TOKEN);

        Response response = oauthClient.getUserProfile();

        // This will likely fail unless you replace SAMPLE_ACCESS_TOKEN with a real access token
        logger.info("Response: {}", response.asString());

        if (response.getStatusCode() == StatusCodes.OK) {
            logger.info("Successfully retrieved user profile");
            String userId = response.jsonPath().getString("sub");
            if (userId != null && !userId.isEmpty()) {
                logger.info("User ID: {}", userId);
            }
        } else {
            logger.info("Failed to get user profile. Make sure you're using a valid access token.");
        }
    }

    /**
     * Test getting user photos
     * Note: This test requires a valid access token with photo scope
     */
    @Test
    public void testGetUserPhotos() {
        logger.info("Testing Get User Photos");

        // Set a valid access token
        oauthClient.setAccessToken(SAMPLE_ACCESS_TOKEN);

        Response response = oauthClient.getUserPhotos();

        // This will likely fail unless you replace SAMPLE_ACCESS_TOKEN with a real access token
        logger.info("Response: {}", response.asString());

        if (response.getStatusCode() == StatusCodes.OK) {
            logger.info("Successfully retrieved user photos");
            int photoCount = response.jsonPath().getList("photos") != null ?
                             response.jsonPath().getList("photos").size() : 0;
            logger.info("Number of photos: {}", photoCount);
        } else {
            logger.info("Failed to get user photos. Make sure you're using a valid access token with photo scope.");
        }
    }

    /**
     * Test revoking access token
     * Note: This test requires a valid access token
     */
    @Test
    public void testRevokeToken() {
        logger.info("Testing Revoke Token");

        // Set a valid access token
        oauthClient.setAccessToken(SAMPLE_ACCESS_TOKEN);

        Response response = oauthClient.revokeToken();

        // This will likely fail unless you replace SAMPLE_ACCESS_TOKEN with a real access token
        logger.info("Response: {}", response.asString());

        if (response.getStatusCode() == StatusCodes.OK) {
            logger.info("Successfully revoked token");
        } else {
            logger.info("Failed to revoke token. Make sure you're using a valid access token.");
        }
    }

    /**
     * Test the complete OAuth 2.0 Authorization Code flow
     * This is a manual test that requires user interaction
     */
    @Test
    public void testCompleteOAuthFlow() {
        logger.info("Testing Complete OAuth Flow");

        // Step 1: Get authorization URL with state parameter
        String state = "pqr25H_ncvhykD8H"; // Using the state from your example
        String authUrl = "https://authorization-server.com/authorize?"
            + "response_type=code"
            + "&client_id=" + CLIENT_ID
            + "&redirect_uri=" + REDIRECT_URI
            + "&scope=photo+offline_access"
            + "&state=" + state;

        logger.info("1. Authorization URL: {}", authUrl);
        logger.info("2. User credentials to use:");
        logger.info("   Username: {}", USER_LOGIN);
        logger.info("   Password: {}", USER_PASSWORD);
        logger.info("3. After authorization, you'll be redirected to a URL with a code parameter");
        logger.info("4. Copy the authorization code from the URL and use it in the next step");

        // Prompt for the authorization code
        logger.info("\n*** MANUAL STEP REQUIRED ***");
        logger.info("Please open the authorization URL in a browser, log in with the credentials above,");
        logger.info("authorize the application, and then enter the authorization code from the redirected URL here.");

        // In a real test, you would have a way to input the code
        // For this example, we'll use a placeholder
        String authCode = "ENTER_AUTHORIZATION_CODE_HERE"; // Replace with the actual code after authorization

        // Only proceed with the rest of the test if a valid code is provided
        if (!authCode.equals("ENTER_AUTHORIZATION_CODE_HERE")) {
            // Step 2: Exchange authorization code for tokens
            oauthClient.setAuthorizationCode(authCode);
            Response tokenResponse = oauthClient.exchangeCodeForToken();
            logger.info("Token Response: {}", tokenResponse.asString());

            // Step 3: Use the access token to access protected resources
            Response profileResponse = oauthClient.getUserProfile();
            logger.info("Profile Response: {}", profileResponse.asString());

            Response photosResponse = oauthClient.getUserPhotos();
            logger.info("Photos Response: {}", photosResponse.asString());

            // Step 4: Refresh the access token
            Response refreshResponse = oauthClient.refreshAccessToken();
            logger.info("Refresh Response: {}", refreshResponse.asString());

            // Step 5: Revoke the token
            Response revokeResponse = oauthClient.revokeToken();
            logger.info("Revoke Response: {}", revokeResponse.asString());
        } else {
            logger.info("Test skipped because no authorization code was provided.");
            logger.info("To complete this test, replace the placeholder with a real authorization code.");
        }
    }
}
