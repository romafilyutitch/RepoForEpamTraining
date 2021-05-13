package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.NumberExpression;
import com.epam.jwd.interpreter.impl.OrExpression;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OrExpressionTest {
    private Expression firstNumber = Mockito.mock(NumberExpression.class);
    private Expression secondNumber = Mockito.mock(NumberExpression.class);

    @InjectMocks
    private OrExpression orExpression = new OrExpression(firstNumber, secondNumber);

    @Test
    void interpret() {
        Mockito.when(firstNumber.interpret()).thenReturn(10);
        Mockito.when(secondNumber.interpret()).thenReturn(2);

        assertEquals(10|2, orExpression.interpret());

        Mockito.verify(firstNumber).interpret();
        Mockito.verify(secondNumber).interpret();
    }
}