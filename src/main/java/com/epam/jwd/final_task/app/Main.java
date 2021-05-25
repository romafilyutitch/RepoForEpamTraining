package com.epam.jwd.final_task.app;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import com.epam.jwd.final_task.exception.ConnectionsPoolActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ConnectionPoolInitializationException, ConnectionsPoolActionException, InterruptedException {
        logger.trace("App is started");
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        pool.init();
        for (int i = 0; i < 27; i++) {
            Connection connection = pool.takeFreeConnection();
        }
        pool.destroy();
        logger.trace("App is end");
    }
}
