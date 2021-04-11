package by.epam.jwd.strategy.impl;

import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiAngleFigurePropertiesStrategy implements FigurePropertiesStrategy {
    private static final MultiAngleFigurePropertiesStrategy instance = new MultiAngleFigurePropertiesStrategy();

    private MultiAngleFigurePropertiesStrategy() {
    }

    public static MultiAngleFigurePropertiesStrategy getInstance() {
        return instance;
    }

    @Override
    public double getPerimeter(Figure figure) {
        List<Double> lengthList = new ArrayList<>();
        for (int i = 1; i < figure.getPoints().length; i++) {
            lengthList.add(getLineLength(figure.getPoints()[i - 1], figure.getPoints()[i]));
        }
        lengthList.add(getLineLength(figure.getPoints()[0], figure.getPoints()[figure.getPoints().length - 1]));
        double perimetr = 0.0;
        for (Double element : lengthList) {
            perimetr += element;
        }
        return perimetr;
    }

    @Override
    public double getSquare(Figure figure) {
        final List<Point> points = Arrays.asList(figure.getPoints());
        double sumMultipliesFirstXByYResult = getSumMultipliesXByY(points);
        double sumMultipliesFirstYByXResult = getSumMultipliesYByX(points);
        double substractionResult = Math.abs(sumMultipliesFirstXByYResult - sumMultipliesFirstYByXResult);
        return substractionResult / 2;
    }

    private double getSumMultipliesYByX(List<Point> points) {
        final List<Double> multipleResults = new ArrayList<>();
        double result;
        for (int i = 0; i < points.size() - 1; i++) {
            result = points.get(i).getY() * points.get(i + 1).getX();
            multipleResults.add(result);
        }
        return sumMultipleResults(multipleResults);
    }

    private double getSumMultipliesXByY(List<Point> points) {
        final List<Double> multipleResults = new ArrayList<>();
        double result;
        for (int i = 0; i < points.size() - 1; i++) {
            result = points.get(i).getX() * points.get(i + 1).getY();
            multipleResults.add(result);
        }
        return sumMultipleResults(multipleResults);
    }

    private double sumMultipleResults(List<Double> multipleResults) {
        double sum = 0.0;
        for (Double result : multipleResults) {
            sum += result;
        }
        return sum;
    }
}
