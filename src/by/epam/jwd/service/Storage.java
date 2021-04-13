package by.epam.jwd.service;

import java.util.stream.Stream;

public interface Storage<T> {
    /**
     * Saves passed figure in storage
     * @param figure figure to be saved
     * @return {@code true} if figure has been saved
     */
    boolean save(T figure);

    /**
     * Removes passed figure from storage
     * @param figure figure to be removed
     * @return {@code true} if figure has been removed
     */
    boolean remove(T figure);

    /**
     * Returns stream of storage figures
     * @return stream of storage figures
     */
    Stream<T> getStream();
}
