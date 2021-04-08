package by.epam.jwd.strategy;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public interface FigurePropertiesStrategy {
    /**
     * Computes perimeter value of passed figure
     *
     * @param figure instance of Figure subclass
     * @return perimeter value of passed figure
     */
    double getPerimeter(Figure figure);

    /**
     * Computes square value of passed figure
     *
     * @param figure instance of Figure subclass
     * @return square value of passed figure
     */
    double getSquare(Figure figure);

    /**
     * Computes length od distance between two points
     *
     * @param onePoint   one instance of Point class
     * @param otherPoint other instance of Point class
     * @return length of distance between two points
     */
    default double getLineLength(Point onePoint, Point otherPoint) {
        double xLength = Math.abs(onePoint.getX() - otherPoint.getX());
        double yLength = Math.abs(onePoint.getY() - otherPoint.getY());
        return Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
    }
}
