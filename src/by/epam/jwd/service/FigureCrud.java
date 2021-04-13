package by.epam.jwd.service;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.impl.Criteria;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface FigureCrud {
    /**
     * Creates and save new figure in storage
     * @param type type of new figure
     * @param figureConstituents array of points for new figure
     * @param <T> type of figure type enumeration
     * @param <R> type of new figure instance
     * @return new figure
     */
    <T extends FigureType, R extends Figure> R create(T type, Point... figureConstituents);

    /**
     * Creates and saves multiple new figures in storage
     * @param type type of new figure
     * @param figureConstituents array of points for new figure
     * @param <T> type from FigureType enumeration
     * @param <R> type of returned fogure
     * @return Collection of new figures
     */
    <T extends FigureType, R extends Figure> Collection<R> multiCreate(T type, Collection<Point[]> figureConstituents);

    /**
     * Deletes figures from storage
     * @param predicate condition of deleting
     */
    void deleteIf(Predicate<Figure> predicate);

    /**
     * Deletes all figures from storage
     */
    void deleteAll();

    /**
     * Returns collection of filtered figures
     * @param predicate condition of filtering
     * @return collection of filtered figures
     */
    Collection<Figure> findAll(Predicate<Figure> predicate);

    /**
     * Returns any figure, that match predicate
     * @param predicate condition of filtering
     * @return any matching figure
     */
    Optional<Figure> findAny(Predicate<Figure> predicate);

    /**
     * Updates figures, that match filter condition
     * @param predicate filtering condition
     * @param consumer update action
     */
    void updateIf(Predicate<Figure> predicate, Consumer<Figure> consumer);

    /**
     * Updates all figures
     * @param consumer update action
     */
    void updateAll(Consumer<Figure> consumer);
    /**
     * Returns figure, that has passed id
     * @param id id of figure
     * @return {@code Optional<Figure>} object that may contain figure object
     */
    Optional<Figure> findById(long id);

    /**
     * Builds result based on chain of criteria
     * @return Collection of figures that match all conditions
     */
    Collection<Figure> findByCriteria(Criteria criteria);
}
