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
public class LinePropertyStrategyTest {

    @Test
    public void testNewInstance_returnsSameInstance() {
        LinePropertiesStrategy oneInstance = LinePropertiesStrategy.getInstance();
        LinePropertiesStrategy otherInstance = LinePropertiesStrategy.getInstance();
        Assert.assertEquals(oneInstance, otherInstance);
    }

    @Test
    public void testGetSquare_returnsZero_whenLinePassed() {
        final double expected = 0;
        final Point[] points = {Point.newInstance(0, 0), Point.newInstance(1, 1)};
        final Figure newLine = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE, points);

        Assert.assertEquals(Double.valueOf(expected) , Double.valueOf(newLine.getSquare()));
    }

    @Test
    public void testGetPerimeter_returnsSquareRootOfTwo_whenPassedLineWithSameSquare() {
        final double expected = Math.sqrt(2.0);
        final Point[] points = {Point.newInstance(0,0), Point.newInstance(1,1)};
        final Figure newLine = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE, points);

        Assert.assertEquals(Double.valueOf(expected), Double.valueOf(newLine.getPerimeter()));
    }
}
