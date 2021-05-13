package com.epam.jwd.interpreter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        long amountOfLeftBracesInText = text.chars().filter(c -> c == '(').count();
        int LeftBracesInTextCounter = 0;
        int i;
        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                LeftBracesInTextCounter++;
            }
            if (amountOfLeftBracesInText == LeftBracesInTextCounter) {
                break;
            }
        }
        int j;
        for (j = 0; j < text.length(); j++) {
            if (text.charAt(j) == ')' && j > i) {
                break;
            }
        }
        final String substring = text.substring(i, j + 1);
        logger.info("Expression in braces was found : " + substring);
        return substring;
    }

    private String orderExpressionStringInOperationPriority(String expressionString) {
        Matcher matcher = Pattern.compile("~\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        matcher = Pattern.compile("\\d+>>\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        matcher = Pattern.compile("\\d+<<\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        matcher = Pattern.compile("\\d+&\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        matcher = Pattern.compile("\\d+^\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        matcher = Pattern.compile("\\d+\\|\\d+").matcher(expressionString);
        if (matcher.find()) {
            final String group = matcher.group();
            expressionString = expressionString.replace(group, "(" + group + ")");
            return expressionString;
        }
        return expressionString;
    }
}
