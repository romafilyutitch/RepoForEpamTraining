package by.epam.jwd.strategy.impl;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.strategy.FigurePropertiesStrategy;

public enum TrianglePropertiesStrategy implements FigurePropertiesStrategy {
    INSTANCE;

    @Override
    public double getPerimeter(Figure figure) {
        Point[] points = figure.getPoints();
        double firstLineLength = getLineLength(points[0], points[1]);
        double secondLineLength = getLineLength(points[1], points[2]);
        double thirdLineLength = getLineLength(points[0], points[2]);
        return firstLineLength + secondLineLength + thirdLineLength;
    }

    @Override
    public double getSquare(Figure figure) {
        Point[] points = figure.getPoints();
        double halfPerimeter = getPerimeter(figure) / 2;
        double firstLineLength = getLineLength(points[0], points[1]);
        double secondLineLength = getLineLength(points[1], points[2]);
        double thirdLineLength = getLineLength(points[0], points[2]);
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstLineLength) * (halfPerimeter - secondLineLength) * (halfPerimeter - thirdLineLength));
    }
}
