package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrExpression implements Expression {
    private final Expression number;
    private final Expression otherNumber;
    private final Logger logger = LoggerFactory.getLogger(OrExpression.class);

    public OrExpression(Expression number, Expression otherNumber) {
        this.number = number;
        this.otherNumber = otherNumber;
    }

    @Override
    public int interpret() {
        final int interpretResult = number.interpret() | otherNumber.interpret();
        logger.info("Or expression was made, result: " + interpretResult);
        return interpretResult;
    }
}
