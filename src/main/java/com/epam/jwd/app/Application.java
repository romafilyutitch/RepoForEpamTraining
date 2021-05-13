package com.epam.jwd.app;

import com.epam.jwd.sort.SortBySymbolAmountInDescendingOrderThenInNaturalOrder;
import com.epam.jwd.sort.SortParagraphsBySentencesAmount;
import com.epam.jwd.sort.SortSentencesByWordsLength;
import com.epam.jwd.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public void run() {
        logger.trace("Application was started");
        try {
            final String text = IOUtil.readTextFromFileInResources("file");
            SortBySymbolAmountInDescendingOrderThenInNaturalOrder firstSortOrder = new SortBySymbolAmountInDescendingOrderThenInNaturalOrder('e');
            SortParagraphsBySentencesAmount secondSortOrder = new SortParagraphsBySentencesAmount();
            SortSentencesByWordsLength thirdSortOrder = new SortSentencesByWordsLength();
            IOUtil.writeFileToOutputInResources(firstSortOrder.sort(text), "sortBySymbolAmount");
            IOUtil.writeFileToOutputInResources(secondSortOrder.sort(text), "sortBySentencesAmount");
            IOUtil.writeFileToOutputInResources(thirdSortOrder.sort(text), "sortByWordsLength");
        } catch (IOException e) {
            System.out.println("There was exception during reading from file");
            logger.error(e.getMessage());
        }
        logger.trace("Application was finished");
    }
}
