package com.epam.jwd.final_task.dao;

public interface DaoFactory {
    UserDao getUserDao();

    BookDao getBookDao();

    OrderDao getOrderDao();

    static DaoFactory getInstance() {
        return new MySQLDaoFactory();
    }
}
