package com.epam.jwd.composer.impl;

import com.epam.jwd.composer.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements Lexeme {
    public static final String PARAGRAPH_START_TAB = "\t";
    public static final String PARAGRAPH_END_DOUBLE_ENTER = "\n\n";
    private final List<Lexeme> sentences = new ArrayList<>();

    @Override
    public String getAsString() {
        final StringBuilder builder = new StringBuilder(PARAGRAPH_START_TAB);
        sentences.forEach(sentence -> builder.append(sentence.getAsString()));
        builder.append(PARAGRAPH_END_DOUBLE_ENTER);
        return builder.toString();
    }

    @Override
    public void addLexeme(Lexeme lexeme) {
        sentences.add(lexeme);
    }

    @Override
    public List<Lexeme> getAsList() {
        return sentences;
    }

}
