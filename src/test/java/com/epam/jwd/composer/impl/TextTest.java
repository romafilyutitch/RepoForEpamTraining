package com.epam.jwd.composer.impl;

import com.epam.jwd.composer.Lexeme;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

class TextTest {
    private Paragraph testParagraph = Mockito.mock(Paragraph.class);

    @InjectMocks
    private Text testText = new Text();

    @BeforeEach
    public void setUp() {
        testText.addLexeme(testParagraph);
    }

    @AfterEach
    public void tearDown() {
        testText.getAsList().clear();
    }

    @Test
    void getAsString() {
        Mockito.when(testParagraph.getAsString()).thenReturn("\tHello, world");
        final String asString = testText.getAsString();
        Assertions.assertEquals("\tHello, world", asString, "text must return textString \"\tHello world\"");
        Mockito.verify(testParagraph).getAsString();
    }

    @Test
    void getAsList() {
        final List<Lexeme> asList = testText.getAsList();
        Assertions.assertFalse(asList.isEmpty(), "list must be not empty");
    }

    @Test
    void addLexeme() {
        final String asString = testText.getAsString();
        Assertions.assertFalse(asString.isEmpty());
    }
}