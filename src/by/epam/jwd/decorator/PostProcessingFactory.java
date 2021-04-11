package by.epam.jwd.decorator;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePostProcessor;
import by.epam.jwd.service.impl.FigureExistencePostProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class PostProcessingFactory extends FigureFigureDecorator {
    private final Collection<FigurePostProcessor> postProcessors = new ArrayList<>();

    public PostProcessingFactory(FigureFactory factory) {
        super(factory);
        addFigurePostProcessor(FigureExistencePostProcessor.INSTANCE);
    }

    public boolean addFigurePostProcessor(FigurePostProcessor postProcessor) {
        return postProcessors.add(postProcessor);
    }

    public boolean removeFigurePostProcessor(FigurePostProcessor postProcessor) {
        return postProcessors.remove(postProcessor);
    }

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        Figure newInstance = super.createFigure(type, figureConstituents);
        for (FigurePostProcessor postProcessor : postProcessors) {
            postProcessor.process(newInstance);
        }
        return newInstance;
    }
}
