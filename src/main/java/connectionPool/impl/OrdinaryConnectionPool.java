package connectionPool.impl;

import connectionPool.ConnectionPool;
import exception.ConnectionsPoolActionException;
import exception.ConnectionPoolInitializationException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrdinaryConnectionPool implements ConnectionPool {

    private static final int MINIMUM_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 30;
    private static final int RESIZE_QUANTITY = 5;

    private final BlockingQueue<Connection> freeConnectionsQueue = new ArrayBlockingQueue<>(MAXIMUM_POOL_SIZE);
    private final CopyOnWriteArraySet<Connection> takenConnections = new CopyOnWriteArraySet<>();
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final Timer timer = new Timer();
    private final PoolResizeTimerTask poolResizeTimerTask = new PoolResizeTimerTask();

    @Override
    public Connection takeConnection() throws ConnectionsPoolActionException {
        if (!isInitialized.get()) {
            throw new IllegalStateException("Pool is not initialized");
        }
        try {
            final Connection connection = freeConnectionsQueue.take();
            takenConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new ConnectionsPoolActionException("Could not take connection", e);
        }
    }

    @Override
    public void returnConnection(Connection connection) throws ConnectionsPoolActionException {
        if (!isInitialized.get()) {
            throw new IllegalStateException("Pool is not initialized");
        }
        checkReturnedConnection(connection);
        try {
            takenConnections.remove(connection);
            freeConnectionsQueue.put(connection);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new ConnectionsPoolActionException("Could not return connection", e);
        }
    }

    @Override
    public void init() throws ConnectionPoolInitializationException {
        if (isInitialized.compareAndSet(false, true)) {
            registerDrivers();
            try {
                for (int i = 0; i < MINIMUM_POOL_SIZE; i++) {
                    final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb?serverTimezone=UTC", "root", "050399");
                    freeConnectionsQueue.put(connection);
                }
                timer.schedule(poolResizeTimerTask, 1000, 5000);
            } catch (SQLException | InterruptedException e) {
                System.out.println(e.getMessage());
                isInitialized.set(false);
                throw new ConnectionPoolInitializationException("failed to open connection", e);
            }
        } else {
            timer.cancel();
            throw new IllegalStateException("Cannot make initialization because pool is already initialized");
        }
    }

    @Override
    public void destroy() {
        if (isInitialized.compareAndSet(true, false)) {
            closeFreeConnections();
            freeConnectionsQueue.clear();
            closeTakenConnections();
            takenConnections.clear();
            timer.cancel();
            deregisterDrivers();
        } else {
            throw new IllegalStateException("Cannot destroy connection pool because pool is not initialized");
        }
    }

    private void checkReturnedConnection(Connection connection) {
        if (connection == null) {
            throw new NullPointerException("Connection is null");
        }
        if (!takenConnections.contains(connection)) {
            throw new IllegalArgumentException("Connection " + connection + " is not taken connection");
        }
    }

    private void closeTakenConnections() {
        for (Connection connection : takenConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void closeFreeConnections() {
        for (Connection connection : freeConnectionsQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void registerDrivers() throws ConnectionPoolInitializationException {
        try {
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/librarydb"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isInitialized.set(false);
            throw new ConnectionPoolInitializationException("Driver register failed", e);
        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> iterator = DriverManager.getDrivers();
        while (iterator.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(iterator.nextElement());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private class PoolResizeTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Free connections = " + freeConnectionsQueue.size() + " taken connections = "
                    + takenConnections.size() + ", total connections = " + (freeConnectionsQueue.size() + takenConnections.size()));
            if (isNeedToGrowPool()) {
                System.out.println("Action is  grow pool");
                addConnections();
            } else if (isNeedToTrimPool()) {
                System.out.println("Result is trim pool");
                closeConnections();
            }
        }

        private boolean isNeedToTrimPool() {
            return takenConnections.size() < freeConnectionsQueue.size() * 0.25
                    && freeConnectionsQueue.size() > MINIMUM_POOL_SIZE;
        }

        private boolean isNeedToGrowPool() {
            return freeConnectionsQueue.size() < takenConnections.size() * 0.25
                    && freeConnectionsQueue.size() + takenConnections.size() < 30;
        }

        private void addConnections() {
            for (int i = 0; i < RESIZE_QUANTITY; i++) {
                try {
                    freeConnectionsQueue.put(DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb?serverTimezone=UTC", "root", "050399"));
                } catch (SQLException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        private void closeConnections() {
            for (int i = 0; i < RESIZE_QUANTITY; i++) {
                try {
                    Connection connectionToClose = freeConnectionsQueue.take();
                    connectionToClose.close();
                } catch (SQLException | InterruptedException ex) {
                   System.out.println(ex.getMessage());
                }
            }
        }
    }
}
