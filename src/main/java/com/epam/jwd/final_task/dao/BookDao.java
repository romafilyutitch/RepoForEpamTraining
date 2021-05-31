package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Book;

import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> findBooksByName(String name) throws DAOException;

    List<Book> findBooksByAuthorName(String authorName) throws DAOException;

    List<Book> findBooksByGenreName(String genreName) throws DAOException;

    List<Book> findBooksByYear(int year) throws DAOException;
}
