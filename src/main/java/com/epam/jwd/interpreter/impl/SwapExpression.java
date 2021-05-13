package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwapExpression implements Expression {
    private final Expression number;
    private static final Logger logger = LoggerFactory.getLogger(SwapExpression.class);

    public SwapExpression(Expression number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        final int interpretResult = ~number.interpret();
        logger.info("Swap interpretation was made, result: " + interpretResult);
        return interpretResult;
    }
}
