package com.epam.jwd.sort;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.parser.Parser;
import com.epam.jwd.parser.TextParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBySymbolAmountInDescendingOrderThenInNaturalOrder implements SortStrategy {
    private final char symbol;
    private static final Logger logger = LoggerFactory.getLogger(SortBySymbolAmountInDescendingOrderThenInNaturalOrder.class);

    public SortBySymbolAmountInDescendingOrderThenInNaturalOrder(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String sort(String text) {
        final Parser parser = TextParserFactory.getParser();
        Lexeme resultText = parser.parse(text);
        logger.info("Text was parsed, result: " + resultText.getAsString());
        final List<Lexeme> paragraphs = resultText.getAsList();
        List<Lexeme> words = getSortedWords(paragraphs);
        final StringBuilder builder = new StringBuilder();
        words.stream().map(Lexeme::getAsString).distinct().forEach(word -> builder.append(word).append("\n"));
        logger.info("Text was sorted, result: " + builder.toString());
        return builder.toString();
    }

    private List<Lexeme> getSortedWords(List<Lexeme> paragraphs) {
        return paragraphs.stream()
                .flatMap(paragraph -> paragraph.getAsList().stream())
                .flatMap(sentence -> sentence.getAsList().stream())
                .sorted(buildComparator())
                .collect(Collectors.toList());
    }

    private Comparator<Lexeme> buildComparator() {
        final Comparator<Lexeme> comparingBySymbolAmountInDescendingOrder = (firstWord, secondWord) -> {
            long symbolAmountInFirstWord = firstWord.getAsString().chars().filter(c -> c == symbol).count();
            long symbolAmountInSecondWord = secondWord.getAsString().chars().filter(c -> c == symbol).count();
            return (int) (symbolAmountInSecondWord - symbolAmountInFirstWord);
        };
        Comparator<Lexeme> comparingInAlphabeticOrder = Comparator.comparing(Lexeme::getAsString);
        return comparingBySymbolAmountInDescendingOrder.thenComparing(comparingInAlphabeticOrder);
    }
}
