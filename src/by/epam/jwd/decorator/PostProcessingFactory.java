package by.epam.jwd.decorator;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePostProcessor;
import by.epam.jwd.service.impl.FigureExistencePostProcessor;

public class PostProcessingFactory extends FigureFigureDecorator {
    private final FigurePostProcessor processor = FigureExistencePostProcessor.INSTANCE;

    public PostProcessingFactory(FigureFactory factory) {
        super(factory);
    }

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        Figure newInstance = super.createFigure(type, figureConstituents);
        processor.process(newInstance);
        return newInstance;
    }
}
