package com.restassured.framework.client;

import io.restassured.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client for Automation Exercise API operations
 * API documentation: https://automationexercise.com/api_list
 */
public class AutomationExerciseClient extends RestAssuredClient {
    private static final Logger logger = LoggerFactory.getLogger(AutomationExerciseClient.class);
    private static final String BASE_URL = "https://automationexercise.com/api";

    public AutomationExerciseClient() {
        super();
        setBaseUrl(BASE_URL);
    }

    /**
     * Get all products list
     * 
     * @return Response
     */
    public Response getAllProducts() {
        logger.info("Getting all products");
        return get("/productsList");
    }

    /**
     * Get all brands list
     * 
     * @return Response
     */
    public Response getAllBrands() {
        logger.info("Getting all brands");
        return get("/brandsList");
    }

    /**
     * Search for products
     * 
     * @param searchTerm Search term
     * @return Response
     */
    public Response searchProducts(String searchTerm) {
        logger.info("Searching for products with term: {}", searchTerm);
        addQueryParam("search_product", searchTerm);
        return post("/searchProduct");
    }

    /**
     * Verify login with credentials
     * 
     * @param email Email
     * @param password Password
     * @return Response
     */
    public Response verifyLogin(String email, String password) {
        logger.info("Verifying login for email: {}", email);
        addQueryParam("email", email);
        addQueryParam("password", password);
        return post("/verifyLogin");
    }

    /**
     * Create a new user account
     * 
     * @param name Name
     * @param email Email
     * @param password Password
     * @return Response
     */
    public Response createAccount(String name, String email, String password) {
        logger.info("Creating account for email: {}", email);
        
        addQueryParam("name", name);
        addQueryParam("email", email);
        addQueryParam("password", password);
        
        // Add default values for required fields
        addQueryParam("title", "Mr");
        addQueryParam("birth_date", "01");
        addQueryParam("birth_month", "01");
        addQueryParam("birth_year", "1990");
        addQueryParam("firstname", name.split(" ")[0]);
        addQueryParam("lastname", name.contains(" ") ? name.substring(name.indexOf(" ") + 1) : "");
        addQueryParam("company", "Test Company");
        addQueryParam("address1", "123 Test St");
        addQueryParam("country", "United States");
        addQueryParam("zipcode", "12345");
        addQueryParam("state", "Test State");
        addQueryParam("city", "Test City");
        addQueryParam("mobile_number", "1234567890");
        
        return post("/createAccount");
    }

    /**
     * Delete a user account
     * 
     * @param email Email
     * @param password Password
     * @return Response
     */
    public Response deleteAccount(String email, String password) {
        logger.info("Deleting account for email: {}", email);
        addQueryParam("email", email);
        addQueryParam("password", password);
        return delete("/deleteAccount");
    }
}
