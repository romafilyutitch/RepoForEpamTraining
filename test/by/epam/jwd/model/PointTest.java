package by.epam.jwd.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {
    @Test
    public void testNewInstance_returnsNewPoint() {
        final int x = 0;
        final int y = 5;

        final Point point = Point.newInstance(x,y);

        Assert.assertNotNull(point);
        Assert.assertEquals(x, point.getX());
        Assert.assertEquals(y, point.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewInstance_throwsIllegalArgumentException_whenPassedIlleagalX() {
        final int x = -10;
        final int y = 10;

        final Point point = Point.newInstance(x,y);

        Assert.assertNotNull(point);
    }

    @Test
    public void testNewInstance_returnsSamePoint_whenPointHasBeenCached() {
        final int x = 10;
        final int y = 10;
        final Point newPoint = Point.newInstance(x,y);

        final Point expectedSamePoint = Point.newInstance(x,y);

        Assert.assertSame(newPoint,expectedSamePoint);
    }

    @Test
    public void testNewInstance_returnDifferentObjects() {
        final int oneX = 10;
        final int oneY = 10;
        final int otherX = 100;
        final int otherY = 100;

        final Point onePoint = Point.newInstance(oneX, oneY);
        final Point otherPoint = Point.newInstance(otherX, otherY);

        Assert.assertNotNull(onePoint);
        Assert.assertNotNull(otherPoint);
        Assert.assertNotEquals(onePoint, otherPoint);
    }
}
