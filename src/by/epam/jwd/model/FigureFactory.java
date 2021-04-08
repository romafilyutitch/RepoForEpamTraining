package by.epam.jwd.model;

public interface FigureFactory {
    /**
     * Get figure
     *
     * @param points array of points
     * @return instance of particular subclass of {@code Figure} class
     */
    Figure getFigure(Point... points);
}
