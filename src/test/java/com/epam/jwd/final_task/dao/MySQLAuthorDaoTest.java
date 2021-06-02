package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.BookAuthor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MySQLAuthorDaoTest {
    private MySQLAuthorDao testDao = MySQLAuthorDao.getInstance();
    private static  ConnectionPool pool = ConnectionPool.getConnectionPool();

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Test
    public void save_mustReturnAuthorWithId_whenNewAuthorIsPassed() throws DAOException {
        final BookAuthor bookAuthor = new BookAuthor("Tom");
        final BookAuthor savedAuthor = testDao.save(bookAuthor);
        Assert.assertNull("Object out of database must be without id", bookAuthor.getId());
        Assert.assertNotNull("author must be returned from database", savedAuthor);
        Assert.assertNotNull("Id value must be assigned after saved finised", savedAuthor.getId());
    }

    @Test
    public void save_mustReturnAuthorFromDatabase_whenAuthorWithThisNameContains() throws DAOException {
        final BookAuthor bookAuthor = new BookAuthor("Tom");
        final BookAuthor savedAuthor = testDao.save(bookAuthor);
        Assert.assertNotNull("author must be returned from database", savedAuthor);
        Assert.assertEquals("Name of passed author and return author must be equals", bookAuthor.getName(), savedAuthor.getName());
        Assert.assertNotNull("returned object must have id", savedAuthor.getId());
    }

    @Test
    public void findAll_mustReturnListOfAllBookAuthors() throws DAOException {
        final List<BookAuthor> all = testDao.findAll();
        Assert.assertNotNull("list of all authors must be not null", all);
        Assert.assertFalse("list of all authors must not contain null", all.contains(null));
    }

    @Test
    public void findById_mustFindSavedBookAuthor_idContains() throws DAOException {
        final BookAuthor bookAuthor = new BookAuthor("Mike");
        final BookAuthor savedBookAuthor = testDao.save(bookAuthor);
        final Optional<BookAuthor> optionalFoundBookAuthor = testDao.findById(savedBookAuthor.getId());
        if (optionalFoundBookAuthor.isEmpty()) {
            Assert.fail("dao must not return empty object");
        }
        final BookAuthor foundBookAuthor = optionalFoundBookAuthor.get();
        Assert.assertEquals("saved book author must be equals to found book author", savedBookAuthor, foundBookAuthor);
    }

    @Test
    public void findById_mustFindEmptyOptionalBookAuthor_ifIdDoesntContains() throws DAOException {
        final Optional<BookAuthor> byId = testDao.findById(0L);
        Assert.assertTrue("optional book author with id = 0 must not exist ", byId.isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.update(new BookAuthor("John"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.delete(new Random().nextLong());
    }
}