package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.composer.impl.TextSentence;
import com.epam.jwd.exeption.IncorrectTextException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentenceParser extends BaseParser {

    public static final String SENTENCE_SPLIT_PATTERN = "\\s";
    public static final String SENTENCE_STARTS_WITH_UPPER_CASE_LETTER_CHECK_PATTERN = "^\\p{Upper}.+";
    public static final String SENTENCE_STARTS_WITH_DOTS_OR_QUESTION_MARK_OR_EXCLAMATION_MARK_CHECK_PATTERN = ".+[.!?]$";
    public static final String WORD_ENDS_WITH_PUNCTURE_CHECK_PATTERN = ".+[,.?!]";

    private static final Logger logger = LoggerFactory.getLogger(SentenceParser.class);

    @Override
    public Lexeme parse(String text) {
        text = text.replaceAll("\n", " ENTER ");
        checkSentence(text);
        Lexeme sentence = new TextSentence();
        String[] splitSentence = text.split(SENTENCE_SPLIT_PATTERN);
        for (String word : splitSentence) {
            parseWord(sentence, word);
        }
        logger.info("Sentence was parsed, result : " + sentence);
        return sentence;
    }

    private void parseWord(Lexeme sentence, String word) {
        if (endsWithPuncture(word)) {
            parseWordEndedWithPuncture(sentence, word);
        } else {
            if (word.equals("ENTER")) {
                sentence.addLexeme(super.parse("\n"));
            } else {
                sentence.addLexeme(super.parse(word));
                sentence.addLexeme(super.parse(" "));
            }
        }
    }

    private void parseWordEndedWithPuncture(Lexeme sentence, String word) {
        sentence.addLexeme(super.parse(word.substring(0, word.length() - 1)));
        sentence.addLexeme(super.parse(String.valueOf(word.charAt(word.length() - 1))));
        sentence.addLexeme(super.parse(" "));
    }

    private void checkSentence(String sentence) {
        if (!sentence.matches(SENTENCE_STARTS_WITH_UPPER_CASE_LETTER_CHECK_PATTERN)) {
            logger.error("Sentence " + sentence + " doesn't start with upper case letter");
            throw new IncorrectTextException("Sentence doesn't starts with Upper case letter");
        }
        if (!sentence.matches(SENTENCE_STARTS_WITH_DOTS_OR_QUESTION_MARK_OR_EXCLAMATION_MARK_CHECK_PATTERN)) {
            logger.error("Sentence " + sentence + " doesn't ent with . or ? or ! or ...");
            throw new IncorrectTextException("Sentence doesn't ends with . or ? or ! or ...");
        }
    }

    private boolean endsWithPuncture(String word) {
        return word.matches(WORD_ENDS_WITH_PUNCTURE_CHECK_PATTERN);
    }
}
