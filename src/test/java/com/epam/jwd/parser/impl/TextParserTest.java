package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextParserTest {
    private String textString = "    Hello, this is the test String.\n\n    Another paragraph.";
    private TextParser testParser;

    @BeforeEach
    public void init() {
        testParser = new TextParser();
        final ParagraphParser paragraphParser = new ParagraphParser();
        final SentenceParser sentenceParser = new SentenceParser();
        final WordParser wordParser = new WordParser();
        sentenceParser.setNext(wordParser);
        paragraphParser.setNext(sentenceParser);
        testParser.setNext(paragraphParser);
    }

    @Test
    void parse() {
        final Lexeme parse = testParser.parse(textString);
        Assertions.assertNotNull(parse, "result must be not null");
        Assertions.assertTrue(parse.getAsList().size() == 2, " size must be 2");
    }
}