package com.computer_database.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);
    private static volatile DataSourceFactory instance;
    private DataSource dataSource;

    /**
     * @param dataSource dataSource
     */
    private DataSourceFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the instance
     */
    public static DataSourceFactory getInstance() {
        if (instance == null) {
            synchronized (DataSourceFactory.class) {
                if (instance == null) {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        logger.error("Failed to getInstance with Class.forName in DataSourceFactory !!!");
                    }

                    String configFile = "/db.properties";
                    HikariConfig cfg = new HikariConfig(configFile);
                    HikariDataSource ds = new HikariDataSource(cfg);

                    instance = new DataSourceFactory(ds);
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
