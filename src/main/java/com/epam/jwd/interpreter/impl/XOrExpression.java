package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XOrExpression implements Expression {
    private final Expression firstNumber;
    private final Expression secondNumber;
    private static final Logger logger = LoggerFactory.getLogger(XOrExpression.class);

    public XOrExpression(Expression firstNumber, Expression secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public int interpret() {
        final int interpretResult = firstNumber.interpret() ^ secondNumber.interpret();
        logger.info("XOr interpretation was made, result: " + interpretResult);
        return interpretResult;
    }
}
