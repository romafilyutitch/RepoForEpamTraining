package com.epam.jwd.composer.impl;

import com.epam.jwd.composer.Lexeme;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TextSentenceTest {
    private Lexeme hello = new Word("Hello");
    private Lexeme world = new Word("World");
    private Lexeme symbol = new Symbol("!");

    private TextSentence textSentence;

    @BeforeEach
    void setUp() {
        textSentence = new TextSentence();
        textSentence.addLexeme(hello);
        textSentence.addLexeme(world);
        textSentence.addLexeme(symbol);
    }

    @AfterEach
    public void tearDown() {
        textSentence = new TextSentence();
    }
    @Test
    void getAsString() {
        Assertions.assertEquals("HelloWorld!", textSentence.getAsString());
    }

    @Test
    void getAsList() {
        final List<Lexeme> strings = Arrays.asList(new Word("Hello"), new Word("World"), new Symbol("!"));
        Assertions.assertEquals(strings, textSentence.getAsList());
    }

    @Test
    void addLexeme() {
        final Symbol symbol = new Symbol("?");
        textSentence.addLexeme(symbol);
        Assertions.assertTrue(textSentence.getAsList().contains(symbol));
    }
}