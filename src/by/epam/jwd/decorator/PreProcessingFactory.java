package by.epam.jwd.decorator;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePreProcessor;
import by.epam.jwd.service.impl.FigurePreProcessorImpl;

import java.util.Collection;
import java.util.LinkedList;

public class PreProcessingFactory extends FigureFigureDecorator {
    private final Collection<FigurePreProcessor> preProcessors = new LinkedList<>();

    public PreProcessingFactory(FigureFactory factory) {
        super(factory);
        addFigurePreProcessor(FigurePreProcessorImpl.INSTANCE);
    }

    public boolean addFigurePreProcessor(FigurePreProcessor preProcessor) {
        return preProcessors.add(preProcessor);
    }

    public boolean removePreProcessor(FigurePreProcessor preProcessor) {
        return preProcessors.remove(preProcessor);
    }

    @Override
    public Figure createFigure(FigureType type, Point... figureConstituents) throws FigureException {
        for (FigurePreProcessor preProcessor : preProcessors) {
            preProcessor.process(type, figureConstituents);
        }
        return super.createFigure(type, figureConstituents);
    }
}
