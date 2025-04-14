package com.restassured.framework.pojo.user;

/**
 * POJO for address
 */
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    /**
     * Default constructor
     */
    public Address() {
    }

    /**
     * Constructor with all fields
     */
    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    /**
     * Get street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get suite
     */
    public String getSuite() {
        return suite;
    }

    /**
     * Set suite
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * Get city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Set zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Get geo
     */
    public Geo getGeo() {
        return geo;
    }

    /**
     * Set geo
     */
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    /**
     * Builder class for Address
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern implementation for Address
     */
    public static class Builder {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder suite(String suite) {
            this.suite = suite;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Builder geo(Geo geo) {
            this.geo = geo;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.street = this.street;
            address.suite = this.suite;
            address.city = this.city;
            address.zipcode = this.zipcode;
            address.geo = this.geo;
            return address;
        }
    }
}
