package com.computer_database.dao;

import com.computer_database.model.Company;

import java.sql.SQLException;
import java.util.List;

/**
 * @author lag
 */
public interface ICompanyDao {

    /**
     * @return a list with all companies
     * @throws SQLException when Sql problem
     */
    List<Company> listAll() throws SQLException;

    /**
     * @param id identifier
     * @return a company detail
     * @throws SQLException when Sql problem
     */
    Company detail(long id) throws SQLException;

    /**
     * @param id id
     * @throws SQLException when Sql problem
     */
    void delete(long id) throws SQLException;
}
