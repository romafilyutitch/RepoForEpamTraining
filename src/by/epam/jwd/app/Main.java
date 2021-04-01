package by.epam.jwd.app;


import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.FigureFactory;
import by.epam.jwd.entity.LineFactory;
import by.epam.jwd.entity.Point;
import by.epam.jwd.entity.SquareFactory;
import by.epam.jwd.entity.TriangleFactory;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.SimpleLogService;
import by.epam.jwd.validation.LineValidationStrategy;
import by.epam.jwd.validation.SquareValidationStrategy;
import by.epam.jwd.validation.TriangleValidationStrategy;

public class Main {
    public static void main(String[] args) {
        LogService logService = new SimpleLogService();
        Point[] points = {Point.newInstance(1, 1), Point.newInstance(1, 5), Point.newInstance(2, 1), Point.newInstance(2, 2)};
        int pointsIndex = 0;
        do {
            logService.log(points[pointsIndex]);
            pointsIndex++;
        } while (pointsIndex != points.length);
        FigureFactory factory = new LineFactory();
        Figure[] lines = {factory.getFigure(points[0], points[0]), factory.getFigure(points[0], points[2])};
        factory = new TriangleFactory();
        Figure[] triangles = {factory.getFigure(points[0], points[1], points[2]), factory.getFigure(points[0], points[2], points[3])};
        factory = new SquareFactory();
        Figure[] squares = {factory.getFigure(points[0], points[1], points[2], points[3])};
        logService.setValidationStrategy(new LineValidationStrategy());
        for (Figure element : lines) {
            logService.log(element);
        }
        logService.setValidationStrategy(new TriangleValidationStrategy());
        for (Figure element : triangles) {
            logService.log(element);
        }
        logService.setValidationStrategy(new SquareValidationStrategy());
        for (Figure element : squares) {
            logService.log(element);
        }
    }
}
