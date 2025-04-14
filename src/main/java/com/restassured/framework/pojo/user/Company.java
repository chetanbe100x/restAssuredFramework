package com.restassured.framework.pojo.user;

/**
 * POJO for company
 */
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    /**
     * Default constructor
     */
    public Company() {
    }

    /**
     * Constructor with all fields
     */
    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
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
     * Get catchPhrase
     */
    public String getCatchPhrase() {
        return catchPhrase;
    }

    /**
     * Set catchPhrase
     */
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    /**
     * Get bs
     */
    public String getBs() {
        return bs;
    }

    /**
     * Set bs
     */
    public void setBs(String bs) {
        this.bs = bs;
    }

    /**
     * Builder class for Company
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern implementation for Company
     */
    public static class Builder {
        private String name;
        private String catchPhrase;
        private String bs;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder catchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
            return this;
        }

        public Builder bs(String bs) {
            this.bs = bs;
            return this;
        }

        public Company build() {
            Company company = new Company();
            company.name = this.name;
            company.catchPhrase = this.catchPhrase;
            company.bs = this.bs;
            return company;
        }
    }
}
