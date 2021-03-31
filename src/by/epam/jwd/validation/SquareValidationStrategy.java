package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;


public class SquareValidationStrategy implements ValidationStrategy {
    private static final String IS_NOT_SQUARE_MESSAGE = "Object %s is not square";

    @Override
    public void check(Figure figure) throws IsNotSquareException, IsNotFigureException {
        checkIfContainsSamePoints(figure);
        Point[] points = figure.getPoints();
        if (isSameX(points) || isSameX(points) || !isSquare(points)) {
            throw new IsNotSquareException(String.format(IS_NOT_SQUARE_MESSAGE, figure));
        }
    }

    private boolean isSquare(Point[] points) {
        final int checkdistance = Math.abs(points[0].getX() - points[1].getX());
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int xDistance = Math.abs(points[i].getX() - points[j].getX());
                int yDistance = Math.abs(points[i].getY() - points[j].getY());
                if (xDistance != 0 && xDistance != checkdistance && yDistance != 0 && yDistance != checkdistance)
                    return false;
            }
        }
        return true;
    }
}
