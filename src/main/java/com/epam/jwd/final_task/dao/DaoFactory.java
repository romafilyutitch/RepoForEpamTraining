package com.epam.jwd.final_task.dao;

public interface DaoFactory {

    UserDao getUserDao();

    BookDao getBookDao();

    OrderDao getOrderDao();

    AuthorDao getAuthorDao();

    GenreDao getGenreDao();

    RoleDao getRoleDao();

    SubscriptionDao getSubscriptionDao();

    static DaoFactory getInstance() {
        return MySQLDaoFactory.getInstance();
    }
}
