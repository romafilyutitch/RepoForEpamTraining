package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.parser.Parser;

public abstract class BaseParser implements Parser {
    private Parser nextParser;

    @Override
    public Lexeme parse(String text) {
        return nextParser.parse(text);
    }

    @Override
    public void setNext(Parser parser) {
        nextParser = parser;
    }
}
