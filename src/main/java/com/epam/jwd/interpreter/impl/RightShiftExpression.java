package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RightShiftExpression implements Expression {
    private final Expression number;
    private final Expression bitsNumber;
    private static final Logger logger = LoggerFactory.getLogger(RightShiftExpression.class);

    public RightShiftExpression(Expression number, Expression bitsNumber) {
        this.number = number;
        this.bitsNumber = bitsNumber;
    }

    @Override
    public int interpret() {
        final int interpretResult = number.interpret() >> bitsNumber.interpret();
        logger.info("Right shift interpretation was made, result: " + interpretResult);
        return interpretResult;
    }
}
