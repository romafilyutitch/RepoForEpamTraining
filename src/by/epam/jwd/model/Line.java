package by.epam.jwd.model;


import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

class Line extends Figure {

    Line(Point[] points, FigurePropertiesStrategy strategy) {
        super(points, strategy);
    }

    @Override
    public String toString() {
        return "Line{ points = " + Arrays.toString(getPoints()) + "}";
    }
}