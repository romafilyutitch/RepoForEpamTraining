package by.epam.jwd.model;

import by.epam.jwd.strategy.LinePropertiesStrategy;


public enum LineFactory implements FigureFactory {
    INSTANCE;

    private static final String WRONG_ARRAY_LENGTH_MESSAGE = "array length must be 2";

    @Override
    public Figure getFigure(Point... points) {
        if (points.length != 2) {
            throw new IllegalArgumentException(WRONG_ARRAY_LENGTH_MESSAGE);
        }
        return new Line(points, LinePropertiesStrategy.getInstance());
    }
}
