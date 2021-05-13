package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.NumberExpression;
import com.epam.jwd.interpreter.impl.SwapExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

class SwapExpressionTest {
    private Expression number = Mockito.mock(NumberExpression.class);

    @InjectMocks
    private SwapExpression swapExpression = new SwapExpression(number);

    @Test
    void interpret() {
        Mockito.when(number.interpret()).thenReturn(10);

        Assertions.assertEquals(~10, swapExpression.interpret());

        Mockito.verify(number).interpret();
    }
}