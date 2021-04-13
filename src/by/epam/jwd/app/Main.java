package by.epam.jwd.app;


import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Line;
import by.epam.jwd.model.MultiAngleFigure;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.Square;
import by.epam.jwd.model.Triangle;
import by.epam.jwd.service.FigureCrud;
import by.epam.jwd.service.LogService;
import by.epam.jwd.service.impl.Criteria;
import by.epam.jwd.service.impl.SimpleLogService;
import by.epam.jwd.service.impl.FigureCrudImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        FigureCrud crudService = FigureCrudImpl.INSTANCE;
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

        Collection<Line> figures = crudService.multiCreate(FigureType.LINE,
                List.of(new Point[]{Point.newInstance(0, 1), Point.newInstance(0, 2)},
                        new Point[]{Point.newInstance(2, 2), Point.newInstance(3, 4)}));
        Collection<Triangle> triangles = crudService.multiCreate(FigureType.TRIANGLE,
                List.of(new Point[]{Point.newInstance(1, 1), Point.newInstance(2, 3), Point.newInstance(4, 5)},
                        new Point[]{Point.newInstance(4, 1), Point.newInstance(5, 5), Point.newInstance(10, 1)}));
        Square square = crudService.create(FigureType.SQUARE,
                new Point[]{Point.newInstance(0, 1), Point.newInstance(0, 2), Point.newInstance(4,1), Point.newInstance(5,5)});
        crudService.create(FigureType.MULTI_ANGLE_FIGURE,
                points.get(0), points.get(1), points.get(2), points.get(3), Point.newInstance(2, 2));
        crudService.create(FigureType.MULTI_ANGLE_FIGURE,
                points.get(3), points.get(2), points.get(1), points.get(0), Point.newInstance(10, 10), Point.newInstance(20, 20));

        logService.printMessage("Search on multiple criteria ( id > 1 && square > 0)");
        Criteria findCriteria = new Criteria.Builder()
                .whereIdMoreThan(1)
                .whereSquareMoreThan(0)
                .build();
        Collection<Figure> result = crudService.findByCriteria(findCriteria);
        result.forEach(logService::info);
        logService.printMessage("Figure with id equals 0");
        Optional<Figure> optionalResult = crudService.findById(0);
        optionalResult.ifPresentOrElse(logService::info, () -> logService.printMessage("There is no figure with id equals 0"));
        logService.printMessage("Update all figures in storage");
        crudService.updateAll(figure -> logService.info(figure + " UPDATED"));
    }
}
