package by.epam.jwd.validation;

import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public enum MultiAngleFigureValidationStrategy implements ValidationStrategy {
    INSTANCE;

    private static final String CAN_NOT_EXIST_MESSAGE = "MultiAngleFigure %s can not exist";

    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotFigureException {
        checkIfContainsSamePoints(figure);
        Point[] points = figure.getPoints();
        if (isSameX(points) || isSameY(points)) {
            throw new CanNotExistException(String.format(CAN_NOT_EXIST_MESSAGE, figure));
        }
    }
}
