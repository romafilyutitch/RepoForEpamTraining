package com.epam.jwd.final_task.connectionPool;
import com.epam.jwd.final_task.exception.ConnectionsPoolActionException;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection takeFreeConnection() throws ConnectionsPoolActionException;

    void returnTakenConnection(Connection connection) throws ConnectionsPoolActionException;

    void init() throws ConnectionPoolInitializationException;

    void destroy();

    static ConnectionPool getConnectionPool() {
        return OrdinaryConnectionPool.getInstance();
    }
}
