package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.UserRole;

import java.util.Optional;

public interface RoleDao extends Dao<UserRole> {
    Optional<UserRole> findByName(String userRoleName) throws DAOException;
}
