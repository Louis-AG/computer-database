package com.computer_database.model;

import java.time.LocalDate;

public class ComputerBuilder {
    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /**
     * @param id id
     * @return this for builder
     */
    public ComputerBuilder setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @param name name
     * @return this for builder
     */
    public ComputerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param introduced introduced
     * @return this for builder
     */
    public ComputerBuilder setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
        return this;
    }

    /**
     * @param discontinued discontinued
     * @return this for builder
     */
    public ComputerBuilder setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
        return this;
    }

    /**
     * @param company company object
     * @return this for builder
     */
    public ComputerBuilder setCompany(Company company) {
        this.company = company;
        return this;
    }

    /**
     * @return builder for computer
     */
    public Computer createComputer() {
        return new Computer(id, name, introduced, discontinued, company);
    }
}