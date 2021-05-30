package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.LibraryUser;
import com.epam.jwd.final_task.model.Subscription;
import com.epam.jwd.final_task.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDaoService implements UserDao {
    private final SubscriptionDaoService subscriptionDaoService = new SubscriptionDaoService();
    private final UserRoleDaoService roleDaoService = new UserRoleDaoService();

    private static final String ID_COLUMN = "id";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ROLE_COLUMN = "role";
    private static final String SUBSCRIPTION_COLUMN = "subscription";
    private static final String GENERATED_KEY_COLUMN = "GENERATED_KEY";
    private static final String SAVE_PREPARED_SQL = "insert into lib_user (login, password, role, subscription) values (?, ?, ?, ?) ";
    private static final String FIND_ALL_SQL = "select id, login, password, role, subscription from lib_user";
    private static final String UPDATE_PREPARED_SQL = "update lib_user set login = ?, password = ?, role = ?, subscription = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from lib_user where id = ?";

    @Override
    public LibraryUser save(LibraryUser entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_PREPARED_SQL, Statement.RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, entity.getLogin());
            saveStatement.setString(2, entity.getPassword());
            saveStatement.setLong(3, entity.getRole().getId());
            Subscription savedSubscription = null;
            if (entity.getUserSubscription() != null) {
                savedSubscription = subscriptionDaoService.save(entity.getUserSubscription());
                saveStatement.setLong(4, savedSubscription.getId());
            } else {
                saveStatement.setNull(4, Types.NULL);
            }
            saveStatement.executeUpdate();
            ResultSet generatedKeys = saveStatement.getGeneratedKeys();
            generatedKeys.next();
            Long key = generatedKeys.getLong(GENERATED_KEY_COLUMN);
            return new LibraryUser(key, entity.getLogin(), entity.getPassword(), entity.getRole(), savedSubscription);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<LibraryUser> findAll() throws DAOException {
        List<LibraryUser> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             Statement findAllStatement = connection.createStatement();
             ResultSet resultSet = findAllStatement.executeQuery(FIND_ALL_SQL);) {
            while (resultSet.next()) {
                Optional<Subscription> optionalSubscription = subscriptionDaoService.findById(resultSet.getLong(SUBSCRIPTION_COLUMN));
                Optional<UserRole> optionalUserRole = roleDaoService.findById(resultSet.getLong(ROLE_COLUMN));
                final LibraryUser user = new LibraryUser(resultSet.getLong(ID_COLUMN),
                        resultSet.getString(LOGIN_COLUMN),
                        resultSet.getString(PASSWORD_COLUMN),
                        optionalUserRole.orElseThrow(DAOException::new),
                        optionalSubscription.orElse(null));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public Optional<LibraryUser> findById(Long id) throws DAOException {
        return findAll().stream().filter(libraryUser -> libraryUser.getId().equals(id)).findAny();
    }

    @Override
    public LibraryUser update(LibraryUser entity) throws DAOException {
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PREPARED_SQL)) {
            updateStatement.setString(1, entity.getLogin());
            updateStatement.setString(2, entity.getPassword());
            updateStatement.setLong(3, entity.getRole().getId());
            if (entity.getUserSubscription() == null) {
                updateStatement.setNull(4, Types.NULL);
            } else {
                updateStatement.setLong(4, entity.getUserSubscription().getId());
            }
            updateStatement.setLong(5, entity.getId());
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

    @Override
    public Optional<LibraryUser> findUserByLogin(String login) throws DAOException {
        return findAll().stream().filter(libraryUser -> libraryUser.getLogin().equals(login)).findAny();
    }

    @Override
    public List<LibraryUser> findUsersByRole(UserRole role) throws DAOException {
        return findAll().stream().filter(libraryUser -> libraryUser.getRole().equals(role)).collect(Collectors.toList());
    }
}
