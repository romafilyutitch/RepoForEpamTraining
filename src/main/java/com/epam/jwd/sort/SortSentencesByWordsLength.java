package com.epam.jwd.sort;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.parser.Parser;
import com.epam.jwd.parser.TextParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SortSentencesByWordsLength implements SortStrategy {
    private static final Logger logger = LoggerFactory.getLogger(SortSentencesByWordsLength.class);

    @Override
    public String sort(String text) {
        final Parser parser = TextParserFactory.getParser();
        final Lexeme resultText = parser.parse(text);
        logger.info("Text was parsed, result: " + resultText.getAsString());
        final List<Lexeme> paragraphs = resultText.getAsList();
        List<Lexeme> sentences = getListOfSortedSentences(paragraphs);
        StringBuilder result = new StringBuilder();
        sentences.forEach(lexeme -> result.append(lexeme.getAsString().replaceAll("\n", " ")).append("\n"));
        logger.info("Text was sorted, result: " + result.toString());
        return result.toString();
    }

    private List<Lexeme> getListOfSortedSentences(List<Lexeme> paragraphs) {
        return paragraphs.stream().flatMap(paragraph -> paragraph.getAsList().stream()).sorted((firstSentence, secondSentence) -> {
            int totalWordsLengthInFirstSentence = firstSentence.getAsList().stream().map(Lexeme::getAsString).mapToInt(String::length).sum();
            int totalWordsLengthInSecondSentence = secondSentence.getAsList().stream().map(Lexeme::getAsString).mapToInt(String::length).sum();
            return totalWordsLengthInFirstSentence - totalWordsLengthInSecondSentence;
        }).collect(Collectors.toList());
    }
}
