package by.epam.jwd.strategy.impl;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.strategy.FigurePropertiesStrategy;

public class LinePropertiesStrategy implements FigurePropertiesStrategy {
    private static LinePropertiesStrategy instance;

    private LinePropertiesStrategy() {
    }

    public static LinePropertiesStrategy getInstance() {
        if (instance == null) {
            instance = new LinePropertiesStrategy();
        }
        return instance;
    }

    @Override
    public double getPerimeter(Figure figure) {
        Point[] points = figure.getPoints();
        return getLineLength(points[0], points[1]);
    }

    @Override
    public double getSquare(Figure figure) {
        return 0;
    }
}
