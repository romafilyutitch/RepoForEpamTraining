package com.epam.jwd.composer.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SymbolTest {
    private Symbol testSymbol;

    @BeforeEach
    public void setUp() {
        testSymbol = new Symbol("?");
    }

    @AfterEach
    public void tearDown() {
        testSymbol = null;
    }

    @Test
    void getAsString() {
        Assertions.assertEquals("?", testSymbol.getAsString());
    }

    @Test
    void getAsList() {
        Assertions.assertEquals("?", testSymbol.getAsList().get(0).getAsString());
    }
}