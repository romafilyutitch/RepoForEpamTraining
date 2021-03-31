package by.epam.jwd.entity;


import java.util.Arrays;

class Triangle extends Figure {
    public Triangle(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return "Triangle{ point = " + Arrays.toString(getPoints()) + "}";
    }
}
