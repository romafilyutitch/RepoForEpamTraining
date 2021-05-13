package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.composer.impl.Word;
import com.epam.jwd.interpreter.impl.InterpretEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressionParser extends BaseParser {
    private static final Logger logger = LoggerFactory.getLogger(ExpressionParser.class);

    @Override
    public Lexeme parse(String text) {
        final InterpretEngine interpretEngine = new InterpretEngine();
        final int i = interpretEngine.makeInterpretation(text);
        final Word resultWord = new Word(Integer.toString(i));
        logger.info("Expression was interpreted, result: " + resultWord.getAsString());
        return resultWord;
    }
}
