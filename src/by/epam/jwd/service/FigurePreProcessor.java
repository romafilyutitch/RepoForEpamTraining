package by.epam.jwd.service;

import by.epam.jwd.factory.FigureType;
import by.epam.jwd.model.Point;

public interface FigurePreProcessor {
    void process(FigureType type, Point... figureConstituents);
}
