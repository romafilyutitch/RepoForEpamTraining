package app;

import connectionPool.ConnectionPool;
import exception.ConnectionPoolInitializationException;
import exception.ConnectionsPoolActionException;

public class Main {
    public static void main(String[] args) throws ConnectionPoolInitializationException, ConnectionsPoolActionException, InterruptedException {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        pool.init();
        Thread.sleep(10000);
        pool.destroy();
    }
}
