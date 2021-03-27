package by.epam.jwd.validation;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;

public class Validator {
    Validator(){}

    public void check(Figure figure) throws CanNotExistException, IsNotSquareException, IsNotFigureException {
        Point[] points = figure.getPoints();
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if(points[i].equals(points[j])){
                    throw new IsNotFigureException();
                }
            }
        }
    }

    public static Validator getInstance(){
        return new Validator();
    }

    boolean isSameX(Point[] points){
        final int xCoord = points[0].getX();
        int amountOfPointsOnSameX = 0;
        for (int i = 1; i < points.length; i++){
            if (points[i].getX() == xCoord){
                amountOfPointsOnSameX++;
            }
        }
        return amountOfPointsOnSameX == points.length;
    }

    boolean isSameY(Point[] points){
        final int yCoord = points[0].getY();
        int amountOfPointsOnSameY = 0;
        for (int i = 1; i < points.length; i++) {
            if(points[i].getY() == yCoord){
                amountOfPointsOnSameY++;
            }
        }
        return amountOfPointsOnSameY == points.length;
    }
}
