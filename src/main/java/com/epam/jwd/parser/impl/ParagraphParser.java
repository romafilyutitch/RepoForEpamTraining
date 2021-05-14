package com.epam.jwd.parser.impl;

import com.epam.jwd.model.Lexeme;
import com.epam.jwd.model.impl.Paragraph;
import com.epam.jwd.exeption.IncorrectTextException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BaseParser {

    public static final String PARAGRAPH_FIND_PATTERN = "\\p{Upper}.+?[?!.]";
    public static final String PARAGRAPH_CHECK_PATTERN = "^\\p{Space}{4}\\p{Upper}.*";
    private static final Logger logger = LoggerFactory.getLogger(ParagraphParser.class);

    @Override
    public Lexeme parse(String text) {
        text = text.replaceAll("\n", "ENTER");
        checkParagraph(text);
        Lexeme paragraph = new Paragraph();
        Pattern pattern = Pattern.compile(PARAGRAPH_FIND_PATTERN);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            group = group.replaceAll("ENTER", "\n");
            paragraph.addLexeme(super.parse(group));
        }
        logger.info("Paragraph was parsed, result: " + paragraph.getAsString());
        return paragraph;
    }

    private void checkParagraph(String text) {
        if (!text.matches(PARAGRAPH_CHECK_PATTERN)) {
            logger.error("Error. Paragraph " + text + "doesn't starts with tab or 4 spaces");
            throw new IncorrectTextException("Paragraph doesn't starts with tab or 4 spaces");
        }
    }
}
