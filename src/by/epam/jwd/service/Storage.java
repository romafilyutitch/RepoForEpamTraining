package by.epam.jwd.service;

import by.epam.jwd.model.Figure;

import java.util.stream.Stream;

public interface Storage {
    boolean save(Figure figure);
    boolean remove(Figure figure);
    Stream<Figure> getStream();
}
