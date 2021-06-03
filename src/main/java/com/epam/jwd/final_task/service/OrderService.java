package com.epam.jwd.final_task.service;

import com.epam.jwd.final_task.dao.BookDao;
import com.epam.jwd.final_task.dao.DaoFactory;
import com.epam.jwd.final_task.dao.UserDao;

public class OrderService {
    private static final BookDao BOOK_DAO = DaoFactory.getInstance().getBookDao();
    private static final UserDao USER_DAO = DaoFactory.getInstance().getUserDao();


}
