package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;


class SquareValidationStrategy implements ValidationStrategy {

    @Override
    public void check(Figure figure) throws IsNotSquareException, IsNotFigureException {
        checkPoints(figure.getPoints());
        Point[] points = figure.getPoints();
        if(isSameX(points) || isSameX(points) || !isSquare(points)){
            throw new IsNotSquareException();
        }
    }

    private boolean isSquare(Point[] points){
        final int checkdistance = Math.abs(points[0].getX() - points[1].getX());
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                int xDistance = Math.abs(points[i].getX() - points[j].getX());
                int yDistance = Math.abs(points[i].getY() - points[j].getY());
                if(xDistance != 0 && xDistance != checkdistance && yDistance != 0 && yDistance != checkdistance)
                    return false;
            }
        }
        return true;
    }
}
