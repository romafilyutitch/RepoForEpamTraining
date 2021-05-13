package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionParserTest {
    private ExpressionParser parser = new ExpressionParser();
    private String expressionString = "13<<2";

    @Test
    void parse() {
        final Lexeme parseResult = parser.parse(expressionString);
        Assertions.assertNotNull(parseResult, "Parse result must be not null");
        Assertions.assertNotNull(parseResult.getAsString(), "Parse result string must be not null");
        Assertions.assertEquals(13<<2, Integer.parseInt(parseResult.getAsString()), "Parse result string must be equals");
    }
}