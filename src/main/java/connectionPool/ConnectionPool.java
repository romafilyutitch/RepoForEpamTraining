package connectionPool;
import connectionPool.impl.OrdinaryConnectionPool;
import exception.ConnectionsPoolActionException;
import exception.ConnectionPoolInitializationException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection takeConnection() throws ConnectionsPoolActionException;

    void returnConnection(Connection connection) throws ConnectionsPoolActionException;

    void init() throws ConnectionPoolInitializationException;

    void destroy();

    static ConnectionPool getConnectionPool() {
        return OrdinaryConnectionPool.INSTANCE;
    }
}
