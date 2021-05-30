package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends Dao<Book> {
    Optional<Book> findBookByName(String name);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByGenre(String genre);

    List<Book> findBooksByYear(int year);
}
