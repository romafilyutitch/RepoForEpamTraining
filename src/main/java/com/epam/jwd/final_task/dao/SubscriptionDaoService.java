package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Subscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionDaoService implements Dao<Subscription> {

    private static final String SAVE_PREPARED_SQL = "insert into reader_subscription (start_date, end_date) values (?, ?)";
    private static final String FIND_ALL_SQL = "select id, start_date, end_date from reader_subscription";
    private static final String UPDATE_PREPARED_SQL = "update reader_subscription set start_date = ?, end_date = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from reader_subscription where id = ?";
    public static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    public static final String ID_COLUMN = "id";
    public static final String START_DATE_COLUMN = "start_date";
    public static final String END_DATE_COLUMN = "end_date";

    @Override
    public Subscription save(Subscription entity) throws DAOException {
        Optional<Subscription> optionalSubscription = findAll().stream().filter(subscription -> subscription.getStartDate().equals(entity.getStartDate()) && subscription.getEndDate().equals(entity.getEndDate())).findAny();
        if (optionalSubscription.isPresent()) {
            return optionalSubscription.get();
        }
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setDate(1, Date.valueOf(entity.getStartDate()));
            saveStatement.setDate(2, Date.valueOf(entity.getEndDate()));
            saveStatement.executeUpdate();
            ResultSet resultSet = saveStatement.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(GENERATED_KEY_COLUMN);
            return new Subscription(id, entity.getStartDate(), entity.getEndDate());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Subscription> findAll() throws DAOException {
        List<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             Statement findAllStatement = connection.createStatement();
             ResultSet resultSet = findAllStatement.executeQuery(FIND_ALL_SQL)) {
            while (resultSet.next()) {
                Subscription subscription = new Subscription(resultSet.getLong(ID_COLUMN),
                        resultSet.getDate(START_DATE_COLUMN).toLocalDate(),
                        resultSet.getDate(END_DATE_COLUMN).toLocalDate());
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return subscriptions;
    }

    @Override
    public Optional<Subscription> findById(Long id) throws DAOException {
        return findAll().stream().filter(subscription -> subscription.getId().equals(id)).findAny();
    }

    @Override
    public Subscription update(Subscription entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setDate(1, Date.valueOf(entity.getStartDate()));
            updateStatement.setDate(2, Date.valueOf(entity.getEndDate()));
            updateStatement.setString(3, ID_COLUMN);
            updateStatement.setLong(4, entity.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entity;
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_PREPARED_SQL)) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
