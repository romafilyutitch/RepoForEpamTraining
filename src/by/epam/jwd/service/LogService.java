package by.epam.jwd.service;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.validation.ValidationStrategy;

public interface LogService {
    void log(Point point);

    void log(Figure figure);

    void setValidationStrategy(ValidationStrategy newStrategy);
}
