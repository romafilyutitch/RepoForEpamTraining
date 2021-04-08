package by.epam.jwd.validation;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public interface ValidationStrategy {
    String IS_NOT_FIGURE_MESSAGE = "Object %s is not figure %s";

    /**
     * Validates passed figure
     *
     * @param figure insatnce of Figure sublass to be validated
     * @throws CanNotExistException if passed triangle can not be triangle
     * @throws IsNotSquareException if passed square can is not square figure
     * @throws IsNotFigureException if passed figure contains same points
     */
    void check(Figure figure) throws CanNotExistException, IsNotSquareException, IsNotFigureException;

    /**
     * Check if figure contains same points in Points array
     *
     * @param figure figure to be checked
     * @throws IsNotFigureException if figure contains same points
     */
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

    /**
     * Check if figure points have same x axle value
     *
     * @param points points of figure
     * @return {@code true} if figure have same x axle value, {@code false} otherwise
     */
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

    /**
     * Check if figure points have same y axle value
     *
     * @param points points of figure
     * @return {@code true} if figure have same y axle value, {@code false} otherwise
     */
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
