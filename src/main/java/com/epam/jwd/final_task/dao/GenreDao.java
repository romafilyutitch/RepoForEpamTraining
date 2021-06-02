package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookGenre;

import java.util.Optional;

public interface GenreDao extends Dao<BookGenre> {
    Optional<BookGenre> getByName(String genreName) throws DAOException;
}
