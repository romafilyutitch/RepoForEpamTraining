package by.epam.jwd.service.impl;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.ApplicationContext;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.SimpleFigureFactory;
import by.epam.jwd.service.FigureCrud;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.SimpleLogService;
import by.epam.jwd.service.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FigureCrudImpl implements FigureCrud {
    private LogService logService = SimpleLogService.INSTANCE;
    private Storage storage = new SimpleStorage();
    private FigureFactory factory = ApplicationContext.INSTANCE.getFigureFactory();
    private Collection<Predicate<Figure>> criteriaPredicates = new LinkedList<>();

    @Override
    public Figure create(FigureType type, Point... figureConstituents) {
        try {
            Figure newInstance = factory.createFigure(type, figureConstituents);
            logService.info(newInstance);
            storage.save(newInstance);
            return newInstance;
        } catch (FigureException e) {
            logService.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Figure> multiCreate(int amount, FigureType type, Point... figureConstituents) {
        Collection<Figure> figures = new LinkedList<>();
        for (int i = 0; i < amount; i++) {
            figures.add(create(type, figureConstituents));
        }
        return figures;
    }

    @Override
    public boolean delete(Figure figure) {
        return storage.remove(figure);
    }

    @Override
    public Optional<Figure> find(Predicate<Figure> predicate) {
        return storage.getStream().filter(predicate).findAny();
    }

    @Override
    public void update(Consumer<Figure> consumer) {
        storage.getStream().forEach(consumer);
    }

    @Override
    public Optional<Figure> findById(long id) {
        return find(figure -> figure.getId() == id);
    }

    @Override
    public List<Figure> getResultByCriteria() {
        Stream<Figure> figureStream = storage.getStream();
        for (Predicate<Figure> predicate : criteriaPredicates) {
            figureStream = figureStream.filter(predicate);
        }
        return figureStream.collect(Collectors.toList());
    }

    @Override
    public FigureCrudImpl addFindCriterion(Predicate<Figure> criterion) {
        criteriaPredicates.add(criterion);
        return this;
    }
}
