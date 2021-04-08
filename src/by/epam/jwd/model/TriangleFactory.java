package by.epam.jwd.model;

import by.epam.jwd.strategy.TrianglePropertiesStrategy;

public enum TriangleFactory implements FigureFactory {
    INSTANCE;

    private static final String WRONG_ARRAY_LENGTH_MESSAGE = "array length must be 3";

    @Override
    public Figure getFigure(Point... points) {
        if (points.length != 3) {
            throw new IllegalArgumentException(WRONG_ARRAY_LENGTH_MESSAGE);
        }
        return new Triangle(points, TrianglePropertiesStrategy.INSTANCE);
    }
}
