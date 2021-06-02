package com.epam.jwd.final_task.dao;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.DAOException;
import com.epam.jwd.final_task.model.UserRole;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MySQLRoleDaoTest {
    private static ConnectionPool pool = ConnectionPool.getConnectionPool();
    private MySQLRoleDao testDao = MySQLRoleDao.getInstance();

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        pool.init();
    }

    @AfterClass
    public static void tearDown() {
        pool.destroy();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void save_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.save(UserRole.ADMIN);
    }

    @Test
    public void findAll_mustReturnListOfUserRoles() throws DAOException {
        final List<UserRole> roles = testDao.findAll();
        Assert.assertNotNull("List of user roles must be not null");
        Assert.assertFalse("list of all authors must not contain null", roles.contains(null));
    }

    @Test
    public void findById_mustReturnCorrectUserRoleWithSpecifiedId() throws DAOException {
        final Optional<UserRole> optionalReader = testDao.findById(1L);
        final Optional<UserRole> optionalLibrarian = testDao.findById(2L);
        final Optional<UserRole> optionalAdmin = testDao.findById(3L);

        if (optionalReader.isEmpty() || optionalLibrarian.isEmpty() || optionalAdmin.isEmpty()) {
            Assert.fail("optional of user roles must be not empty");
        }

        final UserRole reader = optionalReader.get();
        final UserRole librarian = optionalLibrarian.get();
        final UserRole admin = optionalAdmin.get();

        Assert.assertEquals(reader.getId(), UserRole.READER.getId());
        Assert.assertEquals(librarian.getId(), UserRole.LIBRARIAN.getId());
        Assert.assertEquals(admin.getId(), UserRole.ADMIN.getId());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.update(UserRole.ADMIN);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete_mustThrowUnsupportedOperationException() throws DAOException {
        testDao.delete(new Random().nextLong());
    }

    @Test
    public void findByName_mustFindRolesByNames_whenNamesIsPassed() throws DAOException {
        final Optional<UserRole> optionalAdminRole = testDao.findByName("Admin");
        final Optional<UserRole> optionalReaderRole = testDao.findByName("Reader");
        final Optional<UserRole> optionalLibrarianRole = testDao.findByName("Librarian");
        if (optionalAdminRole.isEmpty() || optionalReaderRole.isEmpty() || optionalLibrarianRole.isEmpty())  {
            Assert.fail("One of optional doesn't contain role enum element");
        }
        Assert.assertEquals("optional admin role must be equal to admin role enum", UserRole.ADMIN, optionalAdminRole.get());
        Assert.assertEquals("optional reader role must be equal to reader role enum", UserRole.READER, optionalReaderRole.get());
        Assert.assertEquals("optional librarian role must be equal to librarian role enum", UserRole.LIBRARIAN, optionalLibrarianRole.get());
    }
}