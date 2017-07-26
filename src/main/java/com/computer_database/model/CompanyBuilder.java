package com.computer_database.model;

public class CompanyBuilder {
    private long id;
    private String name;

    /**
     * @param id identifer
     * @return this for builder
     */
    public CompanyBuilder setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @param name name
     * @return this for builder
     */
    public CompanyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return builder
     */
    public Company createCompany() {
        return new Company(id, name);
    }
}