package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.connectionPool.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.model.Book;
import com.epam.jwd.final_task.model.BookAuthor;
import com.epam.jwd.final_task.model.BookGenre;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MySQLBookDaoTest {
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();
    private final MySQLBookDao testDao = MySQLBookDao.getInstance();
    private Book testBook = new Book("Sherlock Holmes", new BookAuthor("A.C. Doyl"), new BookGenre("Detective"), LocalDate.now(), 100, "Detective stories");

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Before
    public void saveTestBook() throws DAOException {
        testBook = testDao.save(testBook);
    }

    @After
    public void deleteTestBook() throws DAOException {
        testDao.delete(testBook.getId());
    }

    @Test
    public void save() {
        Assert.assertNotNull(testBook);
        Assert.assertNotNull(testBook.getId());
    }

    @Test
    public void findAll() throws DAOException {
        final List<Book> allBooks = testDao.findAll();
        Assert.assertNotNull(allBooks);
        Assert.assertFalse(allBooks.isEmpty());
        Assert.assertTrue(allBooks.contains(testBook));
    }

    @Test
    public void findById() throws DAOException {
        final Optional<Book> byId = testDao.findById(testBook.getId());
        if (byId.isEmpty()) {
            Assert.fail();
        }
        Assert.assertEquals(testBook.getId(), byId.get().getId());
    }

    @Test
    public void update() throws DAOException {
        final Book updatedDate = testBook.updateDate(LocalDate.now().minusYears(2));
        final Book update = testDao.update(updatedDate);
        Assert.assertEquals(updatedDate.getId(), update.getId());
        Assert.assertEquals(updatedDate, update);
    }

    @Test
    public void delete() throws DAOException {
        testDao.delete(testBook.getId());
        Assert.assertTrue(testDao.findById(testBook.getId()).isEmpty());
    }
}