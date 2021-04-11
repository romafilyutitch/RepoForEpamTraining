package by.epam.jwd.decorator;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public abstract class FigureFigureDecorator implements FigureFactory {
    private FigureFactory factory;

    public FigureFigureDecorator(FigureFactory factory) {
        this.factory = factory;
    }

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        return factory.createFigure(type, figureConstituents);
    }
}
