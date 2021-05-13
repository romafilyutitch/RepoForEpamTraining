package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftShiftExpression implements Expression {
    private final Expression number;
    private final Expression bits;
    private static final Logger logger = LoggerFactory.getLogger(LeftShiftExpression.class);

    public LeftShiftExpression(Expression number, Expression bits) {
        this.number = number;
        this.bits = bits;
    }

    @Override
    public int interpret() {
        final int interpretResult = number.interpret() << bits.interpret();
        logger.info("Left shift interpretation was made, result: " + interpretResult);
        return interpretResult;
    }
}
