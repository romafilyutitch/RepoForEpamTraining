package by.epam.jwd.decorator;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public class PreProcessingFactory extends FigureFigureDecorator {

    public PreProcessingFactory(FigureFactory factory) {
        super(factory);
    }

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        if (type == FigureType.MULTI_ANGLE_FIGURE) {
            if (figureConstituents.length < type.getAmountOfPoints() - 1 || figureConstituents.length > type.getAmountOfPoints() + 1) {
                throw new IllegalArgumentException("figure constituents for figure " + type + " is not valid, must be >= " + (type.getAmountOfPoints() - 1) + " and <= " + (type.getAmountOfPoints() + 1));
            }
        } else {
            if (type.getAmountOfPoints() != figureConstituents.length) {
                throw new IllegalArgumentException("figure constituents amount for figure" + type + " is not valid, must be " + type.getAmountOfPoints());
            }
        }
        return super.createFigure(type, figureConstituents);
    }
}
