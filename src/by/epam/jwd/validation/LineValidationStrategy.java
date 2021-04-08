package by.epam.jwd.validation;

import by.epam.jwd.model.Figure;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public enum LineValidationStrategy implements ValidationStrategy {
    INSTANCE;

    @Override
    public void check(Figure figure) throws CanNotExistException, IsNotSquareException, IsNotFigureException {
        checkIfContainsSamePoints(figure);
    }
}
