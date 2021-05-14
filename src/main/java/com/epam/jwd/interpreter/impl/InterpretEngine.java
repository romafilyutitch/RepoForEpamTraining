package com.epam.jwd.interpreter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterpretEngine {
    private static final Logger logger = LoggerFactory.getLogger(InterpretEngine.class);

    public int makeInterpretation(String evaluationString) {
        Evaluator evaluator;
        while (evaluationString.contains("(") && evaluationString.contains(")")) {
            final String expressionInBracesOnMaxDepth = findExpressionInBracesOnMaxDepth(evaluationString);
            String replacementString = expressionInBracesOnMaxDepth.replace("(", "");
            replacementString = replacementString.replace(")", "");
            evaluator = new Evaluator(replacementString);
            final int interpret = evaluator.interpret();
            evaluationString = evaluationString.replace(expressionInBracesOnMaxDepth, Integer.toString(interpret));
        }
        evaluator = new Evaluator(evaluationString);
        logger.info("Interpretation was made");
        return evaluator.interpret();
    }

    private String findExpressionInBracesOnMaxDepth(String text) {
        int substringStartIndex = getLastLeftBraceIndex(text);
        int substringEndIndex = getRightRightBraceIndex(text, substringStartIndex);
        final String substring = text.substring(substringStartIndex, substringEndIndex + 1);
        logger.info("Expression in braces was found : " + substring);
        return substring;
    }

    private int getRightRightBraceIndex(String text, int lastLeftBraceIndex) {
        int i;
        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ')' && i > lastLeftBraceIndex) {
                return i;
            }
        }
        return i;
    }

    private int getLastLeftBraceIndex(String text) {
        long amountOfLeftBracesInText = text.chars().filter(c -> c == '(').count();
        int leftBracesInTextCounter = 0;
        int i;
        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i ) == '(') {
                leftBracesInTextCounter++;
            }
            if (amountOfLeftBracesInText == leftBracesInTextCounter) {
                return i;
            }
        }
        return i;
    }
}
