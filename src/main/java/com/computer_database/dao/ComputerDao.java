package com.computer_database.dao;

import com.computer_database.model.CompanyBuilder;
import com.computer_database.model.Computer;
import com.computer_database.model.ComputerBuilder;
import com.computer_database.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComputerDao implements IComputerDao {
    private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
    private static volatile ComputerDao instance;
    private DataSourceFactory dataSourceFactory;

    /**
     * @param dataSourceFactory dataSourceFactory
     */
    private ComputerDao(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    /**
     * @return the instance
     */
    public static ComputerDao getInstance() {
        if (instance == null) {
            synchronized (ComputerDao.class) {
                if (instance == null) {
                    instance = new ComputerDao(DataSourceFactory.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void add(Computer computer) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(
                "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES( ?, ?, ?, ?)")) {

            preparedStatement.setString(1, computer.getName());

            if (computer.getIntroduced() != null) {
                preparedStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
            } else {
                preparedStatement.setNull(2, Types.DATE);
            }

            if (computer.getDiscontinued() != null) {
                preparedStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }

            if (computer.getCompany().getId() != 0) {
                preparedStatement.setLong(4, computer.getCompany().getId());
            } else {
                preparedStatement.setNull(4, Types.BIGINT);
            }

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Computer computer) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(
                "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ;")) {

            preparedStatement.setString(1, computer.getName());

            if (computer.getIntroduced() != null) {
                preparedStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
            } else {
                preparedStatement.setDate(2, null);
            }

            if (computer.getDiscontinued() != null) {
                preparedStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }

            if (computer.getCompany().getId() != 0) {
                preparedStatement.setLong(4, computer.getCompany().getId());
            } else {
                preparedStatement.setNull(4, Types.BIGINT);
            }
            preparedStatement.setLong(5, computer.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("DELETE FROM computer WHERE id=?;")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public int getCountSearch(String search) throws SQLException {
        ResultSet resultSet;
        int count = 0;

        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(
                "SELECT count(*) AS count FROM computer LEFT JOIN company on computer.company_id = company.id " +
                        " WHERE computer.name LIKE ? or company.name LIKE ? ;")) {

            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                count = resultSet.getInt("count");
            }
        }
        return count;
    }

    @Override
    public List<Computer> listAllWithOffsetAndCompanyName(int limit, int offset, String search, String order) throws SQLException {

        List<Computer> computers = new ArrayList<>();
        ResultSet resultSet;


        if (order.equals("")) {
            order = "computer.name";
        }

        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection()
                .prepareStatement("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name " +
                        "FROM computer LEFT JOIN company on computer.company_id = company.id " +
                        "WHERE computer.name LIKE ? " +
                        "or company.name LIKE ? " +
                        "ORDER By " + order + " " +
                        "LIMIT ? " +
                        "OFFSET ? " +
                        ";")) {

            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");

            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, offset);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                LocalDate introduced = null;
                if (resultSet.getDate("introduced") != null) {
                    introduced = resultSet.getDate("introduced").toLocalDate();
                }
                LocalDate discontinued = null;
                if (resultSet.getDate("discontinued") != null) {
                    discontinued = resultSet.getDate("discontinued").toLocalDate();
                }
                long companyId = resultSet.getLong("company_id");

                String companyName = resultSet.getString("company.name");

                Computer computer = new ComputerBuilder().setId(id)
                        .setName(name).setIntroduced(introduced)
                        .setDiscontinued(discontinued)
                        .setCompany(new CompanyBuilder().setId(companyId).setName(companyName).createCompany())
                        .createComputer();

                computers.add(computer);
            }
        }
        return computers;
    }

    @Override
    public Computer detail(long id) throws SQLException {
        Computer computer = new ComputerBuilder().createComputer();
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name " +
                "FROM computer LEFT JOIN company on computer.company_id = company.id " +
                "WHERE computer.id=?;")) {

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");

                LocalDate introduced = null;
                if (resultSet.getDate("introduced") != null) {
                    introduced = resultSet.getDate("introduced").toLocalDate();
                }
                LocalDate discontinued = null;
                if (resultSet.getDate("discontinued") != null) {
                    discontinued = resultSet.getDate("discontinued").toLocalDate();
                }
                long companyId = resultSet.getLong("company_id");
                String companyName = resultSet.getString("company.name");

                computer = new ComputerBuilder().setId(id)
                        .setName(name)
                        .setIntroduced(introduced)
                        .setDiscontinued(discontinued)
                        .setCompany(new CompanyBuilder().setId(companyId).setName(companyName).createCompany())
                        .createComputer();
            }
        }
        return computer;
    }

    @Override
    public void deleteWhereCompanyId(long id) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement("DELETE FROM computer WHERE company_id=?;")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
