package com.epam.jwd.final_task.service;

import com.epam.jwd.final_task.dao.DaoFactory;
import com.epam.jwd.final_task.dao.RoleDao;
import com.epam.jwd.final_task.dao.SubscriptionDao;
import com.epam.jwd.final_task.dao.UserDao;
import com.epam.jwd.final_task.model.User;

public class UserService {
    private static final UserDao USER_DAO = DaoFactory.getInstance().getUserDao();
    private static final RoleDao ROLE_DAO = DaoFactory.getInstance().getRoleDao();
    private static final SubscriptionDao SUBSCRIPTION_DAO = DaoFactory.getInstance().getSubscriptionDao();

    public User create(User user) {
      return null;
    }
}
