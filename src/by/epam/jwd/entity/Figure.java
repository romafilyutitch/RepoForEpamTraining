package by.epam.jwd.entity;

import java.util.Arrays;

public abstract class Figure {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "index out of bounds";
    private Point[] points;

    public Figure(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public Point getPoint(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
        return points[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Arrays.equals(points, figure.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}