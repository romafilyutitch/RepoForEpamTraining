package by.epam.jwd.validation;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;

public enum TriangleValidationStrategy implements ValidationStrategy {
    INSTANCE;
    private static final String CAN_NOT_EXIST_MESSAGE = "Triangle %s can not exist";

    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotFigureException {
        checkIfContainsSamePoints(figure);
        Point[] points = figure.getPoints();
        if (isSameX(points) || isSameY(points)) {
            throw new CanNotExistException(String.format(CAN_NOT_EXIST_MESSAGE, figure));
        }
    }
}
