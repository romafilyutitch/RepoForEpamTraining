package by.epam.jwd.service;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;
import by.epam.jwd.validation.ValidationStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleLogService implements LogService {
    private static final String NULL_MESSAGE= "argument is null";
    private static final Logger LOGGER = LogManager.getLogger(SimpleLogService.class);
    private ValidationStrategy validationStrategy;

    @Override
    public void setValidationStrategy(ValidationStrategy newStrategy){
        if (newStrategy == null){
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        this.validationStrategy = newStrategy;
    }

    @Override
    public void log(Point point){
        if(point == null){
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        LOGGER.info(point);
    }
    @Override
    public void log(Figure figure){
        if(figure == null){
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        try{
            validationStrategy.check(figure);
            LOGGER.info(figure);
        }catch (IsNotFigureException e){
            LOGGER.error("Объект "+figure+" не является фигурой "+figure.getName());
        }catch (CanNotExistException e){
            LOGGER.error("Треугольник "+figure+" не может существовать");
        }catch (IsNotSquareException e){
            LOGGER.error("Объект "+figure+" не явялется квадратом");
        }
    }
}