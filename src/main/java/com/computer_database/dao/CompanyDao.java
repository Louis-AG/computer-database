package com.computer_database.dao;

import com.computer_database.model.Company;
import com.computer_database.model.CompanyBuilder;
import com.computer_database.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements ICompanyDao {
    private static Logger logger = LoggerFactory.getLogger(CompanyDao.class);
    private static volatile CompanyDao instance;
    private DataSourceFactory dataSourceFactory;

    /**
     * @param dataSourceFactory dataSourceFactory
     */
    private CompanyDao(DataSourceFactory dataSourceFactory) {
        super();
        this.dataSourceFactory = dataSourceFactory;
    }

    /**
     * @return the instance
     */
    public static CompanyDao getInstance() {
        if (instance == null) {
            synchronized (CompanyDao.class) {
                if (instance == null) {
                    instance = new CompanyDao(DataSourceFactory.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public List<Company> listAll() throws SQLException {

        List<Company> companies = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("SELECT id, name FROM company;")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company = new CompanyBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("name")).createCompany();
                companies.add(company);
            }
        }
        return companies;
    }

    @Override
    public Company detail(long id) throws SQLException {
        Company company = new CompanyBuilder().createCompany();
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("SELECT id, name FROM company WHERE id=?;")) {

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                company = new CompanyBuilder().setId(id).setName(name).createCompany();
            }
        }
        return company;
    }

    @Override
    public void delete(long id) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("DELETE FROM company WHERE id=?;")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
