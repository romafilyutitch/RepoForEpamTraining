package by.epam.jwd.entity;

public class SquareFactory implements FigureFactory {
    private static final String WRONG_ARRAY_LENGTH_MESSAGE = "array length must be 4";
    @Override
    public Figure getFigure(Point... points) {
        if(points.length != 4){
            throw new IllegalArgumentException(WRONG_ARRAY_LENGTH_MESSAGE);
        }
        return new Square(points);
    }
}
