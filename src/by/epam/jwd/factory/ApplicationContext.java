package by.epam.jwd.factory;

import by.epam.jwd.decorator.PostProcessingFactory;
import by.epam.jwd.decorator.PreProcessingFactory;
import by.epam.jwd.model.SimpleFigureFactory;

public enum ApplicationContext {
    INSTANCE;
    public FigureFactory getFigureFactory() {
        FigureFactory simpleFactory = SimpleFigureFactory.INSTANCE;
        simpleFactory = new PreProcessingFactory(simpleFactory);
        simpleFactory = new PostProcessingFactory(simpleFactory);
        return simpleFactory;
    }
}
