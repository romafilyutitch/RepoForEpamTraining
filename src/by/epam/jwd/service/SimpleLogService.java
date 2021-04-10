package by.epam.jwd.service;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public enum SimpleLogService implements LogService {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(SimpleLogService.class);

    @Override
    public void info(Point point) {
        LOGGER.info(point);
    }

    @Override
    public void info(Figure figure) {
        LOGGER.info(figure + " perimeter is " + figure.getPerimeter() + ", square is " + figure.getSquare());
    }

    @Override
    public void info(Collection<Figure> figures) {
        for (Figure figure : figures) {
            info(figure);
        }
    }

    @Override
    public void error(String message) {
        LOGGER.error(message);
    }
}