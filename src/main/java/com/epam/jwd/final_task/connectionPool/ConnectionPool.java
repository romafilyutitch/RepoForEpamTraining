package com.epam.jwd.final_task.connectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection takeFreeConnection();

    void returnTakenConnection(Connection connection);

    void init() throws ConnectionPoolInitializationException;

    void destroy();

    static ConnectionPool getConnectionPool() {
        return OrdinaryConnectionPool.getInstance();
    }
}
