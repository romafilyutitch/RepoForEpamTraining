package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookGenre;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MySQLGenreDaoTest {
    private static ConnectionPool pool = ConnectionPool.getConnectionPool();
    private MySQLGenreDao testDao = MySQLGenreDao.getInstance();

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Test
    public void save_mustAssignIdToSavedBookGenre() throws DAOException {
        BookGenre bookGenre = new BookGenre("Drama");
        bookGenre = testDao.save(bookGenre);
        Assert.assertNotNull("Saved book genre must be not null", bookGenre);
        Assert.assertNotNull("Saved book genre id must be not null", bookGenre.getId());
    }

    @Test
    public void findAll() throws DAOException {
        final List<BookGenre> all = testDao.findAll();
        Assert.assertNotNull("List of book genres must be not null", all);
    }

    @Test
    public void findById() throws DAOException {
        BookGenre bookGenre = new BookGenre("Drama");
        bookGenre = testDao.save(bookGenre);
        Optional<BookGenre> optionalBookGenre = testDao.findById(bookGenre.getId());
        if (optionalBookGenre.isEmpty()) {
            Assert.fail("optional saved book genre must be not empty");
        }
        BookGenre foundBookGenre = optionalBookGenre.get();
        Assert.assertEquals("saved book genre must be equals to found book genre by id", bookGenre, foundBookGenre);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.update(new BookGenre("sadasdsd"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() throws DAOException {
        testDao.delete(new Random().nextLong());
    }
}