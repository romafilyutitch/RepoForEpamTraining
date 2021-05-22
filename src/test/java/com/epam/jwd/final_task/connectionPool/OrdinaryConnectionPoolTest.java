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
    private static OrdinaryConnectionPool testPool = OrdinaryConnectionPool.INSTANCE;

    @BeforeClass
    public static void setUp() throws ConnectionPoolInitializationException {
        testPool.init();
    }

    @AfterClass
    public static void tearDown() {
        testPool.destroy();
    }

    @Test
    public void takeConnection_willReturnNutNullConnection() throws ConnectionsPoolActionException {
        final Connection testConnection = testPool.takeConnection();
        Assert.assertNotNull("Not null connection will be taken", testConnection);
    }

    @Test
    public void returnConnection() throws ConnectionsPoolActionException {
        final Connection testConnection = testPool.takeConnection();
        testPool.returnConnection(testConnection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnConnection_willThrowException_whenReturnedConnectionIsNotInTakenConnectionsSet() throws SQLException, ConnectionsPoolActionException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "050399");
        testPool.returnConnection(connection);
    }

    @Test(expected = NullPointerException.class)
    public void returnConnection_willThrowException_whenReturnedConnectionIsNull() throws ConnectionsPoolActionException {
        testPool.returnConnection(null);
    }
}