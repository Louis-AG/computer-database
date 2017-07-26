package com.computer_database.service;

import com.computer_database.dao.CompanyDao;
import com.computer_database.dao.ComputerDao;
import com.computer_database.dao.ICompanyDao;
import com.computer_database.dao.IComputerDao;
import com.computer_database.exception.DataBaseException;
import com.computer_database.model.Company;
import com.computer_database.util.ConnectionManager;

import java.sql.SQLException;
import java.util.List;

public class CompanyService implements ICompanyService {
    private static volatile ICompanyService instance;
    private ICompanyDao companyDao;
    private IComputerDao computerDao;
    private ConnectionManager connectionManager;


    /**
     * @param connectionManager connectionManager
     * @param companyDao        company dao
     * @param computerDao       computer dao
     */
    private CompanyService(ICompanyDao companyDao, IComputerDao computerDao, ConnectionManager connectionManager) {
        this.companyDao = companyDao;
        this.computerDao = computerDao;
        this.connectionManager = connectionManager;
    }

    /**
     * @return the instance
     */
    public static ICompanyService getInstance() {
        if (instance == null) {
            synchronized (CompanyService.class) {
                if (instance == null) {
                    CompanyService.instance = new CompanyService(
                            CompanyDao.getInstance(),
                            ComputerDao.getInstance(),
                            ConnectionManager.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public List<Company> listAll() {
        try {
            connectionManager.openConnection();
            return companyDao.listAll();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public Company detail(long id) {
        try {
            connectionManager.openConnection();
            return companyDao.detail(id);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }


    @Override
    public void deleteCompanyAndComputers(long id) {
        connectionManager.openConnection();

        try {
            connectionManager.openTransaction();

            computerDao.deleteWhereCompanyId(id);
            companyDao.delete(id);

            connectionManager.commit();

        } catch (SQLException e) {
            connectionManager.rollback();
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeTransaction();
            connectionManager.closeConnection();
        }
    }
}
