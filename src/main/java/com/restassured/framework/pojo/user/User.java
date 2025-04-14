package com.restassured.framework.pojo.user;

/**
 * POJO for user
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String website;
    private Address address;
    private Company company;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor with all fields
     */
    public User(String id, String name, String email, String phone, String username, String website, Address address, Company company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.website = website;
        this.address = address;
        this.company = company;
    }

    /**
     * Get id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Set company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Builder class for User
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern implementation for User
     */
    public static class Builder {
        private String id;
        private String name;
        private String email;
        private String phone;
        private String username;
        private String website;
        private Address address;
        private Company company;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.name = this.name;
            user.email = this.email;
            user.phone = this.phone;
            user.username = this.username;
            user.website = this.website;
            user.address = this.address;
            user.company = this.company;
            return user;
        }
    }
}
