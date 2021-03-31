package by.epam.jwd.entity;

public class TriangleFactory implements FigureFactory {
    private static final String WRONG_ARRAY_LENGTH_MESSAGE = "array length must be 3";

    @Override
    public Figure getFigure(Point... points) {
        if (points.length != 3) {
            throw new IllegalArgumentException(WRONG_ARRAY_LENGTH_MESSAGE);
        }
        return new Triangle(points);
    }
}
