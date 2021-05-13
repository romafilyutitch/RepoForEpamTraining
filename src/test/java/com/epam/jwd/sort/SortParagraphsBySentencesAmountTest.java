package com.epam.jwd.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortParagraphsBySentencesAmountTest {
    private SortParagraphsBySentencesAmount sort = new SortParagraphsBySentencesAmount();
    private String testString = "    Hello, this is test string.\n\n    This is another string. And another one. And other.";

    @Test
    void sort() {
        final String sortedResult = sort.sort(testString);
        Assertions.assertNotNull(sortedResult, "Sorted result must be not null");
    }
}