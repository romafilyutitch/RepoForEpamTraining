package com.epam.jwd.final_task.app;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.dao.GenreDao;
import com.epam.jwd.final_task.dao.MySQLGenreDao;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookGenre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ConnectionPoolInitializationException, InterruptedException, DAOException {
        logger.trace("App is started");
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        pool.init();
        GenreDao genreDao = MySQLGenreDao.getInstance();
        genreDao.save(new BookGenre("tutorials"));
        pool.destroy();
        logger.trace("App is end");
    }
}
