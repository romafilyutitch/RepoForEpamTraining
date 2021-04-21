package by.epam.jwd.model;

import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;
import java.util.Objects;

public abstract class Figure {
    private static long ID = 0;
    private final long id;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "index out of bounds";
    private final Point[] points;
    private final FigurePropertiesStrategy figurePropertiesStrategy;

    public Figure(Point[] points, FigurePropertiesStrategy propertyStrategy) {
        if (points == null) {
            throw new IllegalArgumentException("points arrays must not be null");
        }
        if (points.length == 0) {
            throw new IllegalArgumentException("points array length must be bigger than 0");
        }
        this.points = points;
        this.id = ID++;
        this.figurePropertiesStrategy = propertyStrategy;
    }

    /**
     * Get array of Points
     *
     * @return Array of figure points
     */
    public Point[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    /**
     * Get a points for specified index
     *
     * @param index index of point if points array
     * @return Point instance in points array;
     * @throws IndexOutOfBoundsException if index is not valid
     */
    public Point getPoint(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
        return points[index];
    }

    /**
     * Returns figure id
     * @return figure id
     */
    public long getId() {
        return id;
    }

    /**
     * Get perimetr of figure
     *
     * @return perimetr of figure
     */
    public double getPerimeter() {
        return figurePropertiesStrategy.getPerimeter(this);
    }

    /**
     * Get square of figure
     *
     * @return square of figure
     */
    public double getSquare() {
        return figurePropertiesStrategy.getSquare(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Arrays.equals(points, figure.points) && Objects.equals(figurePropertiesStrategy, figure.figurePropertiesStrategy);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(figurePropertiesStrategy);
        result = 31 * result + Arrays.hashCode(points);
        return result;
    }
}