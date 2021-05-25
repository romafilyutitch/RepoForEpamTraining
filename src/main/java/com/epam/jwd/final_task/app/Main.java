package com.epam.jwd.final_task.app;

import com.epam.jwd.final_task.connectionPool.ConnectionPool;
import com.epam.jwd.final_task.exception.ConnectionPoolInitializationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ConnectionPoolInitializationException, InterruptedException {
        logger.trace("App is started");
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        pool.init();
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            connections.add(pool.takeFreeConnection());
        }
        Thread.sleep(10000);
        for (int i = 0; i < 25; i++) {
            pool.returnTakenConnection(connections.get(i));
        }
        Thread.sleep(50000);
        pool.destroy();
        logger.trace("App is end");
    }
}
