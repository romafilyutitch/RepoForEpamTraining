package by.epam.jwd.service.impl;

import by.epam.jwd.service.LogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum SimpleLogService implements LogService {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(SimpleLogService.class);

    @Override
    public void info(Object object) {
        LOGGER.info(object);
    }

    @Override
    public void printMessage(String message) {
        LOGGER.info(message);
    }

    @Override
    public void error(String message) {
        LOGGER.error(message);
    }
}