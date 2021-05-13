package com.epam.jwd.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

class ApplicationTest {
    private Application testApplication = new Application();

    @Test
    void run() {
        testApplication.run();
        Assertions.assertTrue(Files.exists(Paths.get("src\\main\\resources\\output\\sortBySentencesAmount.txt")));
        Assertions.assertTrue(Files.exists(Paths.get("src\\main\\resources\\output\\sortBySymbolAmount.txt")));
        Assertions.assertTrue(Files.exists(Paths.get("src\\main\\resources\\output\\sortByWordsLength.txt")));
    }
}