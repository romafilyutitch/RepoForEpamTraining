package com.epam.jwd.parser;

import com.epam.jwd.composer.Lexeme;

public interface Parser {
    Lexeme parse(String text);

    void setNext(Parser parser);

}
