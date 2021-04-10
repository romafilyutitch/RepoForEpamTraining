package by.epam.jwd.service.impl;

import by.epam.jwd.model.Figure;
import by.epam.jwd.service.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class SimpleStorage implements Storage {
    private Collection<Figure> figures = new ArrayList<>();

    @Override
    public boolean save(Figure figure) {
        return figures.add(figure);
    }

    @Override
    public boolean remove(Figure figure) {
        return figures.remove(figure);
    }

    @Override
    public Stream<Figure> getStream() {
        return figures.stream();
    }
}
