package com.computer_database.dao;

import com.computer_database.model.Computer;

import java.sql.SQLException;
import java.util.List;

public interface IComputerDao {

    /**
     * @param computer a computer
     * @throws SQLException when Sql problem
     */
    void add(Computer computer) throws SQLException;

    /**
     * @param computer a computer
     * @throws SQLException when Sql problem
     */
    void update(Computer computer) throws SQLException;

    /**
     * @param id identifier
     * @throws SQLException when Sql problem
     */
    void delete(long id) throws SQLException;

    /**
     * @param search search field
     * @return the count of all computers
     * @throws SQLException when Sql problem
     */
    int getCountSearch(String search) throws SQLException;

    /**
     * @param limit  the limit
     * @param offset the offset
     * @param search the like chain to recherche
     * @param order  the order of the list
     * @return a list of computers
     * @throws SQLException when Sql problem
     */
    List<Computer> listAllWithOffsetAndCompanyName(int limit, int offset, String search, String order) throws SQLException;

    /**
     * @param id identifier
     * @return a computer
     * @throws SQLException when Sql problem
     */
    Computer detail(long id) throws SQLException;

    /**
     * @param id id
     * @throws SQLException when Sql problem
     */
    void deleteWhereCompanyId(long id) throws SQLException;
}
