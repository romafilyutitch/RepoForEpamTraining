package com.epam.jwd.parser.impl;

import com.epam.jwd.model.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextSentenceParserTest {
    private String testText = "Hello, this is test String.";
    private SentenceParser parser;

    @BeforeEach
    public void setUp() {
        parser = new SentenceParser();
        final WordParser wordParser = new WordParser();
        final ExpressionParser expressionParser = new ExpressionParser();
        parser.setNext(wordParser);
        wordParser.setNext(expressionParser);
    }

    @Test
    void parse() {
        final Lexeme result = parser.parse(testText);
        Assertions.assertNotNull(result, "result must be not null");
        Assertions.assertFalse(result.getAsString().isEmpty(), "String must be not empty");
        Assertions.assertNotNull(result.getAsList(), "result list must be not null");
        Assertions.assertFalse(result.getAsList().isEmpty(), "Result list must be not empty");

    }
}