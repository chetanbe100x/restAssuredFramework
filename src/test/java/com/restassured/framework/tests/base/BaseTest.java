package com.restassured.framework.tests.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.restassured.framework.client.RestAssuredClient;
import com.restassured.framework.config.ConfigManager;
import com.restassured.framework.listeners.TestListener;
import com.restassured.framework.reporting.ExtentReportManager;
import com.restassured.framework.utils.LoggerUtil;

import io.restassured.RestAssured;

/**
 * Base test class for all API tests
 */
@Listeners(TestListener.class)
public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected RestAssuredClient apiClient;

    @BeforeSuite
    public void setUp() {
        logger.info("Setting up test suite");
        
        // Initialize configuration
        ConfigManager.getInstance();
        
        // Initialize reporting
        ExtentReportManager.initReports();
        
        // Configure RestAssured
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Initialize API client
        apiClient = new RestAssuredClient();
        
        logger.info("Test suite setup completed");
    }

    @AfterSuite
    public void tearDown() {
        logger.info("Tearing down test suite");
        
        // Flush reports
        ExtentReportManager.flushReports();
        
        logger.info("Test suite teardown completed");
    }
}
