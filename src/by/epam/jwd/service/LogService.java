package by.epam.jwd.service;

import by.epam.jwd.entity.Figure;
import by.epam.jwd.entity.Point;
import by.epam.jwd.exception.CanNotExistException;
import by.epam.jwd.exception.IsNotFigureException;
import by.epam.jwd.exception.IsNotSquareException;
import by.epam.jwd.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogService{
    private static final String NULL_MESSAGE= "argument is null";
    private static final Logger LOGGER = LogManager.getLogger(LogService.class);
    private Validator validator;

    private LogService(Validator validator){
        this.validator = validator;
    }

    public void setValidator(Validator validator){
        this.validator = validator;
    }

    public static LogService getInstance(Validator validator){
        return new LogService(validator);
    }

    public void log(Point point){
        if(point == null){
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        LOGGER.info(point);
    }

    public void log(Figure figure){
        if(figure == null){
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        try{
            validator.check(figure);
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