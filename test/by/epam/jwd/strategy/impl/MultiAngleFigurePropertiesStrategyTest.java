package by.epam.jwd.strategy.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MultiAngleFigurePropertiesStrategyTest {

    @Test
    public void testGetInstance_returnSameInstance() {
        final MultiAngleFigurePropertiesStrategy oneStrategy = MultiAngleFigurePropertiesStrategy.getInstance();
        final MultiAngleFigurePropertiesStrategy otherStrategy = MultiAngleFigurePropertiesStrategy.getInstance();

        Assert.assertEquals(oneStrategy, otherStrategy);
    }

    @Test
    public void testGetPerimeter_returnsEqualPerimeter_whenPassedMultiAngleFigureWithSamePerimeter() {

    }
}
