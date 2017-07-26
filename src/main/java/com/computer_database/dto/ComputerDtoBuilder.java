package com.computer_database.dto;

import com.computer_database.model.Company;

import java.time.LocalDate;

public class ComputerDtoBuilder {
    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /**
     * @param id id
     * @return this for builder
     */
    public ComputerDtoBuilder setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @param name name
     * @return this for builder
     */
    public ComputerDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param introduced introduced
     * @return this for builder
     */
    public ComputerDtoBuilder setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
        return this;
    }

    /**
     * @param discontinued discontinued
     * @return this for builder
     */
    public ComputerDtoBuilder setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
        return this;
    }

    /**
     * @param company companyName
     * @return this for builder
     */
    public ComputerDtoBuilder setCompany(Company company) {
        this.company = company;
        return this;
    }

    /**
     * @return builder
     */
    public ComputerDto createComputerDto() {
        return new ComputerDto(id, name, introduced, discontinued, company);
    }
}