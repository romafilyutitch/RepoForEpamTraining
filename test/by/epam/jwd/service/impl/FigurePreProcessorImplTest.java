package by.epam.jwd.service.impl;

import by.epam.jwd.factory.FigureFactory;
import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FigurePreProcessorImplTest {

    @Test
    public void testProcess_preProcessLineRight_whenPassedValidFigureTypeAndPoints() {
        final Point[] points = {Point.newInstance(0,0), Point.newInstance(5,5)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.LINE, points);
    }

    @Test
    public void testProcess_preProcessTriangleRight_whenPassedValidFigureTypeAndPoints() {
        final Point[] points = {Point.newInstance(0,0), Point.newInstance(1,1), Point.newInstance(1,0)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.TRIANGLE, points);
    }

    @Test
    public void testProcess_preProcessSquareRight_whenPassedValidFigureTypeAndPoints() {
        final Point[] points = {Point.newInstance(0, 0), Point.newInstance(0,1), Point.newInstance(1,0), Point.newInstance(1,1)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.SQUARE, points);
    }

    @Test
    public void testProcess_preProcess_MultiAngleFigure_whenPassedValidFigureTypeAndPoints() {
        final Point[] points = {Point.newInstance(0,0), Point.newInstance(1,1), Point.newInstance(2,2), Point.newInstance(2,0), Point.newInstance(1,0)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.MULTI_ANGLE_FIGURE, points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcess_throwsException_whenPassedInvalidFigureTypeAndPoints() {
        final Point[] illegalPoints = {Point.newInstance(0,0), Point.newInstance(1,1)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.SQUARE, illegalPoints);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcess_throwsException_whenPassedMultiAngleFigureFigureTypeAndInvalidPointsAmount() {
        final Point[] illegalPoints = {Point.newInstance(0,0), Point.newInstance(1,1)};

        FigurePreProcessorImpl.INSTANCE.process(FigureType.MULTI_ANGLE_FIGURE, illegalPoints);
    }
}
