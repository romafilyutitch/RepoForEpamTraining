package com.epam.jwd.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortBySymbolAmountInDescendingOrderThenInNaturalOrderTest {
    private SortBySymbolAmountInDescendingOrderThenInNaturalOrder sort = new SortBySymbolAmountInDescendingOrderThenInNaturalOrder('e');
    private String testText = "    Hello, this is test text.\n\n    And another one.";

    @Test
    void sort() {
        final String sortResult = sort.sort(testText);
        Assertions.assertNotNull(sortResult, "Sort result must be not null");
        Assertions.assertFalse(sortResult.isEmpty(), "Sort result must be not empty string");
    }
}