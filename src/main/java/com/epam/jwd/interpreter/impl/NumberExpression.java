package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberExpression implements Expression {
    private final int number;
    private static final Logger logger = LoggerFactory.getLogger(NumberExpression.class);

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        logger.info(number + " is returning");
        return number;
    }
}
