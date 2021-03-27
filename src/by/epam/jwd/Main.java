package by.epam.jwd;

import by.epam.jwd.entity.Line;
import by.epam.jwd.entity.Point;
import by.epam.jwd.entity.Square;
import by.epam.jwd.entity.Triangle;
import by.epam.jwd.service.LogService;
import by.epam.jwd.validation.SquareValidator;
import by.epam.jwd.validation.TriangleValidator;
import by.epam.jwd.validation.Validator;

public class Main {
    public static void main(String[] args) {
        Validator linesValidator = Validator.getInstance();
        LogService logService = LogService.getInstance(linesValidator);
        Point[] points  = {Point.getInstance(1,1), Point.getInstance(1,5), Point.getInstance(2,1), Point.getInstance(2,2)};
        Line[] lines = {Line.getInstance("first Line", points[0],points[0]), Line.getInstance("second Line",points[0],points[2])};
        Triangle[] triangles = {Triangle.getInstance("first Triangle",points[0],points[1],points[2]),
                Triangle.getInstance("second Triangle",points[0],points[2],points[3])};
        Square[] squares = {Square.getInstance("Single square",points[0],points[1],points[2],points[3])};
        int pointsIndex = 0;
        do{
            logService.log(points[pointsIndex]);
            pointsIndex++;
        }while(pointsIndex!=points.length);
        for(Line element : lines){
            logService.log(element);
        }
        logService.setValidator(TriangleValidator.getInstance());
        for (Triangle element : triangles ){
            logService.log(element);
        }
        logService.setValidator(SquareValidator.getInstance());
        for (Square element : squares){
            logService.log(element);
        }
    }
}
