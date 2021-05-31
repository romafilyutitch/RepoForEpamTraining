package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.LibraryUser;
import com.epam.jwd.final_task.model.Subscription;
import com.epam.jwd.final_task.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDaoService extends AbstractDao<LibraryUser> implements UserDao {
    private final SubscriptionDaoService subscriptionDaoService = new SubscriptionDaoService();
    private final UserRoleDaoService roleDaoService = new UserRoleDaoService();

    private static final String ID_COLUMN = "id";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ROLE_COLUMN = "role";
    private static final String SUBSCRIPTION_COLUMN = "subscription";
    private static final String SAVE_PREPARED_SQL = "insert into lib_user (login, password, role, subscription) values (?, ?, ?, ?) ";
    private static final String FIND_ALL_SQL = "select id, login, password, role, subscription from lib_user";
    private static final String UPDATE_PREPARED_SQL = "update lib_user set login = ?, password = ?, role = ?, subscription = ? where id = ?";
    private static final String DELETE_PREPARED_SQL = "delete from lib_user where id = ?";

    public UserDaoService() {
        super(FIND_ALL_SQL, SAVE_PREPARED_SQL, UPDATE_PREPARED_SQL, DELETE_PREPARED_SQL);
    }
    @Override
    protected LibraryUser mapResultSet(ResultSet result) throws SQLException, DAOException {
        final Optional<UserRole> optionalUserRole = roleDaoService.findById(result.getLong(ROLE_COLUMN));
        final Optional<Subscription> optionalSubscription = subscriptionDaoService.findById(result.getLong(SUBSCRIPTION_COLUMN));
        final Long id = result.getLong(ID_COLUMN);
        final String login = result.getString(LOGIN_COLUMN);
        final String password = result.getString(PASSWORD_COLUMN);
        final LibraryUser userFromResultSet = new LibraryUser(login, password, optionalUserRole.orElseThrow(DAOException::new), optionalSubscription.orElseThrow(DAOException::new));
        userFromResultSet.setId(id);
        return userFromResultSet;
    }

    @Override
    protected void setSavePrepareStatementValues(LibraryUser entity, PreparedStatement savePreparedStatement) throws SQLException, DAOException {
        savePreparedStatement.setString(1, entity.getLogin());
        savePreparedStatement.setString(2, entity.getPassword());
        savePreparedStatement.setLong(3, entity.getRole().getId());
        if (entity.getUserSubscription() != null) {
            subscriptionDaoService.save(entity.getUserSubscription());
            savePreparedStatement.setLong(4, entity.getUserSubscription().getId());
        } else {
            savePreparedStatement.setNull(4, Types.INTEGER);
        }
    }

    @Override
    protected void setUpdatePreparedStatementValues(LibraryUser entity, PreparedStatement updatePreparedStatement) throws SQLException {
        updatePreparedStatement.setString(1, entity.getLogin());
        updatePreparedStatement.setString(2, entity.getPassword());
        updatePreparedStatement.setLong(3, entity.getRole().getId());
        updatePreparedStatement.setLong(4, entity.getUserSubscription().getId());
        updatePreparedStatement.setLong(5, entity.getId());
    }

    @Override
    public Optional<LibraryUser> findUserByLogin(String login) throws DAOException {
        return findAll().stream().filter(libraryUser -> libraryUser.getLogin().equals(login)).findAny();
    }

    @Override
    public List<LibraryUser> findUsersByRole(UserRole role) throws DAOException {
        return findAll().stream().filter(libraryUser -> libraryUser.getRole().equals(role)).collect(Collectors.toList());
    }

    @Override
    protected Optional<LibraryUser> findSaved(LibraryUser user) throws DAOException {
        return findAll().stream().filter(userFromTable -> userFromTable.getLogin().equalsIgnoreCase(user.getLogin())).findAny();
    }
}
