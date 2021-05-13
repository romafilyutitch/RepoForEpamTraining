package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.AndExpression;
import com.epam.jwd.interpreter.impl.NumberExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;


class AndExpressionTest {
    private Expression firstNumber = Mockito.mock(NumberExpression.class);
    private Expression secondNumber = Mockito.mock(NumberExpression.class);

    @InjectMocks
    private AndExpression andExpression = new AndExpression(firstNumber, secondNumber);

    @Test
    void interpret() {
        Mockito.when(firstNumber.interpret()).thenReturn(10);
        Mockito.when(secondNumber.interpret()).thenReturn(2);

        Assertions.assertEquals(10&2, andExpression.interpret());

        Mockito.verify(firstNumber).interpret();
        Mockito.verify(secondNumber).interpret();
    }
}