package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDaoService extends AbstractDao<UserRole> {
    private static final String FIND_ALL_SQL = "select id, role from user_role";
    public static final String ROLE_COLUMN = "role";

    public UserRoleDaoService() {
        super(FIND_ALL_SQL);
    }

    @Override
    protected UserRole mapResultSet(ResultSet result) throws SQLException {
        return UserRole.valueOf(result.getString(ROLE_COLUMN).toUpperCase());
    }

    @Override
    protected void setSavePrepareStatementValues(UserRole entity, PreparedStatement savePreparedStatement) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdatePreparedStatementValues(UserRole entity, PreparedStatement updatePreparedStatement) {
        throw new UnsupportedOperationException();
    }
}
