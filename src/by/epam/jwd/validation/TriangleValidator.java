package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public class TriangleValidator extends Validator{
    TriangleValidator(){}
    public static TriangleValidator getInstance(){
        return new TriangleValidator();
    }
    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotFigureException, IsNotSquareException {
        super.check(figure);
        Point[] points = figure.getPoints();
        if(isSameX(points) || isSameY(points)){
            throw new CanNotExistException();
        }
    }
}
