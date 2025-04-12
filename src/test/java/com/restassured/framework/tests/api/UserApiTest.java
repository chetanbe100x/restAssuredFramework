package com.restassured.framework.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.framework.client.UserClient;
import com.restassured.framework.constants.StatusCodes;
import com.restassured.framework.pojo.user.Address;
import com.restassured.framework.pojo.user.Company;
import com.restassured.framework.pojo.user.Geo;
import com.restassured.framework.pojo.user.User;
import com.restassured.framework.tests.base.BaseTest;

import io.restassured.response.Response;

/**
 * Test class for User API endpoints
 */
public class UserApiTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserApiTest.class);
    private UserClient userClient;
    private String userId;

    @BeforeClass
    public void init() {
        logger.info("Initializing UserApiTest");
        userClient = new UserClient();
        
        // For demonstration purposes, we'll use a mock token
        // In a real scenario, you would authenticate first
        userClient.setAuthToken("mock-jwt-token");
    }

    @Test(priority = 1)
    public void testGetAllUsers() {
        logger.info("Testing get all users API");
        
        Response response = userClient.getAllUsers();
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getList("")).isNotEmpty();
    }

    @Test(priority = 2)
    public void testCreateUser() {
        logger.info("Testing create user API");
        
        // Create user object
        User user = createTestUser();
        
        Response response = userClient.createUser(user);
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.CREATED);
        
        // Extract user ID for later tests
        userId = response.jsonPath().getString("id");
        assertThat(userId).isNotEmpty();
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testGetUserById() {
        logger.info("Testing get user by ID API");
        
        Response response = userClient.getUserById(userId);
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("id")).isEqualTo(userId);
        assertThat(response.jsonPath().getString("name")).isEqualTo("John Doe");
    }

    @Test(priority = 4, dependsOnMethods = "testGetUserById")
    public void testUpdateUser() {
        logger.info("Testing update user API");
        
        // Update user object
        User updatedUser = createTestUser();
        updatedUser.setName("Jane Doe");
        
        Response response = userClient.updateUser(userId, updatedUser);
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
        assertThat(response.jsonPath().getString("name")).isEqualTo("Jane Doe");
    }

    @Test(priority = 5, dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        logger.info("Testing delete user API");
        
        Response response = userClient.deleteUser(userId);
        
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK);
    }

    private User createTestUser() {
        Geo geo = Geo.builder()
                .lat("40.7128")
                .lng("-74.0060")
                .build();
        
        Address address = Address.builder()
                .street("123 Main St")
                .suite("Apt 4B")
                .city("New York")
                .zipcode("10001")
                .geo(geo)
                .build();
        
        Company company = Company.builder()
                .name("Test Company")
                .catchPhrase("Testing is fun")
                .bs("quality assurance")
                .build();
        
        return User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("123-456-7890")
                .username("johndoe")
                .website("johndoe.com")
                .address(address)
                .company(company)
                .build();
    }
}
