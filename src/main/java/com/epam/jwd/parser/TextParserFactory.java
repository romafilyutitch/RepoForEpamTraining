package com.epam.jwd.parser;

import com.epam.jwd.parser.impl.ExpressionParser;
import com.epam.jwd.parser.impl.ParagraphParser;
import com.epam.jwd.parser.impl.SentenceParser;
import com.epam.jwd.parser.impl.TextParser;
import com.epam.jwd.parser.impl.WordParser;

public class TextParserFactory{

    public static Parser getParser() {
        final TextParser parser = new TextParser();
        final ParagraphParser paragraphParser = new ParagraphParser();
        final SentenceParser sentenceParser = new SentenceParser();
        final WordParser wordParser = new WordParser();
        final ExpressionParser expressionParser = new ExpressionParser();
        wordParser.setNext(expressionParser);
        sentenceParser.setNext(wordParser);
        paragraphParser.setNext(sentenceParser);
        parser.setNext(paragraphParser);
        return parser;
    }
}
