package com.restassured.framework.pojo.user;

/**
 * POJO for geo coordinates
 */
public class Geo {
    private String lat;
    private String lng;

    /**
     * Default constructor
     */
    public Geo() {
    }

    /**
     * Constructor with all fields
     */
    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Get latitude
     */
    public String getLat() {
        return lat;
    }

    /**
     * Set latitude
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Get longitude
     */
    public String getLng() {
        return lng;
    }

    /**
     * Set longitude
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * Builder class for Geo
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern implementation for Geo
     */
    public static class Builder {
        private String lat;
        private String lng;

        public Builder lat(String lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(String lng) {
            this.lng = lng;
            return this;
        }

        public Geo build() {
            Geo geo = new Geo();
            geo.lat = this.lat;
            geo.lng = this.lng;
            return geo;
        }
    }
}
