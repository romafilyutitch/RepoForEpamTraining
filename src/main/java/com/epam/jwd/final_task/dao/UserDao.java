package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.model.User;
import com.epam.jwd.final_task.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLogin(String login) throws DAOException;

    List<User> findUsersByRole(UserRole role) throws DAOException;

}
