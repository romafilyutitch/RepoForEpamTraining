package com.epam.jwd.composer.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTest {
    private Word testWord;

    @Test
    void getAsString() {
        testWord = new Word("WORD");
        Assertions.assertEquals("WORD", testWord.getAsString());
    }

    @Test
    void getAsList() {
        testWord = new Word("WORD");
        Assertions.assertEquals("WORD", testWord.getAsList().get(0).getAsString());
    }
}