package by.epam.jwd.service.impl;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Figure;
import by.epam.jwd.model.Point;
import by.epam.jwd.model.SimpleFigureFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FigureStorageTest {
    @Test
    public void testSave_savesPassedFigure() {
        Figure line = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE,
                Point.newInstance(0, 0),
                Point.newInstance(1, 1));
        FigureStorage storage = new FigureStorage();
        Assert.assertTrue(storage.save(line));
    }

    @Test
    public void testRemove_removesPassedFigure() {
        Figure line = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE,
                Point.newInstance(0,0),
                Point.newInstance(1,1));
        FigureStorage storage = new FigureStorage();
        storage.save(line);

        Assert.assertTrue(storage.remove(line));
    }

    @Test
    public void testRemove_dontRemoves_whenFigureIsntInSotorage() {
        Figure line = SimpleFigureFactory.INSTANCE.createFigure(FigureType.LINE,
                Point.newInstance(0,0),
                Point.newInstance(1,1));
        FigureStorage storage = new FigureStorage();
        Assert.assertFalse(storage.remove(line));
    }
}
