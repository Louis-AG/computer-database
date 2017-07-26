package com.computer_database.service;

import com.computer_database.dao.ComputerDao;
import com.computer_database.dao.IComputerDao;
import com.computer_database.exception.DataBaseException;
import com.computer_database.model.Computer;
import com.computer_database.model.Page;
import com.computer_database.util.ConnectionManager;

import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * @author lag
 */
public final class ComputerService implements IComputerService {
    private static volatile IComputerService instance;
    private IComputerDao computerDao;
    private ConnectionManager connectionManager;

    /**
     * @param connectionManager connectionManager
     * @param computerDao       computer dao
     */
    private ComputerService(IComputerDao computerDao, ConnectionManager connectionManager) {
        this.computerDao = computerDao;
        this.connectionManager = connectionManager;
    }

    /**
     * @return the instance
     */
    public static IComputerService getInstance() {
        if (instance == null) {
            synchronized (ComputerService.class) {
                if (instance == null) {
                    ComputerService.instance = new ComputerService(ComputerDao.getInstance(), ConnectionManager.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void add(Computer computer) {
        try {
            connectionManager.openConnection();
            computerDao.add(computer);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public void update(Computer computer) {
        try {
            connectionManager.openConnection();
            computerDao.update(computer);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public void delete(long id) {
        try {
            connectionManager.openConnection();
            computerDao.delete(id);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public void deleteMany(String listId) {
        Stream.of(listId.split(","))
                .map(Long::parseLong)
                .forEach(this::delete);
    }

    @Override
    public Computer detail(long id) {
        try {
            connectionManager.openConnection();
            return computerDao.detail(id);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public Page<Computer> listAllWithPagingAndCompanyName(int index, int limit, String search, String order) {
        connectionManager.openConnection();

        int offset = 0;

        Page<Computer> page = new Page<>();

        if (index >= 0) {
            offset = offset + (limit * index);
            int count;
            try {
                connectionManager.openTransaction();
                count = computerDao.getCountSearch(search);

                if (offset <= count) {
                    page.setPageCurrent(index);
                    page.setPageTotal(((count % limit) == 0) ? (count / limit) : ((count / limit) + 1));
                    page.setLimit(limit);
                    page.setDatas(computerDao.listAllWithOffsetAndCompanyName(limit, offset, search, order));

                }
                connectionManager.commit();
            } catch (SQLException e) {
                connectionManager.rollback();
                throw new DataBaseException(e.getMessage());
            } finally {
                connectionManager.closeTransaction();
                connectionManager.closeConnection();
            }
        }
        return page;
    }

    @Override
    public int getCountSearch(String search) {
        try {
            connectionManager.openConnection();
            return computerDao.getCountSearch(search);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            connectionManager.closeConnection();
        }
    }
}