package by.epam.jwd.factory;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public interface FigureFactory {
    /**
     * Creates figure
     * @param type type new of figure
     * @param figureConstituents array of new figure points
     * @return new instance of figure
     * @throws FigureException
     */
    Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException;
}
