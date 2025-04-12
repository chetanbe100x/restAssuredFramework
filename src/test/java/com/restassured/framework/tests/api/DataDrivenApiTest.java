package com.restassured.framework.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.framework.client.UserClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Example of data-driven API testing
 */
public class DataDrivenApiTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DataDrivenApiTest.class);
    private UserClient userClient;

    @BeforeClass
    public void init() {
        logger.info("Initializing DataDrivenApiTest");
        userClient = new UserClient();
    }

    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {
        return new Object[][] {
            {"1", true},
            {"2", true},
            {"3", true},
            {"999", false} // Non-existent user
        };
    }

    @Test(dataProvider = "userIds")
    public void testGetUserById(String userId, boolean shouldExist) {
        logger.info("Testing get user by ID: {}, should exist: {}", userId, shouldExist);
        
        Response response = userClient.getUserById(userId);
        
        if (shouldExist) {
            assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
            assertThat(response.jsonPath().getString("id")).isEqualTo(userId);
        } else {
            assertThat(response.getStatusCode()).isEqualTo(StatusCodes.NOT_FOUND);
        }
    }
}
