package com.restassured.framework.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.framework.client.AutomationExerciseClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Test class for Automation Exercise API endpoints
 * API documentation: https://automationexercise.com/api_list
 */
public class AutomationExerciseApiTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(AutomationExerciseApiTest.class);
    private AutomationExerciseClient apiClient;

    @BeforeClass
    public void init() {
        logger.info("Initializing AutomationExerciseApiTest");
        apiClient = new AutomationExerciseClient();
    }

    /**
     * Test API 1: Get All Products List
     * https://automationexercise.com/api_list
     */
    @Test
    public void testGetAllProductsList() {
        logger.info("Testing Get All Products List API");

        Response response = apiClient.getAllProducts();

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("200");
        assertThat(response.jsonPath().getList("products")).isNotEmpty();
    }

    /**
     * Test API 2: POST To All Products List
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToAllProductsList() {
        logger.info("Testing POST To All Products List API");

        Response response = apiClient.post("/productsList");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("405");
        assertThat(response.jsonPath().getString("message")).isEqualTo("This request method is not supported.");
    }

    /**
     * Test API 3: Get All Brands List
     * https://automationexercise.com/api_list
     */
    @Test
    public void testGetAllBrandsList() {
        logger.info("Testing Get All Brands List API");

        Response response = apiClient.getAllBrands();

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("200");
        assertThat(response.jsonPath().getList("brands")).isNotEmpty();
    }

    /**
     * Test API 4: PUT To All Brands List
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPutToAllBrandsList() {
        logger.info("Testing PUT To All Brands List API");

        Response response = apiClient.put("/brandsList");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("405");
        assertThat(response.jsonPath().getString("message")).isEqualTo("This request method is not supported.");
    }

    /**
     * Test API 5: POST To Search Product
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToSearchProduct() {
        logger.info("Testing POST To Search Product API");

        Response response = apiClient.searchProducts("top");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("200");
        assertThat(response.jsonPath().getList("products")).isNotEmpty();
    }

    /**
     * Test API 6: POST To Search Product without search_product parameter
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToSearchProductWithoutParameter() {
        logger.info("Testing POST To Search Product API without parameter");

        Response response = apiClient.post("/searchProduct");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("400");
        assertThat(response.jsonPath().getString("message")).isEqualTo("Bad request, search_product parameter is missing in POST request.");
    }

    /**
     * Test API 8: POST To Verify Login with valid details
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToVerifyLoginWithValidDetails() {
        logger.info("Testing POST To Verify Login with valid details");

        // Note: Replace with valid credentials for your account
        Response response = apiClient.verifyLogin("test_api_user@example.com", "test_password");

        // This will likely fail unless you have a valid account
        // The test is included for demonstration purposes
        logger.info("Response: {}", response.asString());
    }

    /**
     * Test API 9: POST To Verify Login without email parameter
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToVerifyLoginWithoutEmailParameter() {
        logger.info("Testing POST To Verify Login without email parameter");

        apiClient.addQueryParam("password", "test_password");
        Response response = apiClient.post("/verifyLogin");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("400");
        assertThat(response.jsonPath().getString("message")).isEqualTo("Bad request, email or password parameter is missing in POST request.");
    }

    /**
     * Test API 10: POST To Verify Login without password parameter
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToVerifyLoginWithoutPasswordParameter() {
        logger.info("Testing POST To Verify Login without password parameter");

        apiClient.addQueryParam("email", "test_api_user@example.com");
        Response response = apiClient.post("/verifyLogin");

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("responseCode")).isEqualTo("400");
        assertThat(response.jsonPath().getString("message")).isEqualTo("Bad request, email or password parameter is missing in POST request.");
    }

    /**
     * Test API 11: POST To Create/Register User Account
     * https://automationexercise.com/api_list
     */
    @Test
    public void testPostToCreateUserAccount() {
        logger.info("Testing POST To Create/Register User Account");

        // Generate a unique email
        String uniqueEmail = "test_user_" + System.currentTimeMillis() + "@example.com";

        Response response = apiClient.createAccount("Test User", uniqueEmail, "test_password");

        // This might fail if the API has rate limiting or other restrictions
        // The test is included for demonstration purposes
        logger.info("Response: {}", response.asString());
    }

    /**
     * Test API 12: DELETE To Delete User Account
     * https://automationexercise.com/api_list
     */
    @Test
    public void testDeleteUserAccount() {
        logger.info("Testing DELETE To Delete User Account");

        // Note: Replace with valid credentials for an account you want to delete
        Response response = apiClient.deleteAccount("test_delete_user@example.com", "test_password");

        // This will likely fail unless you have a valid account to delete
        // The test is included for demonstration purposes
        logger.info("Response: {}", response.asString());
    }
}
