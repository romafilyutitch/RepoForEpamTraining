package by.epam.jwd.service;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;
import by.epam.jwd.validation.ValidationStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleLogService implements LogService{
    private static final Logger LOGGER = LogManager.getLogger(SimpleLogService.class);
    private ValidationStrategy validationStrategy;
    @Override
    public void setValidationStrategy(ValidationStrategy newStrategy){
        this.validationStrategy = newStrategy;
    }
    @Override
    public void log(Point point){
        LOGGER.info(point);
    }
    @Override
    public void log(Figure figure){
        try{
            validationStrategy.check(figure);
            LOGGER.info(figure);
        }catch (IsNotFigureException | CanNotExistException | IsNotSquareException e){
            LOGGER.error(e.getMessage());
        }
    }
}