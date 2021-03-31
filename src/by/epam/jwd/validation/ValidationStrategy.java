package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public interface ValidationStrategy {
    String IS_NOT_FIGURE_MESSAGE = "Object %s is not figure %s";

    void check(Figure figure) throws CanNotExistException, IsNotSquareException, IsNotFigureException;

    default void checkIfContainsSamePoints(Figure figure) throws IsNotFigureException {
        Point[] points = figure.getPoints();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].equals(points[j])) {
                    throw new IsNotFigureException(String.format(IS_NOT_FIGURE_MESSAGE, figure, figure.getClass().getSimpleName()));
                }
            }
        }
    }

    default boolean isSameX(Point[] points) {
        final int xCoord = points[0].getX();
        int amountOfPointsOnSameX = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getX() == xCoord) {
                amountOfPointsOnSameX++;
            }
        }
        return amountOfPointsOnSameX == points.length;
    }

    default boolean isSameY(Point[] points) {
        final int yCoord = points[0].getY();
        int amountOfPointsOnSameY = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() == yCoord) {
                amountOfPointsOnSameY++;
            }
        }
        return amountOfPointsOnSameY == points.length;
    }
}
