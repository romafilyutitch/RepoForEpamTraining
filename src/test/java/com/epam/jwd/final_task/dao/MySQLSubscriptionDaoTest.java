package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.Subscription;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MySQLSubscriptionDaoTest {
    private static ConnectionPool pool = ConnectionPool.getConnectionPool();
    private MySQLSubscriptionDao testDao = MySQLSubscriptionDao.getInstance();

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Test
    public void save_mustAssignIdToSavedSubscription() throws DAOException {
        Subscription subscription = new Subscription(LocalDate.now(), LocalDate.now().plusDays(10));
        subscription = testDao.save(subscription);
        Assert.assertNotNull("saved subscription must be not null", subscription);
        Assert.assertNotNull("save subscription id must be not null", subscription.getId());
    }

    @Test
    public void findAll_mustReturnListOfSubscriptions() throws DAOException {
        final List<Subscription> allSubscriptions = testDao.findAll();
        Assert.assertNotNull("subscriptions list must be not null", allSubscriptions);
        Assert.assertFalse("subscriptions list must not contain null", allSubscriptions.contains(null));
    }

    @Test
    public void findById_mustReturnSubscriptionWithSpecifiedId() throws DAOException {
        Subscription subscription = new Subscription(LocalDate.now(), LocalDate.now().plusDays(10));
        subscription = testDao.save(subscription);
        Optional<Subscription> optionalSubscription = testDao.findById(subscription.getId());
        if (optionalSubscription.isEmpty()) {
            Assert.fail();
        }
        Subscription foundSubscription = optionalSubscription.get();
        Assert.assertEquals("saved and found subscriptions id must be equals", subscription.getId(), foundSubscription.getId());
    }

    @Test
    public void update_mustChangeUpdatedElementInTable() throws DAOException {
        Subscription subscription = new Subscription(LocalDate.now(), LocalDate.now().plusDays(10));
        subscription = testDao.save(subscription);
        subscription = subscription.updateEndDate(LocalDate.now());
        final Subscription updatedSubscription = testDao.update(subscription);
        Assert.assertNotNull("updated subscription must be not null", updatedSubscription);
        Assert.assertEquals("subscription with changed date and updated subscription must be equals", subscription, updatedSubscription);
    }

    @Test
    public void delete_mustDeleteSavedSubscriptionWithSpecifiedId() throws DAOException {
        Subscription subscription = new Subscription(LocalDate.now(), LocalDate.now().plusDays(10));
        subscription = testDao.save(subscription);
        Long id = subscription.getId();
        testDao.delete(id);
        final Optional<Subscription> optionalDeletedSubscription = testDao.findById(id);
        Assert.assertTrue("optional deleted subscription must be empty", optionalDeletedSubscription.isEmpty());
    }
}