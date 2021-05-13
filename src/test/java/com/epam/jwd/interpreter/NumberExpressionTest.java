package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.NumberExpression;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberExpressionTest {
    private NumberExpression number;

    @BeforeEach
    public void setUp() {
        number = new NumberExpression(10);
    }

    @AfterEach
    public void tearDown() {
        number = null;
    }

    @Test
    void interpret() {
        assertEquals(10, number.interpret());
    }
}