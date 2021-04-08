package by.epam.jwd.exception;

/**
 * Exception class for validation if triangle can not exist
 */
public class CanNotExistException extends Exception {
    public CanNotExistException(String message) {
        super(message);
    }
}