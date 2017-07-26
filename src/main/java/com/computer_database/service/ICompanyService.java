package com.computer_database.service;

import com.computer_database.model.Company;

import java.util.List;

public interface ICompanyService {
    /**
     * @return list of Companies
     */
    List<Company> listAll();

    /**
     * @param id id
     * @return Company detail
     */
    Company detail(long id);

    /**
     * @param id id
     */
    void deleteCompanyAndComputers(long id);
}
