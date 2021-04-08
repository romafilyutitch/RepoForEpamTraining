package by.epam.jwd.model;


import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

class Triangle extends Figure {
    Triangle(Point[] points, FigurePropertiesStrategy strategy) {
        super(points, strategy);
    }

    @Override
    public String toString() {
        return "Triangle{ point = " + Arrays.toString(getPoints()) + "}";
    }
}
