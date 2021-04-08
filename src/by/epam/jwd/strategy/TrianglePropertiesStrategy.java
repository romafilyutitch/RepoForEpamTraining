package by.epam.jwd.strategy;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;

public enum TrianglePropertiesStrategy implements FigurePropertiesStrategy {
    INSTANCE;

    @Override
    public double getPerimeter(Figure figure) {
        Point[] points = figure.getPoints();
        double a = getLineLength(points[0], points[1]);
        double b = getLineLength(points[1], points[2]);
        double c = getLineLength(points[0], points[2]);
        return a + b + c;
    }

    @Override
    public double getSquare(Figure figure) {
        Point[] points = figure.getPoints();
        double halfPerimeter = getPerimeter(figure) / 2;
        double a = getLineLength(points[0], points[1]);
        double b = getLineLength(points[1], points[2]);
        double c = getLineLength(points[0], points[2]);
        return Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
    }
}
