package by.epam.jwd.model;

import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.strategy.impl.LinePropertiesStrategy;
import by.epam.jwd.strategy.impl.MultiAngleFigurePropertiesStrategy;
import by.epam.jwd.strategy.impl.SquarePropertiesStrategy;
import by.epam.jwd.strategy.impl.TrianglePropertiesStrategy;

public enum SimpleFigureFactory implements FigureFactory {
    INSTANCE;

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) {
        switch (type) {
            case LINE:
                return new Line(figureConstituents, LinePropertiesStrategy.getInstance());
            case TRIANGLE:
                return new Triangle(figureConstituents, TrianglePropertiesStrategy.INSTANCE);
            case SQUARE:
                return new Square(figureConstituents, SquarePropertiesStrategy.getInstance());
            case MULTI_ANGLE_FIGURE:
                return new MultiAngleFigure(figureConstituents, MultiAngleFigurePropertiesStrategy.getInstance());
            default:
                throw new EnumConstantNotPresentException(FigureType.class, type.name());
        }
    }
}
