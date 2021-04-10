package by.epam.jwd.model;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.strategy.LinePropertiesStrategy;
import by.epam.jwd.strategy.SquarePropertiesStrategy;
import by.epam.jwd.strategy.TrianglePropertiesStrategy;

public enum SimpleFigureFactory implements FigureFactory {
    INSTANCE;
    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        switch (type) {
            case LINE:
                return new Line(figureConstituents, LinePropertiesStrategy.getInstance());
            case TRIANGLE:
                return new Triangle(figureConstituents, TrianglePropertiesStrategy.INSTANCE);
            case SQUARE:
                return new Square(figureConstituents, SquarePropertiesStrategy.getInstance());
            case MULTI_ANGLE_FIGURE:
                return new MultiAngleFigure(figureConstituents, SquarePropertiesStrategy.getInstance());
            default:
                throw new EnumConstantNotPresentException(FigureType.class, type.name());
        }
    }
}
