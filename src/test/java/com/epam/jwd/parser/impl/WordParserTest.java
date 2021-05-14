package com.epam.jwd.parser.impl;

import com.epam.jwd.model.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordParserTest {
    private String testWord = "Hello";
    private WordParser parser = new WordParser();

    @Test
    void parse() {
        final Lexeme parseResult = parser.parse(testWord);
        Assertions.assertNotNull(parseResult, "Parse result must be not null");
        Assertions.assertNotNull(parseResult.getAsString(), "Parse result String must be not null");
        Assertions.assertFalse(parseResult.getAsString().isEmpty(), "Parse result string must be not empty");
        Assertions.assertNotNull(parseResult.getAsList(), "Parse result list must be not null");
        Assertions.assertFalse(parseResult.getAsList().isEmpty(), "Parse result string must be not empty");
        Assertions.assertEquals("Hello", parseResult.getAsString(), "Parse result must be equals to \"Hello\"");
    }
}