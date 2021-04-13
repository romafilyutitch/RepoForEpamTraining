package by.epam.jwd.service.impl;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.ApplicationContext;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigureCrud;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FigureCrudImpl implements FigureCrud {
    INSTANCE;
    private final LogService logService = SimpleLogService.INSTANCE;
    private final Storage<Figure> storage = new FigureStorage();
    private final FigureFactory factory = ApplicationContext.INSTANCE.getFigureFactory();

    @Override
    public <T extends FigureType, R extends Figure> R create(T type, Point... figureConstituents) {
        R newInstance = null;
        try {
            newInstance = (R) factory.createFigure(type, figureConstituents);
            logService.info(newInstance);
            storage.save(newInstance);
        } catch (FigureException e) {
            logService.error(e.getMessage());
        }
        return newInstance;
    }

    @Override
    public <T extends FigureType, R extends Figure> Collection<R> multiCreate(T type, Collection<Point[]> figuresConstituents) {
        Collection<R> figures = new ArrayList<>();
        for (Point[] figureConstituent : figuresConstituents) {
            R newFigure = create(type, figureConstituent);
            if (newFigure != null) {
                figures.add(newFigure);
            }
        }
        return figures;
    }

    @Override
    public void deleteIf(Predicate<Figure> predicate) {
        storage.getStream().filter(predicate).forEach(storage::remove);
    }

    @Override
    public void deleteAll() {
        storage.getStream().forEach(storage::remove);
    }

    @Override
    public Optional<Figure> findAny(Predicate<Figure> predicate) {
        return storage.getStream().filter(predicate).findAny();
    }

    @Override
    public Collection<Figure> findAll(Predicate<Figure> predicate) {
        return storage.getStream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void updateIf(Predicate<Figure> predicate, Consumer<Figure> consumer) {
        storage.getStream().filter(predicate).forEach(consumer);
    }

    @Override
    public void updateAll(Consumer<Figure> consumer) {
        storage.getStream().forEach(consumer);
    }

    @Override
    public Optional<Figure> findById(long id) {
        return findAny(figure -> figure.getId() == id);
    }

    @Override
    public Collection<Figure> findByCriteria(Criteria criteria) {
        Collection<Predicate<Figure>> predicates = criteria.getCriteriaPredicates();
        Stream<Figure> stream = storage.getStream();
        for (Predicate<Figure> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        return stream.collect(Collectors.toList());
    }
}
