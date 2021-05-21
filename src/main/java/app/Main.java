package app;

import connectionPool.ConnectionPool;
import connectionPool.impl.OrdinaryConnectionPool;

import exception.ConnectionsPoolActionException;
import exception.ConnectionPoolInitializationException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ConnectionPoolInitializationException, ConnectionsPoolActionException, InterruptedException {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        pool.init();
        for (int i = 0; i < 44; i++) {
            Connection connection = pool.takeConnection();
        }
        pool.destroy();
    }
}
