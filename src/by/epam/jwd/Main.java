package by.epam.jwd;

import by.epam.jwd.entity.Line;
import by.epam.jwd.entity.Point;
import by.epam.jwd.entity.Square;
import by.epam.jwd.entity.Triangle;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.SimpleLogServiceFactory;
import by.epam.jwd.validation.ValidationStrategy;
import by.epam.jwd.validation.Figure;
import by.epam.jwd.validation.SimpleValidationStrategyFactory;
import by.epam.jwd.validation.ValidationStrategyFactory;

public class Main {
    public static void main(String[] args) {
        LogService logService = SimpleLogServiceFactory.newInstance().getLogService();
        ValidationStrategyFactory validationStrategyFactory = SimpleValidationStrategyFactory.newInstance();
        ValidationStrategy lineValidation = validationStrategyFactory.getValidationStrategy(Figure.LINE);
        ValidationStrategy triangleValidation = validationStrategyFactory.getValidationStrategy(Figure.TRIANGLE);
        ValidationStrategy squareValidation = validationStrategyFactory.getValidationStrategy(Figure.SQUARE);

        Point[] points  = {Point.newInstance(1,1), Point.newInstance(1,5), Point.newInstance(2,1), Point.newInstance(2,2)};
        Line[] lines = {Line.newInstance("first Line", points[0],points[0]), Line.newInstance("second Line",points[0],points[2])};
        Triangle[] triangles = {Triangle.newInstance("first Triangle",points[0],points[1],points[2]),
                Triangle.newInstance("second Triangle",points[0],points[2],points[3])};
        Square[] squares = {Square.newInstance("Single square",points[0],points[1],points[2],points[3])};

        int pointsIndex = 0;
        do{
            logService.log(points[pointsIndex]);
            pointsIndex++;
        }while(pointsIndex!=points.length);

        logService.setValidationStrategy(lineValidation);
        for(Line element : lines){
            logService.log(element);
        }
        logService.setValidationStrategy(triangleValidation);
        for(Triangle element : triangles){
            logService.log(element);
        }

        logService.setValidationStrategy(squareValidation);
        for(Square element: squares){
            logService.log(element);
        }
    }
}
