package com.epam.jwd.final_task.service;

import com.epam.jwd.final_task.dao.AuthorDao;
import com.epam.jwd.final_task.dao.BookDao;
import com.epam.jwd.final_task.dao.DaoFactory;
import com.epam.jwd.final_task.dao.GenreDao;

public class BookService {
    private static final AuthorDao AUTHOR_DAO = DaoFactory.getInstance().getAuthorDao();
    private static final GenreDao GENRE_DAO = DaoFactory.getInstance().getGenreDao();
    private static final BookDao BOOK_DAO = DaoFactory.getInstance().getBookDao();


}
