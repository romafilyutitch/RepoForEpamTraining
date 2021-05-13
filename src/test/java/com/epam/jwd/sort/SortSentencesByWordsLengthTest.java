package com.epam.jwd.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortSentencesByWordsLengthTest {
    private SortSentencesByWordsLength sort = new SortSentencesByWordsLength();
    private String testText = "    This is the test string.\n\n    This is another test string.";

    @Test
    void sort() {
        final String sortedString = sort.sort(testText);
        Assertions.assertNotNull(sortedString, "Sorted string must be not null");
        final String[] sortedSplitString = sortedString.split("\n");
        Assertions.assertEquals("This is the test string.", sortedSplitString[0].trim());
        Assertions.assertEquals("This is another test string.", sortedSplitString[1].trim());
    }
}