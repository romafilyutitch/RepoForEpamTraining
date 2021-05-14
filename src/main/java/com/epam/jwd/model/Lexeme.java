package com.epam.jwd.model;

import java.util.List;

public interface Lexeme {
    String getAsString();

    List<Lexeme> getAsList();

    default void addLexeme(Lexeme lexeme) {
        throw new IllegalArgumentException("Cannot add Lexeme in lexeme");
    }
}
