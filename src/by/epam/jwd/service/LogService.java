package by.epam.jwd.service;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.validation.ValidationStrategy;

public interface LogService {
    /**
     * Logs information of Point class instance
     *
     * @param point instance of Point class to be loged
     */
    void log(Point point);

    /**
     * Logs information of Figure instance
     *
     * @param figure instance of Figure class to be logged
     */
    void log(Figure figure);

    /**
     * Changes validation strategy
     *
     * @param newStrategy instance of ValidationStrategy subclass
     */
    void setValidationStrategy(ValidationStrategy newStrategy);
}
