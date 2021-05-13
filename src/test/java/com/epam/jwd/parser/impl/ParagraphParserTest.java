package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParagraphParserTest {
    private String testString = "    Hello, this is the test String.";
    private ParagraphParser parser;
    @BeforeEach
    void setUp() {
        parser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        parser.setNext(sentenceParser);
        sentenceParser.setNext(wordParser);
        wordParser.setNext(expressionParser);
    }
    @Test
    void parse() {
        Lexeme testLexeme = parser.parse(testString);
        Assertions.assertNotNull(testLexeme, "returned object must be not null");
        Assertions.assertNotNull(testLexeme.getAsString(), "string must be not null");
        Assertions.assertFalse(testLexeme.getAsList().isEmpty(), "returned list must be not empty");
    }
}