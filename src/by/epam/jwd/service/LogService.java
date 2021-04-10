package by.epam.jwd.service;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

import java.util.Collection;

public interface LogService {

    void info(Point point);

    void info(Figure message);

    void info(Collection<Figure> figures);

    void error(String message);
}
