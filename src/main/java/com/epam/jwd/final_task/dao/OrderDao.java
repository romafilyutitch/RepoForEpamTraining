package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Book;
import com.epam.jwd.final_task.model.BookOrder;
import com.epam.jwd.final_task.model.LibraryUser;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends Dao<BookOrder> {
    List<BookOrder> findOrdersByUser(LibraryUser user) throws DAOException;

    List<BookOrder> findOrdersByBook(Book book) throws DAOException;

    List<BookOrder> findOrdersByOrderDate(LocalDate orderDate) throws DAOException;
}
