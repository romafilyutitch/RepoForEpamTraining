package com.epam.jwd.sort;

import com.epam.jwd.model.Lexeme;
import com.epam.jwd.parser.Parser;
import com.epam.jwd.parser.TextParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class SortParagraphsBySentencesAmount implements SortStrategy {
    private static final Logger logger = LoggerFactory.getLogger(SortParagraphsBySentencesAmount.class);

    @Override
    public String sort(String text) {
        final Parser parser = TextParserFactory.getParser();
        Lexeme resultText = parser.parse(text);
        logger.info("Text was parsed, result: " + resultText.getAsString());
        final List<Lexeme> paragraphs = resultText.getAsList();
        paragraphs.sort(Comparator.comparingInt(firstParagraph -> firstParagraph.getAsList().size()));
        StringBuilder builder = new StringBuilder();
        paragraphs.forEach(builder::append);
        logger.info("Text was sorted, result: " + builder.toString());
        return resultText.getAsString();
    }
}