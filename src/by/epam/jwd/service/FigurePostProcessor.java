package by.epam.jwd.service;

import by.epam.jwd.exception.FigureException;
import by.epam.jwd.model.Figure;

public interface FigurePostProcessor {
    Figure process(Figure figure) throws FigureException;
}
