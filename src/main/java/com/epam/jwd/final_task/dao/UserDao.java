package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.model.LibraryUser;
import com.epam.jwd.final_task.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<LibraryUser> {
    Optional<LibraryUser> findUserByLogin(String login);

    List<LibraryUser> findUsersByRole(UserRole role);

}
