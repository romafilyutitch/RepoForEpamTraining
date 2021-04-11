package by.epam.jwd.strategy.impl;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.strategy.FigurePropertiesStrategy;

public class SquarePropertiesStrategy implements FigurePropertiesStrategy {
    private static final SquarePropertiesStrategy instance = new SquarePropertiesStrategy();

    private SquarePropertiesStrategy() {
    }

    public static SquarePropertiesStrategy getInstance() {
        return instance;
    }

    @Override
    public double getPerimeter(Figure figure) {
        Point[] points = figure.getPoints();
        double a = getLineLength(points[0], points[1]);
        return a * 4;

    }

    @Override
    public double getSquare(Figure figure) {
        Point[] points = figure.getPoints();
        double a = getLineLength(points[0], points[1]);
        return Math.pow(a, 2);
    }
}
