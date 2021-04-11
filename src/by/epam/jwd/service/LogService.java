package by.epam.jwd.service;

public interface LogService {

    void info(Object object);

    void printMessage(String message);

    void error(String message);
}
