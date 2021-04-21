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
public class TrianglePropertiesStrategyTest {

    @Test
    public void testGetPerimeter_returnsEqualsPerimeter_whenPassedTriangleWithSamePerimeter() {
        final double expected = (Math.sqrt(2.0)) + (Math.sqrt(1.0)) + (Math.sqrt(1.0));
        final Point[] points = {
                Point.newInstance(1, 1),
                Point.newInstance(2, 2),
                Point.newInstance(2, 1)
        };

        final Figure triangle = SimpleFigureFactory.INSTANCE.createFigure(FigureType.TRIANGLE, points);

        Assert.assertEquals(Double.valueOf(expected), Double.valueOf(triangle.getPerimeter()));
    }

    @Test
    public void testGetSquare_returnsEqualsSquare_whenPassedTriangleWithSameSquare() {
        final Point[] points = {
                Point.newInstance(1, 1),
                Point.newInstance(2, 2),
                Point.newInstance(2, 1)
        };
        final Figure triangle = SimpleFigureFactory.INSTANCE.createFigure(FigureType.TRIANGLE, points);
        final TrianglePropertiesStrategy strategy = TrianglePropertiesStrategy.INSTANCE;
        final double halfPerimeter = triangle.getPerimeter() / 2;
        final double firstLineLength = strategy.getLineLength(points[0], points[1]);
        final double secondLineLength = strategy.getLineLength(points[1], points[2]);
        final double thirdLineLength = strategy.getLineLength(points[2], points[0]);
        final double expected = Math.sqrt(halfPerimeter *
                (halfPerimeter - firstLineLength) *
                (halfPerimeter - secondLineLength) *
                (halfPerimeter - thirdLineLength));

        Assert.assertEquals(Double.valueOf(expected), Double.valueOf(triangle.getSquare()));
    }
}
