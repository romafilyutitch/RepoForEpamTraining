package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.connectionPool.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.model.BookOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MySQLOrderDaoTest {
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();
    private final MySQLOrderDao testDao = MySQLOrderDao.getInstance();
    private BookOrder testOrder;

    public MySQLOrderDaoTest() throws DAOException {
        testOrder = new BookOrder(DaoFactory.getInstance().getUserDao().findAll().stream().findAny().get(),
                DaoFactory.getInstance().getBookDao().findAll().stream().findAny().get(),
                LocalDate.now());
    }

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Before
    public void saveTestOrder() throws DAOException {
        testOrder = testDao.save(testOrder);
    }

    @After
    public void deleteTestOrder() throws DAOException {
        testDao.delete(testOrder.getId());
    }

    @Test
    public void save() {
        Assert.assertNotNull(testOrder);
        Assert.assertNotNull(testOrder.getId());
    }

    @Test
    public void findAll() throws DAOException {
        final List<BookOrder> all = testDao.findAll();
        Assert.assertNotNull(all);
        Assert.assertFalse(all.isEmpty());
        Assert.assertTrue(all.contains(testOrder));
    }

    @Test
    public void findById() throws DAOException {
        final Optional<BookOrder> byId = testDao.findById(testOrder.getId());
        if (byId.isEmpty()) {
            Assert.fail();
        }
        Assert.assertEquals(testOrder, byId.get());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() throws DAOException {
        testDao.update(testOrder);
    }

    @Test
    public void delete() throws DAOException {
        testDao.delete(testOrder.getId());
        Assert.assertTrue(testDao.findById(testOrder.getId()).isEmpty());
    }
}