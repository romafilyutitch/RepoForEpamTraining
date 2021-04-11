package by.epam.jwd.factory;

import by.epam.jwd.decorator.PostProcessingFactory;
import by.epam.jwd.decorator.PreProcessingFactory;
import by.epam.jwd.model.SimpleFigureFactory;

public enum ApplicationContext {
    INSTANCE;

    /**
     * Wraps figure factory in decorators
     * @return wrapped figure factory
     */
    public FigureFactory getFigureFactory() {
        return new PostProcessingFactory(new PreProcessingFactory(SimpleFigureFactory.INSTANCE));
    }
}
