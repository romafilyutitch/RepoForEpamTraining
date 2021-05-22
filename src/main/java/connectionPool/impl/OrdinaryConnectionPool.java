package connectionPool.impl;

import connectionPool.ConnectionPool;
import connectionPool.ProxyConnection;
import exception.ConnectionPoolInitializationException;
import exception.ConnectionsPoolActionException;
import properties.ConnectionPoolProperties;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

public enum OrdinaryConnectionPool implements ConnectionPool {
    INSTANCE;

    public static final String POOL_IS_NOT_INITIALIZED_MESSAGE = "Pool is not initialized";
    public static final String COULD_NOT_TAKE_CONNECTION_MESSAGE = "Could not take connection";
    public static final String COULD_NOT_RETURN_CONNECTION_MESSAGE = "Could not return connection";
    public static final String CONNECTION_IS_NOT_PROXY_CONNECTION_MESSAGE = "Connection is not proxy connection";
    public static final String FAILED_TO_OPEN_CONNECTION_MESSAGE = "failed to open connection";
    public static final String POOL_IS_INITIALIZED_EXCEPTION_MESSAGE = "Cannot make initialization. Pool is already initialized";
    public static final String NEGATIVE_AMOUNT_OF_ADDED_CONNECTIONS_MESSAGE = "amount of added connections is negative";
    public static final String CONNECTION_IS_NULL_MESSAGE = "Connection is null";
    public static final String RETURNED_CONNECTION_IS_NOT_TAKEN_CONNECTION_MESSAGE = "Returned connection is not taken connection";
    public static final String DRIVERS_REGISTRATION_FAILED_MESSAGE = "Driver registration failed";

    private final int MINIMUM_POOL_SIZE = ConnectionPoolProperties.getMinimalPoolSize();
    private final int MAXIMUM_POOL_SIZE = ConnectionPoolProperties.getMaximalPoolSize();
    private final int RESIZE_QUANTITY = ConnectionPoolProperties.getResizeQuantity();
    private final int POOL_RESIZE_CHECK_DELAY_TIME =  ConnectionPoolProperties.getPoolResizeTimerTaskCheckDelayTime();
    private final int POOL_RESIZE_CHECK_PERIOD_TIME = ConnectionPoolProperties.getPoolResizeTimerTaskCheckPeriodTime();

    private final BlockingQueue<Connection> freeConnectionsQueue = new ArrayBlockingQueue<>(MAXIMUM_POOL_SIZE);
    private final CopyOnWriteArraySet<Connection> takenConnections = new CopyOnWriteArraySet<>();
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final Timer timer = new Timer(true);
    private final PoolResizeTimerTask poolResizeTimerTask = new PoolResizeTimerTask();

    @Override
    public Connection takeConnection() throws ConnectionsPoolActionException {
        if (!isInitialized.get()) {
            throw new IllegalStateException(POOL_IS_NOT_INITIALIZED_MESSAGE);
        }
        try {
            final Connection connection = freeConnectionsQueue.take();
            takenConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new ConnectionsPoolActionException(COULD_NOT_TAKE_CONNECTION_MESSAGE, e);
        }
    }

    @Override
    public void returnConnection(Connection connection) throws ConnectionsPoolActionException {
        if (!isInitialized.get()) {
            throw new IllegalStateException(POOL_IS_NOT_INITIALIZED_MESSAGE);
        }
        checkReturnedConnection(connection);
        if (connection instanceof ProxyConnection) {
            try {
                takenConnections.remove(connection);
                freeConnectionsQueue.put(connection);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new ConnectionsPoolActionException(COULD_NOT_RETURN_CONNECTION_MESSAGE, e);
            }
        } else {
            throw new IllegalArgumentException(CONNECTION_IS_NOT_PROXY_CONNECTION_MESSAGE);
        }
    }

    @Override
    public void init() throws ConnectionPoolInitializationException {
        if (isInitialized.compareAndSet(false, true)) {
            registerDrivers();
            try {
                addConnectionsToPool(MINIMUM_POOL_SIZE);
            } catch (SQLException | InterruptedException e) {
                System.out.print(e.getMessage());
                isInitialized.set(false);
                deregisterDrivers();
                throw new ConnectionPoolInitializationException(FAILED_TO_OPEN_CONNECTION_MESSAGE, e);
            }
            timer.schedule(poolResizeTimerTask, POOL_RESIZE_CHECK_DELAY_TIME, POOL_RESIZE_CHECK_PERIOD_TIME);
        } else {
            throw new IllegalStateException(POOL_IS_INITIALIZED_EXCEPTION_MESSAGE);
        }
    }

    private void addConnectionsToPool(int amountOfAddedConnections) throws SQLException, InterruptedException {
        if (amountOfAddedConnections < 0) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_OF_ADDED_CONNECTIONS_MESSAGE);
        }
        for (int i = 0; i < amountOfAddedConnections; i++) {
            final Connection connection = DriverManager.getConnection(ConnectionPoolProperties.getUrl(), ConnectionPoolProperties.getUserName(), ConnectionPoolProperties.getPassword());
            final ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnectionsQueue.put(proxyConnection);
        }
    }

    @Override
    public void destroy() {
        if (isInitialized.compareAndSet(true, false)) {
            closeFreeConnections();
            freeConnectionsQueue.clear();
            closeTakenConnections();
            takenConnections.clear();
            deregisterDrivers();
        } else {
            throw new IllegalStateException(POOL_IS_NOT_INITIALIZED_MESSAGE);
        }
    }

    private void checkReturnedConnection(Connection connection) {
        if (connection == null) {
            throw new NullPointerException(CONNECTION_IS_NULL_MESSAGE);
        }
        if (!takenConnections.contains(connection)) {
            throw new IllegalArgumentException(RETURNED_CONNECTION_IS_NOT_TAKEN_CONNECTION_MESSAGE);
        }
    }

    private void closeTakenConnections() {
        for (Connection connection : takenConnections) {
            try {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void closeFreeConnections() {
        for (Connection connection : freeConnectionsQueue) {
            try {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void registerDrivers() throws ConnectionPoolInitializationException {
        try {
            DriverManager.registerDriver(DriverManager.getDriver(ConnectionPoolProperties.getUrl()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isInitialized.set(false);
            throw new ConnectionPoolInitializationException(DRIVERS_REGISTRATION_FAILED_MESSAGE, e);
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
                    && freeConnectionsQueue.size() + takenConnections.size() < MAXIMUM_POOL_SIZE;
        }

        private void addConnections() {
            try {
                addConnectionsToPool(RESIZE_QUANTITY);
            } catch (SQLException | InterruptedException e) {
                System.out.println(e);
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
