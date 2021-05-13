package com.epam.jwd.parser.impl;

import com.epam.jwd.composer.Lexeme;
import com.epam.jwd.composer.impl.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextParser extends BaseParser {
    public static final String PARAGRAPH_SPLIT_REGEX = "\n\n";
    private static final Logger logger = LoggerFactory.getLogger(TextParser.class);

    @Override
    public Lexeme parse(String text) {
        Lexeme textLexeme = new Text();
        String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);
        for (String paragraph : paragraphs) {
            textLexeme.addLexeme(super.parse(paragraph));
        }
        logger.info("Text was parsed, result: " + textLexeme);
        return textLexeme;
    }
}
