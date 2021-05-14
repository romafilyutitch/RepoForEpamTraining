package com.epam.jwd.parser.impl;

import com.epam.jwd.model.Lexeme;
import com.epam.jwd.model.impl.Symbol;
import com.epam.jwd.model.impl.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordParser extends BaseParser {

    public static final String IS_EXPRESSION_CHECK_PATTERN = "[^\\p{Alpha}]+";
    public static final String IS_SYMBOL_CHECK_PATTERN = "\\p{Punct}|\\s";
    private static final Logger logger = LoggerFactory.getLogger(WordParser.class);

    @Override
    public Lexeme parse(String text) {
        if (isSymbol(text)) {
            logger.info("Symbol was parsed, result: " + text);
            return new Symbol(text);
        }
        if (isExpression(text)) {
            logger.info("Symbol was parsed, result: " + text);
            return super.parse(text);
        }
        logger.info("Word was parsed, result: " + text);
        return new Word(text);
    }

    private boolean isExpression(String text) {
        return text.matches(IS_EXPRESSION_CHECK_PATTERN);
    }

    private boolean isSymbol(String text) {
        return text.matches(IS_SYMBOL_CHECK_PATTERN);
    }
}
