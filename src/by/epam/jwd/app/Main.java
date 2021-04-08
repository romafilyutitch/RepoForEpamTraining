package by.epam.jwd.app;


import by.epam.jwd.model.Figure;
import by.epam.jwd.model.FigureFactory;
import by.epam.jwd.model.LineFactory;
import by.epam.jwd.model.MultiAngleFigureFactory;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.SquareFactory;
import by.epam.jwd.model.TriangleFactory;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.SimpleLogService;
import by.epam.jwd.validation.LineValidationStrategy;
import by.epam.jwd.validation.MultiAngleFigureValidationStrategy;
import by.epam.jwd.validation.SquareValidationStrategy;
import by.epam.jwd.validation.TriangleValidationStrategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LogService logService = SimpleLogService.INSTANCE;
        Point[] points = {Point.newInstance(1, 1), Point.newInstance(1, 5), Point.newInstance(2, 1), Point.newInstance(2, 2)};
        int pointsIndex = 0;
        do {
            logService.log(points[pointsIndex]);
            pointsIndex++;
        } while (pointsIndex != points.length);
        FigureFactory factory = LineFactory.INSTANCE;
        Figure[] lines = {factory.getFigure(points[0], points[0]), factory.getFigure(points[0], points[2])};
        factory = TriangleFactory.INSTANCE;
        Figure[] triangles = {factory.getFigure(points[0], points[1], points[2]), factory.getFigure(points[0], points[2], points[3])};
        factory = SquareFactory.INSTANCE;
        Figure[] squares = {factory.getFigure(points[0], points[1], points[2], points[3])};
        logService.setValidationStrategy(LineValidationStrategy.INSTANCE);
        for (Figure element : lines) {
            logService.log(element);
        }
        logService.setValidationStrategy(TriangleValidationStrategy.INSTANCE);
        for (Figure element : triangles) {
            logService.log(element);
        }
        logService.setValidationStrategy(SquareValidationStrategy.INSTANCE);
        for (Figure element : squares) {
            logService.log(element);
        }

        logService.setValidationStrategy(MultiAngleFigureValidationStrategy.INSTANCE);

        factory = MultiAngleFigureFactory.INSATNCE;
        Figure fourAngleFigure = factory.getFigure(points);
        logService.log(fourAngleFigure);

        points = Arrays.copyOf(points, points.length + 1);
        points[points.length - 1] = Point.newInstance(2, 1);
        Figure fiveAngleFigure = factory.getFigure(points);
        logService.log(fiveAngleFigure);

        points = Arrays.copyOf(points, points.length + 1);
        points[points.length - 1] = Point.newInstance(4, 1);
        Figure sixAngleFigure = factory.getFigure(points);
        logService.log(sixAngleFigure);

    }
}
