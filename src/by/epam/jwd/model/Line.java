package by.epam.jwd.model;


import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

class Line extends Figure {

    Line(Point[] points, FigurePropertiesStrategy strategy) {
        super(points, strategy);
    }

    @Override
    public String toString() {
        return "Line { id = " + getId() + ", points = "
                + Arrays.toString(getPoints())
                + ", Perimeter is " + getPerimeter()
                + ", square is " + getSquare() + " }";
    }
}