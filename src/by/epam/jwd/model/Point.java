package by.epam.jwd.model;

import java.util.Arrays;
import java.util.Objects;

public class Point {
    private static final int GROWTH_FACTOR = 16;
    private static Point[] pointsCache = new Point[16];
    private static int cachedPointsAmount = 0;
    private final static String NEGATIVE_ARGUMENT_MESSAGE = "arguments must be positive";
    private final int x;
    private final int y;

    /**
     * Point constructor
     *
     * @param x value of horizontal axle
     * @param y value of vertical axle
     */
    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get horizontal axle value
     *
     * @return x axle value
     */
    public int getX() {
        return x;
    }

    /**
     * get vertical axle value
     *
     * @return y axle value
     */
    public int getY() {
        return y;
    }

    /**
     * Creates instance of Point and save instance if Points cache
     *
     * @param x horizontal axle value
     * @param y vertical axle value
     * @return instance of Point
     */
    public static Point newInstance(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(NEGATIVE_ARGUMENT_MESSAGE);
        }
        Point newPoint = null;
        for (int i = 0; i < cachedPointsAmount; i++) {
            Point cachedElement = pointsCache[i];
            if (cachedElement.getX() == x && cachedElement.getY() == y) {
                newPoint = cachedElement;
            }
        }
        if (newPoint == null) {
            newPoint = new Point(x, y);
            if (cachedPointsAmount == pointsCache.length) {
                pointsCache = Arrays.copyOf(pointsCache, pointsCache.length + GROWTH_FACTOR);
            }
            pointsCache[cachedPointsAmount] = newPoint;
            cachedPointsAmount++;
        }
        return newPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point other = (Point) o;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point { x = " + x + ", y = " + y + "}";
    }
}