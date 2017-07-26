package com.computer_database.service;

import com.computer_database.model.Computer;
import com.computer_database.model.Page;

public interface IComputerService {

    /**
     * @param computer computer
     */
    void add(Computer computer);

    /**
     * @param computer computer
     */
    void update(Computer computer);

    /**
     * @param id identifer
     */
    void delete(long id);

    /**
     * @param listId list of identifer
     */
    void deleteMany(String listId);

    /**
     * @param index  indentifer
     * @param limit  limit
     * @param search the like chain to recherche
     * @param order  the order of the list
     * @return pageDto
     */
    Page<Computer> listAllWithPagingAndCompanyName(int index, int limit, String search, String order);

    /**
     * @param search the like chain to recherche
     * @return count of computers
     */
    int getCountSearch(String search);

    /**
     * @param id identifer
     * @return computer
     */
    Computer detail(long id);
}
