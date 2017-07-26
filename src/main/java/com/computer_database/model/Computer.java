package com.computer_database.model;

import java.time.LocalDate;

public class Computer {

    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /**
     * Constructor.
     */
    public Computer() {
        super();
    }

    /**
     * @param id           identifier
     * @param name         name
     * @param introduced   localDate for introduction in use
     * @param discontinued localDate for discontinution of use
     * @param company      link to the company identifier
     */
    public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Computer)) {
            return false;
        }

        Computer computer = (Computer) o;

        if (id != computer.id) {
            return false;
        }
        if (!name.equals(computer.name)) {
            return false;
        }
        if (introduced != null ? !introduced.equals(computer.introduced) : computer.introduced != null) {
            return false;
        }
        if (discontinued != null ? !discontinued.equals(computer.discontinued) : computer.discontinued != null) {
            return false;
        }
        return company != null ? company.equals(computer.company) : computer.company == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced=" + introduced +
                ", discontinued=" + discontinued +
                ", company=" + company +
                '}';
    }
}
