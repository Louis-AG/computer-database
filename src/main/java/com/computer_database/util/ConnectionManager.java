package com.computer_database.util;

import com.computer_database.dao.DataSourceFactory;
import com.computer_database.exception.DataBaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lag on 12/07/17.
 */
public class ConnectionManager {
    private static volatile ConnectionManager instance;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private DataSource dataSource;


    /**
     * @param dataSource dataSource
     */
    private ConnectionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the instance
     */
    public static ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
                    ConnectionManager.instance = new ConnectionManager(dataSourceFactory.getDataSource());
                }
            }
        }
        return instance;
    }


    public Connection getConnection() {
        return threadLocal.get();
    }

    /**
     * Connect.
     */
    public void openConnection() {
        try {
            threadLocal.set(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Open transaction.
     */
    public void openTransaction() {
        try {
            threadLocal.get().setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Commit connection.
     */
    public void commit() {
        try {
            threadLocal.get().commit();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Rollback connection.
     */
    public void rollback() {
        try {
            threadLocal.get().rollback();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Close transaction.
     */
    public void closeTransaction() {
        try {
            threadLocal.get().setAutoCommit(true);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            threadLocal.get().close();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


}
