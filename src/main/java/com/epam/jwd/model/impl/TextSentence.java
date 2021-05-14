package com.epam.jwd.model.impl;

import com.epam.jwd.model.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class TextSentence implements Lexeme {
    private final List<Lexeme> wordsAndSymbols = new ArrayList<>();

    @Override
    public String getAsString() {
        final StringBuilder builder = new StringBuilder();
        wordsAndSymbols.forEach(word -> builder.append(word.getAsString()));
        return builder.toString();
    }

    @Override
    public List<Lexeme> getAsList() {
        return wordsAndSymbols;
    }

    @Override
    public void addLexeme(Lexeme lexeme) {
        wordsAndSymbols.add(lexeme);
    }

}
