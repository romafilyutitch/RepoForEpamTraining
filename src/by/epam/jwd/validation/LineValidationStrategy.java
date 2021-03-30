package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public class LineValidationStrategy implements ValidationStrategy {

    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotSquareException, IsNotFigureException {
        checkIfContainsSamePoints(figure);
    }
}
