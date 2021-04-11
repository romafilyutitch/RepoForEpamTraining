package by.epam.jwd.service.impl;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Point;
import by.epam.jwd.service.FigurePreProcessor;

public enum FigurePreProcessorImpl implements FigurePreProcessor {
    INSTANCE;
    @Override
    public void process(FigureType type, Point... figureConstituents) {
        if (type == FigureType.MULTI_ANGLE_FIGURE) {
            if (figureConstituents.length < type.getAmountOfPoints() - 1 || figureConstituents.length > type.getAmountOfPoints() + 1) {
                throw new IllegalArgumentException("figure constituents for figure " + type + " is not valid, must be >= " + (type.getAmountOfPoints() - 1) + " and <= " + (type.getAmountOfPoints() + 1));
            }
        } else {
            if (type.getAmountOfPoints() != figureConstituents.length) {
                throw new IllegalArgumentException("figure constituents amount for figure" + type + " is not valid, must be " + type.getAmountOfPoints());
            }
        }
    }
}
