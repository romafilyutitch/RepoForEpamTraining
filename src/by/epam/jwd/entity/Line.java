package by.epam.jwd.entity;


import java.util.Arrays;

class Line extends Figure {
    public Line(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return "Line{ points = " + Arrays.toString(getPoints()) + "}";
    }
}