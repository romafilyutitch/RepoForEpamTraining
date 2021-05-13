package com.epam.jwd.interpreter.impl;

import com.epam.jwd.interpreter.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.regex.Pattern;

public class Evaluator implements Expression {
    public String expressionsString;
    private final String reverseString;
    private final Stack<Expression> expressionsStack = new Stack<>();
    private final static Logger logger = LoggerFactory.getLogger(Evaluator.class);

    public Evaluator(String expressionsString) {
        this.expressionsString = expressionsString;
        this.reverseString = new StringBuilder(expressionsString).reverse().toString();
    }

    @Override
    public int interpret() {
        pushNumbersInExpressionsStack();
        pushExpressionsInExpressionsStack();
        Expression result = expressionsStack.pop();
        return result.interpret();
    }

    private void pushExpressionsInExpressionsStack() {
        Pattern.compile("\\d+").splitAsStream(expressionsString).forEach(operator -> {
            switch (operator) {
                case "<<":
                    expressionsStack.push(new LeftShiftExpression(expressionsStack.pop(), expressionsStack.pop()));
                    break;
                case ">>":
                    expressionsStack.push(new RightShiftExpression(expressionsStack.pop(), expressionsStack.pop()));
                    break;
                case "~":
                    expressionsStack.push(new SwapExpression(expressionsStack.pop()));
                    break;
                case "&":
                    expressionsStack.push(new AndExpression(expressionsStack.pop(), expressionsStack.pop()));
                    break;
                case "|":
                    expressionsStack.push(new OrExpression(expressionsStack.pop(), expressionsStack.pop()));
                    break;
                case "^":
                    expressionsStack.push(new XOrExpression(expressionsStack.pop(), expressionsStack.pop()));
                    break;
            }
        });
        logger.info("Expression was evaluated");
    }

    private void pushNumbersInExpressionsStack() {
        Pattern.compile("\\D+").splitAsStream(reverseString).forEach(number -> {
            if (number.length() > 1) {
                number = new StringBuilder(number).reverse().toString();
            }
            expressionsStack.push(new NumberExpression(Integer.parseInt(number)));
        });
        logger.info("Numbers in Expression was pushed in stack");
    }
}
