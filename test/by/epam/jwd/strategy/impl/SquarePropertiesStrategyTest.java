package by.epam.jwd.strategy.impl;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.SimpleFigureFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SquarePropertiesStrategyTest {

    @Test
    public void testNewIstance_returnsSameInstance() {
        final SquarePropertiesStrategy oneInstance = SquarePropertiesStrategy.getInstance();
        final SquarePropertiesStrategy otherInstance = SquarePropertiesStrategy.getInstance();

        Assert.assertEquals(oneInstance, otherInstance);
    }

    @Test
    public void testGetPerimeter_returnsEqualsPerimeter_whenPassedSquareWithSamePerimeter() {
        final double expected = 4.0;
        final Point[] points = {
                Point.newInstance(0, 0),
                Point.newInstance(0, 1),
                Point.newInstance(1, 0),
                Point.newInstance(1, 1)
        };
        final Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);

        Assert.assertEquals(Double.valueOf(expected), Double.valueOf(square.getPerimeter()));
    }

    @Test
    public void testGetSquare_returnsEqualsSquare_whenPassedSquareWithSameSquare() {
        final double expected = Math.pow(1.0, 2);
        final Point[] points = {
                Point.newInstance(0, 0),
                Point.newInstance(0, 1),
                Point.newInstance(1, 0),
                Point.newInstance(1, 1)
        };
        final Figure square = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);

        Assert.assertEquals(Double.valueOf(expected), Double.valueOf(square.getSquare()));
    }
}
