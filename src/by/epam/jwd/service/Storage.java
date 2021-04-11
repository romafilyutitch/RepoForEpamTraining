package by.epam.jwd.service;

import by.epam.jwd.model.Figure;

import java.util.stream.Stream;

public interface Storage {
    /**
     * Saves passed figure in storage
     * @param figure figure to be saved
     * @return {@code true} if figure has been saved
     */
    boolean save(Figure figure);

    /**
     * Removes passed figure from storage
     * @param figure figure to be removed
     * @return {@code true} if figure has been removed
     */
    boolean remove(Figure figure);

    /**
     * Returns stream of storage figures
     * @return stream of storage figures
     */
    Stream<Figure> getStream();
}
