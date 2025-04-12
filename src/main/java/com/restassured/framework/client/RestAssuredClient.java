package com.restassured.framework.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restassured.framework.config.ConfigManager;
import com.restassured.framework.constants.Endpoints;
import com.restassured.framework.reporting.ExtentReportManager;
import com.restassured.framework.utils.JsonUtil;

/**
 * Base client for REST API interactions using RestAssured
 */
public class RestAssuredClient {
    private static final Logger logger = LoggerFactory.getLogger(RestAssuredClient.class);
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private String baseUrl;

    public RestAssuredClient() {
        this.baseUrl = ConfigManager.getInstance().getProperty(Endpoints.BASE_URL);
        initRequestSpec();
        initResponseSpec();
    }

    private void initRequestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    private void initResponseSpec() {
        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Set base URL
     * 
     * @param baseUrl Base URL
     * @return RestAssuredClient instance
     */
    public RestAssuredClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        initRequestSpec();
        return this;
    }

    /**
     * Add header to request
     * 
     * @param name  Header name
     * @param value Header value
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addHeader(String name, String value) {
        requestSpec = requestSpec.header(name, value);
        return this;
    }

    /**
     * Add headers to request
     * 
     * @param headers Map of headers
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addHeaders(Map<String, String> headers) {
        requestSpec = requestSpec.headers(headers);
        return this;
    }

    /**
     * Add query parameter to request
     * 
     * @param name  Parameter name
     * @param value Parameter value
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addQueryParam(String name, String value) {
        requestSpec = requestSpec.queryParam(name, value);
        return this;
    }

    /**
     * Add query parameters to request
     * 
     * @param params Map of parameters
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addQueryParams(Map<String, String> params) {
        requestSpec = requestSpec.queryParams(params);
        return this;
    }

    /**
     * Add path parameter to request
     * 
     * @param name  Parameter name
     * @param value Parameter value
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addPathParam(String name, String value) {
        requestSpec = requestSpec.pathParam(name, value);
        return this;
    }

    /**
     * Add path parameters to request
     * 
     * @param params Map of parameters
     * @return RestAssuredClient instance
     */
    public RestAssuredClient addPathParams(Map<String, String> params) {
        requestSpec = requestSpec.pathParams(params);
        return this;
    }

    /**
     * Set request body
     * 
     * @param body Request body
     * @return RestAssuredClient instance
     */
    public RestAssuredClient setBody(Object body) {
        requestSpec = requestSpec.body(body);
        return this;
    }

    /**
     * Set request body as JSON string
     * 
     * @param jsonBody JSON string
     * @return RestAssuredClient instance
     */
    public RestAssuredClient setJsonBody(String jsonBody) {
        requestSpec = requestSpec.body(jsonBody);
        return this;
    }

    /**
     * Set request content type
     * 
     * @param contentType Content type
     * @return RestAssuredClient instance
     */
    public RestAssuredClient setContentType(ContentType contentType) {
        requestSpec = requestSpec.contentType(contentType);
        return this;
    }

    /**
     * Execute GET request
     * 
     * @param endpoint API endpoint
     * @return Response
     */
    public Response get(String endpoint) {
        logger.info("Executing GET request to: {}", endpoint);
        ExtentReportManager.logInfo("Executing GET request to: " + endpoint);
        
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
        
        logResponse(response);
        return response;
    }

    /**
     * Execute POST request
     * 
     * @param endpoint API endpoint
     * @return Response
     */
    public Response post(String endpoint) {
        logger.info("Executing POST request to: {}", endpoint);
        ExtentReportManager.logInfo("Executing POST request to: " + endpoint);
        
        Response response = given()
                .spec(requestSpec)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
        
        logResponse(response);
        return response;
    }

    /**
     * Execute PUT request
     * 
     * @param endpoint API endpoint
     * @return Response
     */
    public Response put(String endpoint) {
        logger.info("Executing PUT request to: {}", endpoint);
        ExtentReportManager.logInfo("Executing PUT request to: " + endpoint);
        
        Response response = given()
                .spec(requestSpec)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
        
        logResponse(response);
        return response;
    }

    /**
     * Execute PATCH request
     * 
     * @param endpoint API endpoint
     * @return Response
     */
    public Response patch(String endpoint) {
        logger.info("Executing PATCH request to: {}", endpoint);
        ExtentReportManager.logInfo("Executing PATCH request to: " + endpoint);
        
        Response response = given()
                .spec(requestSpec)
                .when()
                .patch(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
        
        logResponse(response);
        return response;
    }

    /**
     * Execute DELETE request
     * 
     * @param endpoint API endpoint
     * @return Response
     */
    public Response delete(String endpoint) {
        logger.info("Executing DELETE request to: {}", endpoint);
        ExtentReportManager.logInfo("Executing DELETE request to: " + endpoint);
        
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
        
        logResponse(response);
        return response;
    }

    private void logResponse(Response response) {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asString());
        
        ExtentReportManager.logInfo("Response Status Code: " + response.getStatusCode());
        
        String responseBody = response.getBody().asString();
        if (responseBody != null && !responseBody.isEmpty()) {
            try {
                // Try to format JSON for better readability in reports
                responseBody = JsonUtil.prettyPrint(responseBody);
            } catch (Exception e) {
                // If formatting fails, use the original response
                logger.warn("Failed to format response JSON", e);
            }
            ExtentReportManager.logInfo("Response Body: " + responseBody);
        }
    }
}
