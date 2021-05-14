package com.epam.jwd.model.impl;

import com.epam.jwd.model.Lexeme;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

class ParagraphTest {

    private TextSentence textSentence = Mockito.mock(TextSentence.class);
    @InjectMocks
    private Paragraph paragraph = new Paragraph();


    @BeforeEach
    public void setUp() {
        paragraph.addLexeme(textSentence);
    }

    @AfterEach
    public void tearDown() {
        paragraph.getAsList().clear();
    }

    @Test
    void getAsString() {
        Mockito.when(textSentence.getAsString()).thenReturn("Hello, world!");
        final String asString = paragraph.getAsString();
        Assertions.assertTrue(asString.startsWith("\t"), "result string must start with tab");
        Assertions.assertEquals("Hello, world!", asString.trim(), "result must be \"Hello, world!\"");
        Mockito.verify(textSentence).getAsString();
    }

    @Test
    void addLexeme() {
        final String asString = paragraph.getAsString();
        Assertions.assertTrue(!asString.isEmpty(), "string must be not empty");
    }

    @Test
    void getAsList() {
        final List<Lexeme> asList = paragraph.getAsList();
        Assertions.assertTrue(!asList.isEmpty(), "list mus be not empty");
    }
}