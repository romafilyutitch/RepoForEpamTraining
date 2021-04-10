package by.epam.jwd.service;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface FigureCrud {
    Figure create(FigureType type, Point... figureConstituents);
    Collection<Figure> multiCreate(int amount, FigureType type, Point... figureConstituents);
    boolean delete(Figure figure);
    Optional<Figure> find(Predicate<Figure> predicate);
    void update(Consumer<Figure> consumer);
    Optional<Figure> findById(long id);
    List<Figure> getResultByCriteria();
    FigureCrud addFindCriterion(Predicate<Figure> criterion);
}
