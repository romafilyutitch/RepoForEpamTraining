package by.epam.jwd.app;


import by.epam.jwd.exception.FigureException;
import by.epam.jwd.factory.ApplicationContext;
import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigureCrud;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.SimpleLogService;
import by.epam.jwd.service.impl.FigureCrudImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FigureCrud crudService = new FigureCrudImpl();
        LogService logService = SimpleLogService.INSTANCE;
        List<Point> points = new ArrayList<>();
        points.add(Point.newInstance(1, 1));
        points.add(Point.newInstance(1, 5));
        points.add(Point.newInstance(2, 1));
        points.add(Point.newInstance(2, 2));
        Iterator<Point> pointsIterator = points.iterator();
        do {
            logService.info(pointsIterator.next());
        } while (pointsIterator.hasNext());
        List<Figure> lines = new ArrayList<>();
        List<Figure> triangles = new ArrayList<>();
        List<Figure> squares = new ArrayList<>();
        List<Figure> multiAngleFigures = new ArrayList<>();

        lines.add(crudService.create(FigureType.LINE, points.get(0),points.get(0)));
        lines.add(crudService.create(FigureType.LINE, points.get(0), points.get(2)));

        triangles.add(crudService.create(FigureType.TRIANGLE, points.get(0), points.get(1), points.get(2)));
        triangles.add(crudService.create(FigureType.TRIANGLE, points.get(0), points.get(2), points.get(3)));

        squares.add(crudService.create(FigureType.SQUARE, points.get(0), points.get(1), points.get(2), points.get(3)));

        multiAngleFigures.add(crudService.create(FigureType.MULTI_ANGLE_FIGURE, points.get(0), points.get(1), points.get(2), points.get(3)));
        multiAngleFigures.add(crudService.create(FigureType.MULTI_ANGLE_FIGURE, points.get(0), points.get(1), points.get(2), points.get(3), Point.newInstance(4, 4)));
        multiAngleFigures.add(crudService.create(FigureType.MULTI_ANGLE_FIGURE, points.get(2), points.get(0), points.get(1), points.get(3), Point.newInstance(3, 4), Point.newInstance(4, 4)));
    }
}
