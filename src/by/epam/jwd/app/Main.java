package by.epam.jwd.app;


import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigureCrud;
import by.epam.jwd.service.LogService;
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

        crudService.multiCreate(FigureType.LINE,
                List.of(new Point[]{Point.newInstance(0, 1), Point.newInstance(0, 2)},
                        new Point[]{Point.newInstance(2, 2), Point.newInstance(3, 4)}));
        crudService.multiCreate(FigureType.TRIANGLE,
                List.of(new Point[]{Point.newInstance(1, 1), Point.newInstance(2, 3), Point.newInstance(4, 5)},
                        new Point[]{Point.newInstance(4, 1), Point.newInstance(5, 5), Point.newInstance(10, 1)}));
        crudService.create(FigureType.SQUARE,
                Point.newInstance(1, 1), Point.newInstance(5, 7), Point.newInstance(8, 3), Point.newInstance(5, 1));
        crudService.create(FigureType.MULTI_ANGLE_FIGURE,
                points.get(0), points.get(1), points.get(2), points.get(3), Point.newInstance(2, 2));
        crudService.create(FigureType.MULTI_ANGLE_FIGURE,
                points.get(3), points.get(2), points.get(1), points.get(0), Point.newInstance(10, 10), Point.newInstance(20, 20));

        logService.printMessage("Results of operations with crudService");
        Collection<Figure> resultCollection = crudService
                .addFindCriterion(figure -> figure.getSquare() > 5)
                .addFindCriterion(figure -> figure.getPerimeter() > 10)
                .addFindCriterion(figure -> figure.getId() < 10)
                .buildResultByCriteria();
        logService.printMessage("Figures that match multiple criteria: ");
        resultCollection.forEach(logService::info);

        resultCollection = crudService.findAll(figure -> figure.getPoint(0).getX() == 1);
        logService.printMessage("Figures that have first point's x axle equal 1:");
        resultCollection.forEach(logService::info);

        logService.printMessage("Result of finding figure with id equal 5:");
        Optional<Figure> result = crudService.findById(5);
        result.ifPresentOrElse(logService::info, () -> logService.printMessage("There is no such figure in storage"));
    }
}
