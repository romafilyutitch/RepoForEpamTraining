package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.model.Subscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SubscriptionDaoService implements Dao<Subscription> {
    private static final String TABLE_NAME = "reader_subscription";
    private static final String ID_COLUMN = "id";
    private static final String START_DATE_COLUMN = "start_date";
    private static final String END_DATE_COLUMN = "end_date";
    private static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    private static final String SAVE_PREPARED_SQL ="INSERT INTO ? (?, ?) VALUES(?,?)";
    private static final String FIND_ALL_SQL = "SELECT ?, ?, ? FROM ?";
    private static final String UPDATE_PREPARED_SQL = "UPDATE ? SET ? = ?, ? = ? WHERE ? = ?";

    @Override
    public Subscription save(Subscription entity) {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, TABLE_NAME);
            saveStatement.setString(2, START_DATE_COLUMN);
            saveStatement.setString(3, END_DATE_COLUMN);
            saveStatement.setDate(4, Date.valueOf(entity.getStartDate()));
            saveStatement.setDate(5, Date.valueOf(entity.getEndDate()));
            ResultSet resultSet = saveStatement.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(GENERATED_KEY_COLUMN);
            Subscription subscription = new Subscription(id, entity.getStartDate(), entity.getEndDate());
            return subscription;
        } catch (SQLException e) {
            System.out.println("Save subscription failed");
            return entity;
        }
    }

    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
        PreparedStatement findAllStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            findAllStatement.setString(1, ID_COLUMN);
            findAllStatement.setString(2, START_DATE_COLUMN);
            findAllStatement.setString(3, END_DATE_COLUMN);
            try (ResultSet resultSet = findAllStatement.executeQuery()) {
                while (resultSet.next()) {
                    Subscription subscription = new Subscription(resultSet.getLong(ID_COLUMN),
                            resultSet.getDate(START_DATE_COLUMN).toLocalDate(),
                            resultSet.getDate(END_DATE_COLUMN).toLocalDate());
                    subscriptions.add(subscription);
                }
                return subscriptions;
            }
        } catch (SQLException e) {
            System.out.println("Subscription find failed");
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return findAll().stream().filter(subscription -> subscription.getId().equals(id)).findAny();
    }

    @Override
    public Subscription update(Subscription entity) {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setString(1, TABLE_NAME);
            updateStatement.setString(2, START_DATE_COLUMN);
            updateStatement.setDate(3, Date.valueOf(entity.getStartDate()));
            updateStatement.setString(4, END_DATE_COLUMN);
            updateStatement.setDate(5, Date.valueOf(entity.getEndDate()));
            updateStatement.setString(6, ID_COLUMN);
            updateStatement.setLong(7, entity.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update subscription is failed");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {

    }
}
