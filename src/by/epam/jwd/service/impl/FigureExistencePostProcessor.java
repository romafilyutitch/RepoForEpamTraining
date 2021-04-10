package by.epam.jwd.service.impl;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.exception.FigureNotExistException;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePostProcessor;

public enum FigureExistencePostProcessor implements FigurePostProcessor {
    INSTANCE;
    @Override
    public Figure process(Figure figure) throws FigureException {
        Point[] points = figure.getPoints();
        if (checkIfContainsSamePoints(figure)) {
            throw new FigureNotExistException("Object " + figure + " is not figure " + figure.getClass().getSimpleName());
        }
        if (hasSameX(points) || hasSameY(points)) {
            throw new FigureException("Object " + figure + " can not be " + figure.getClass().getSimpleName());
        }
        if (isFourAngleFigure(figure) && !isSquare(points)) {
                throw new FigureException("Object " + figure + " can not be Square");
        }
        return figure;
    }

    private boolean checkIfContainsSamePoints(Figure figure) {
        Point[] points = figure.getPoints();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].equals(points[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasSameX(Point[] points) {
        final int xCoord = points[0].getX();
        int amountOfPointsOnSameX = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getX() == xCoord) {
                amountOfPointsOnSameX++;
            }
        }
        return amountOfPointsOnSameX == points.length;
    }

    private boolean hasSameY(Point[] points) {
        final int yCoord = points[0].getY();
        int amountOfPointsOnSameY = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() == yCoord) {
                amountOfPointsOnSameY++;
            }
        }
        return amountOfPointsOnSameY == points.length;
    }

    private boolean isSquare(Point[] points) {
        final int checkdistance = Math.abs(points[0].getX() - points[1].getX());
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int xDistance = Math.abs(points[i].getX() - points[j].getX());
                int yDistance = Math.abs(points[i].getY() - points[j].getY());
                if (xDistance != 0 && xDistance != checkdistance && yDistance != 0 && yDistance != checkdistance)
                    return false;
            }
        }
        return true;
    }

    private boolean isFourAngleFigure(Figure figure) {
        return figure.getPoints().length == 4;
    }
}
