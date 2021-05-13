package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.NumberExpression;
import com.epam.jwd.interpreter.impl.RightShiftExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

class RightShiftExpressionTest {
    private Expression firstNumber = Mockito.mock(NumberExpression.class);
    private Expression secondNumber = Mockito.mock(NumberExpression.class);

    @InjectMocks
    private RightShiftExpression rightShiftExpression = new RightShiftExpression(firstNumber, secondNumber);

    @Test
    void interpret() {
        Mockito.when(firstNumber.interpret()).thenReturn(10);
        Mockito.when(secondNumber.interpret()).thenReturn(2);

        Assertions.assertEquals(10>>2, rightShiftExpression.interpret());

        Mockito.verify(firstNumber).interpret();
        Mockito.verify(secondNumber).interpret();
    }
}