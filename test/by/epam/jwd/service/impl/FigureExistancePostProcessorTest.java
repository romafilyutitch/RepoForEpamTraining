package by.epam.jwd.service.impl;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.exception.FigureNotExistException;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.SimpleFigureFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FigureExistancePostProcessorTest {

    @Test
    public void testProcess_processesRight_whenValidLineIsPassed() {
        Point[] points = {Point.newInstance(0,0), Point.newInstance(1,1)};
        Figure line = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE, points);
        try {
            FigureExistencePostProcessor.INSTANCE.process(line);
        } catch (FigureException e) {
            Assert.fail();
        }
    }

    @Test
    public void testProcess_processesRight_whenValidTriangleIsPassed() {
        Point[] points = {Point.newInstance(0,0), Point.newInstance(1,1), Point.newInstance(1,0)};
        Figure triangle = SimpleFigureFactory.INSTANCE.createFigure(FigureType.TRIANGLE, points);
        try {
            FigureExistencePostProcessor.INSTANCE.process(triangle);
        } catch (FigureException e) {
            Assert.fail();
        }
    }

    @Test
    public void testProcess_processesRight_whenValidSquareIsPassed() {
        Point[] points = {Point.newInstance(0,0), Point.newInstance(1,0), Point.newInstance(0,1), Point.newInstance(1,1)};
        Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);
        try {
            FigureExistencePostProcessor.INSTANCE.process(square);
        } catch (FigureException e) {
            Assert.fail();
        }
    }

    @Test
    public void testProcess_throwsFigureException_whenInvalidSquareIsPassed() {
        Point[] points = {Point.newInstance(0,0), Point.newInstance(2,0), Point.newInstance(0,1), Point.newInstance(2,1)};
        Figure rectangle = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);
        Assert.assertThrows(FigureException.class, () -> FigureExistencePostProcessor.INSTANCE.process(rectangle));
    }

    @Test
    public void testProcess_processedRight_whenValidMultiAngleFigureIsPassed() {
        Point[] points = {
                Point.newInstance(0,0),
                Point.newInstance(1,1),
                Point.newInstance(3,3),
                Point.newInstance(3,0),
                Point.newInstance(1,0)
        };
        Figure multiAngleFigure = SimpleFigureFactory.INSTANCE.createFigure(FigureType.MULTI_ANGLE_FIGURE, points);
        try {
            FigureExistencePostProcessor.INSTANCE.process(multiAngleFigure);
        } catch (FigureException e) {
            Assert.fail();
        }
    }

    @Test
    public void testProcess_throwsFigureNotExistException_whenPassedSamePoints() {
        Point[] points = {Point.newInstance(1,1), Point.newInstance(1,1), Point.newInstance(2,2), Point.newInstance(3,1)};
        Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);
        Assert.assertThrows(FigureNotExistException.class, ()-> FigureExistencePostProcessor.INSTANCE.process(square));
    }

    @Test
    public void testProcess_throwsFigureException_whenFigureHasSameX() {
        Point[] points = {Point.newInstance(1,1), Point.newInstance(1,2), Point.newInstance(1,3), Point.newInstance(1,5)};
        Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);
        Assert.assertThrows(FigureException.class, () -> FigureExistencePostProcessor.INSTANCE.process(square));
    }

    @Test
    public void testProcess_throwsFigureException_whenFigureHasSameY() {
        Point[] points  = {Point.newInstance(1,1), Point.newInstance(2,1), Point.newInstance(3,1), Point.newInstance(4,1)};
        Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);
        Assert.assertThrows(FigureException.class, () -> FigureExistencePostProcessor.INSTANCE.process(square));
    }
}
