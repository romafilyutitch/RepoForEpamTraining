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
        ConnectionPool pool = new OrdinaryConnectionPool();
        pool.init();
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            connections.add(pool.takeConnection());
        }
        Thread.sleep(10000);
        for (int i = 0; i < 25; i++) {
            pool.returnConnection(connections.get(i));
        }
        Thread.sleep(100000);
        pool.destroy();
    }
}
