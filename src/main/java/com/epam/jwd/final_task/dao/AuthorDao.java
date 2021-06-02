package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookAuthor;

import java.util.Optional;

public interface AuthorDao extends Dao<BookAuthor>{
    Optional<BookAuthor> getByName(String authorName) throws DAOException;
}
