package exception;

import java.sql.SQLException;

public class ConnectionPoolInitializationException extends Exception {
    public ConnectionPoolInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
