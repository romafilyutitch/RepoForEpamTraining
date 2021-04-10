package by.epam.jwd.model;

import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

public abstract class Figure {
    private static long ID = 0;
    private long id;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "index out of bounds";
    private final Point[] points;
    private final FigurePropertiesStrategy figurePropertiesStrategy;

    /**
     * Figure constructor
     *
     * @param points           points array to pass
     * @param propertyStrategy set figure properties
     */
    public Figure(Point[] points, FigurePropertiesStrategy propertyStrategy) {
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
        return Arrays.equals(points, figure.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}