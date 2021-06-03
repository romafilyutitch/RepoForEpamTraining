package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookAuthor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class MySQLAuthorDaoTest {
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();
    private final MySQLAuthorDao testDao = MySQLAuthorDao.getInstance();
    private BookAuthor testAuthor = new BookAuthor("James");

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Before
    public void saveTestAuthor() throws DAOException {
        testAuthor = testDao.save(testAuthor);
    }

    @Test
    public void save() {
        Assert.assertNotNull(testAuthor);
        Assert.assertNotNull(testAuthor.getId());
    }

    @Test
    public void findAll() throws DAOException {
        final List<BookAuthor> allAuthors = testDao.findAll();
        Assert.assertNotNull(allAuthors);
        Assert.assertFalse(allAuthors.isEmpty());
        Assert.assertTrue(allAuthors.contains(testAuthor));
    }

    @Test
    public void findById() throws DAOException {
        final Optional<BookAuthor> byId = testDao.findById(testAuthor.getId());
        if (byId.isEmpty()){
            Assert.fail();
        }
        Assert.assertEquals(testAuthor, byId.get());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() throws DAOException {
        testDao.update(testAuthor);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() throws DAOException {
        testDao.delete(testAuthor.getId());
    }
}