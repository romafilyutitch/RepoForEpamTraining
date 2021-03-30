package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;

class TriangleValidationStrategy implements ValidationStrategy {
    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotFigureException {
        checkPoints(figure.getPoints());
        Point[] points = figure.getPoints();
        if(isSameX(points) || isSameY(points)){
            throw new CanNotExistException();
        }
    }
}
