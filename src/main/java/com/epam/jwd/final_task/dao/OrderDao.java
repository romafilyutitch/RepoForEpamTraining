package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookOrder;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends Dao<BookOrder> {
    List<BookOrder> findOrdersByUserLogin(String userLogin) throws DAOException;

    List<BookOrder> findOrdersByBookName(String bookName) throws DAOException;

    List<BookOrder> findOrdersByOrderDate(LocalDate orderDate) throws DAOException;
}
