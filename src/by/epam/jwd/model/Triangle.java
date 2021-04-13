package by.epam.jwd.model;


import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

public class Triangle extends Figure {
    Triangle(Point[] points, FigurePropertiesStrategy strategy) {
        super(points, strategy);
    }

    @Override
    public String toString() {
        return "Triangle { id = " + getId() + ", points = "
                + Arrays.toString(getPoints())
                + ", Perimeter is " + getPerimeter()
                + ", square is " + getSquare() + " }";
    }
}
