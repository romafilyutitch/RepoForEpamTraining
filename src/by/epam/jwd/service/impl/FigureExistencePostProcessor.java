package by.epam.jwd.service.impl;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.exception.FigureNotExistException;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePostProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FigureExistencePostProcessor implements FigurePostProcessor {
    INSTANCE;
    @Override
    public Figure process(Figure figure) throws FigureException {
        if (checkIfContainsSamePoints(figure)) {
            throw new FigureNotExistException("Object " + figure + " is not figure " + figure.getClass().getSimpleName());
        }
        if (hasSameX(figure) || hasSameY(figure)) {
            throw new FigureException("Object " + figure + " can not be " + figure.getClass().getSimpleName());
        }
        if (isFourAngleFigure(figure) && !isSquare(figure)) {
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

    private boolean hasSameX(Figure figure) {
        Point[] points = figure.getPoints();
        final int xCoord = points[0].getX();
        int amountOfPointsOnSameX = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getX() == xCoord) {
                amountOfPointsOnSameX++;
            }
        }
        return amountOfPointsOnSameX == points.length;
    }

    private boolean hasSameY(Figure figure) {
        Point[] points = figure.getPoints();
        final int yCoord = points[0].getY();
        int amountOfPointsOnSameY = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() == yCoord) {
                amountOfPointsOnSameY++;
            }
        }
        return amountOfPointsOnSameY == points.length;
    }

    private boolean isSquare(Figure figure) {
        List<Double> lengths = new ArrayList<>();
        Point[] points = figure.getPoints();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                lengths.add(getLength(points[i], points[j]));
            }
        }
        Collections.sort(lengths);
        return lengths.get(0).equals(lengths.get(3)) && lengths.get(4).equals(lengths.get(5));
    }

    private double getLength(Point onePoint, Point otherPoint) {
        double length = Math.sqrt(Math.pow(onePoint.getX() - otherPoint.getX(), 2) + Math.pow(onePoint.getY() - otherPoint.getY(), 2));
        if (length >= 0) {
            return length;
        }
        return Math.abs(length);


    }

    private boolean isFourAngleFigure(Figure figure) {
        return figure.getPoints().length == 4;
    }
}
