package by.epam.jwd.entity;


import java.util.Arrays;

class Square extends Figure{
    public Square(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return "Square { points = "+ Arrays.toString(getPoints())+"}";
    }
}