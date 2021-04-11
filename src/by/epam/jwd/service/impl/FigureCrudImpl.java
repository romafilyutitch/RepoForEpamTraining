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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FigureCrudImpl implements FigureCrud {
    INSTANCE;
    private final LogService logService = SimpleLogService.INSTANCE;
    private final Storage storage = new SimpleStorage();
    private final FigureFactory factory = ApplicationContext.INSTANCE.getFigureFactory();
    private final Collection<Predicate<Figure>> criteriaPredicates = new LinkedList<>();

    @Override
    public boolean create(FigureType type, Point... figureConstituents) {
        boolean created = false;
        try {
            Figure newInstance = factory.createFigure(type, figureConstituents);
            logService.info(newInstance);
            storage.save(newInstance);
            created = true;
        } catch (FigureException e) {
            logService.error(e.getMessage());
        }
        return created;
    }

    @Override
    public boolean multiCreate(FigureType type, Collection<Point[]> figuresConstituents) {
        boolean created = false;
        for (Point[] figureConstituent : figuresConstituents) {
            if (create(type, figureConstituent)) {
                created = true;
            }
        }
        return created;
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
    public List<Figure> buildResultByCriteria() {
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
