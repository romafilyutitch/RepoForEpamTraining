package by.epam.jwd.factory;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public interface FigureFactory {
    Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException;
}
