package by.epam.jwd.model;

import by.epam.jwd.strategy.MultiAngleFigurePropertiesStrategy;

public enum MultiAngleFigureFactory implements FigureFactory {
    INSATNCE;

    private static final String ARRAY_SIZE_NOT_VALID = "array size is not valid. (4<=N<=6)";

    @Override
    public Figure getFigure(Point... points) {
        if (points.length < 4 || points.length > 6) {
            throw new IllegalArgumentException(ARRAY_SIZE_NOT_VALID);
        }
        return new MultiAngleFigure(points, MultiAngleFigurePropertiesStrategy.getInstance());
    }
}
