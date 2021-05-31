package com.epam.jwd.final_task.dao;

public class MySQLDaoFactory implements DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserDaoService();
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoService();
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoService();
    }
}
