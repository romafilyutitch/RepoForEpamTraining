package by.epam.jwd.model;

import by.epam.jwd.strategy.FigurePropertiesStrategy;

import java.util.Arrays;

class MultiAngleFigure extends Figure {
    MultiAngleFigure(Point[] points, FigurePropertiesStrategy strategy) {
        super(points, strategy);
    }

    @Override
    public String toString() {
        return "MultiAngleFigure { " + Arrays.toString(getPoints()) + "}";
    }
}
