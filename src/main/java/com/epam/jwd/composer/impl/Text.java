package com.epam.jwd.composer.impl;

import com.epam.jwd.composer.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class Text implements Lexeme {
    private final List<Lexeme> paragraphs = new ArrayList<>();

    @Override
    public String getAsString() {
        final StringBuilder builder = new StringBuilder();
        paragraphs.forEach(paragraphs -> builder.append(paragraphs.getAsString()));
        return builder.toString();
    }

    @Override
    public List<Lexeme> getAsList() {
        return paragraphs;
    }

    @Override
    public void addLexeme(Lexeme lexeme) {
        paragraphs.add(lexeme);
    }

}
