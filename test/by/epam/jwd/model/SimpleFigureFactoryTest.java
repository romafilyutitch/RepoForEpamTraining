package by.epam.jwd.model;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.strategy.impl.LinePropertiesStrategy;
import by.epam.jwd.strategy.impl.MultiAngleFigurePropertiesStrategy;
import by.epam.jwd.strategy.impl.SquarePropertiesStrategy;
import by.epam.jwd.strategy.impl.TrianglePropertiesStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleFigureFactoryTest {

    @Test
    public void testCreateFigure_createsLine() {
        final Point[] points = {Point.newInstance(0, 0), Point.newInstance(10, 10)};
        final Line expectedLine = new Line(points, LinePropertiesStrategy.getInstance());

        final Figure actualLine = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE, points);

        Assert.assertNotNull(actualLine);
        Assert.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testCreateFigure_createsTriangle() {
        final Point[] points = {Point.newInstance(0, 0), Point.newInstance(1, 1), Point.newInstance(1, 0)};
        final Triangle expectedTriangle = new Triangle(points, TrianglePropertiesStrategy.INSTANCE);

        final Figure actualTriangle = SimpleFigureFactory.INSTANCE.createFigure(FigureType.TRIANGLE, points);

        Assert.assertNotNull(actualTriangle);
        Assert.assertEquals(expectedTriangle, actualTriangle);
    }

    @Test
    public void testCreateFigure_createsSquare() {
        final Point[] points = {Point.newInstance(0, 0), Point.newInstance(0, 1), Point.newInstance(1, 0), Point.newInstance(1, 1)};
        final Square expectedSquare = new Square(points, SquarePropertiesStrategy.getInstance());

        final Figure actualSqaure = SimpleFigureFactory.INSTANCE.createFigure(FigureType.SQUARE, points);

        Assert.assertNotNull(actualSqaure);
        Assert.assertEquals(expectedSquare, actualSqaure);
    }

    @Test
    public void testCreateFigure_createsMultiAngleFigure() {
        final Point[] points = {
                Point.newInstance(0,0),
                Point.newInstance(1,1),
                Point.newInstance(2,2),
                Point.newInstance(3,3),
                Point.newInstance(3,0)
        };
        final MultiAngleFigure expectedMultiAngleFigure = new MultiAngleFigure(points, MultiAngleFigurePropertiesStrategy.getInstance());

        final Figure actualMultiAngleFigure = SimpleFigureFactory.INSTANCE.createFigure(FigureType.MULTI_ANGLE_FIGURE, points);

        Assert.assertNotNull(actualMultiAngleFigure);
        Assert.assertEquals(expectedMultiAngleFigure, actualMultiAngleFigure);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFigure_throwsException_whenPassedIllegalFigureType() {
        Point[] points = {Point.newInstance(0,0), Point.newInstance(0,1)};
        SimpleFigureFactory.INSTANCE.createFigure(FigureType.valueOf("asd"), points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFigure_throwsException_whenPassedIllegalPoints() {
        Point[] points = new Point[0];
        SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE, points);
    }

}
