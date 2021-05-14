package com.epam.jwd.model.impl;

import com.epam.jwd.model.Lexeme;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Word implements Lexeme {
    private final String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String getAsString() {
        return word;
    }

    @Override
    public List<Lexeme> getAsList() {
        return Collections.singletonList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return word;
    }
}
