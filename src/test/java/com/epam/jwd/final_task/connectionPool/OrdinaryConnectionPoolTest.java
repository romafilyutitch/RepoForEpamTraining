package com.epam.jwd.final_task.connectionPool;

import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.ConnectionsPoolActionException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OrdinaryConnectionPoolTest {
    private static final OrdinaryConnectionPool testPool = OrdinaryConnectionPool.getInstance();

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        testPool.init();
    }

    @AfterClass
    public static void tearDown() {
        testPool.destroy();
    }

    @Test
    public void takeFreeConnection_mustReturnNutNullConnection() throws ConnectionsPoolActionException {
        final Connection testConnection = testPool.takeFreeConnection();
        Assert.assertNotNull("Not null connection will be taken", testConnection);
    }

    @Test
    public void returnTakenConnection() throws ConnectionsPoolActionException {
        final Connection testConnection = testPool.takeFreeConnection();
        testPool.returnTakenConnection(testConnection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnTakenConnection_mustThrowException_whenReturnedConnectionIsNotInTakenConnectionsSet() throws SQLException, ConnectionsPoolActionException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "050399");
        testPool.returnTakenConnection(connection);
    }

    @Test(expected = NullPointerException.class)
    public void returnTakenConnection_mustThrowException_whenReturnedConnectionIsNull() throws ConnectionsPoolActionException {
        testPool.returnTakenConnection(null);
    }

    @Test
    public void takeFreeConnection_mustReturnProxyConnection() throws ConnectionsPoolActionException {
        final Connection connection = testPool.takeFreeConnection();
        Assert.assertTrue("Taken connection must be instance of ProxyConnection class", connection instanceof ProxyConnection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnTakenConnection_mustThrowException_whenReturnConnectionIsNotProxyConnection() throws SQLException, ConnectionsPoolActionException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "050399");
        testPool.returnTakenConnection(connection);
    }
}