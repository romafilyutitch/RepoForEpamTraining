package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRoleDaoService implements Dao<UserRole> {
    private static final String FIND_ALL_SQL = "select id, role from user_role";
    public static final String ROLE_COLUMN = "role";

    @Override
    public UserRole save(UserRole entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserRole> findAll() throws DAOException {
        List<UserRole> roles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().takeFreeConnection();
             Statement findAllStatement = connection.createStatement();
             ResultSet findAllResultSet = findAllStatement.executeQuery(FIND_ALL_SQL)) {
            while (findAllResultSet.next()) {
                UserRole role = UserRole.valueOf(findAllResultSet.getString(ROLE_COLUMN).toUpperCase());
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return roles;
    }

    @Override
    public Optional<UserRole> findById(Long id) throws DAOException{
        return findAll().stream().filter(userRole -> userRole.getId().equals(id)).findAny();
    }

    @Override
    public UserRole update(UserRole entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
