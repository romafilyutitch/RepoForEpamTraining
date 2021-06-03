package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.connectionPool.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.model.UserRole;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class MySQLRoleDaoTest {
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();
    private MySQLRoleDao testDao = MySQLRoleDao.getInstance();
    private UserRole testRole = UserRole.ADMIN;
    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void save() throws DAOException {
        testDao.save(testRole);
    }

    @Test
    public void findAll() throws DAOException {
        final List<UserRole> allRoles = testDao.findAll();
        Assert.assertNotNull(allRoles);
        Assert.assertFalse(allRoles.isEmpty());
        Assert.assertTrue(allRoles.contains(testRole));
    }

    @Test
    public void findById() throws DAOException {
        final Optional<UserRole> byId = testDao.findById(testRole.getId());
        if (byId.isEmpty()) {
            Assert.fail();
        }
        Assert.assertEquals(testRole, byId.get());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() throws DAOException {
        testDao.update(testRole);
    }

    @Test(expected =UnsupportedOperationException.class)
    public void delete() throws DAOException {
        testDao.delete(testRole.getId());
    }
}