package com.epam.jwd.interpreter;

import com.epam.jwd.interpreter.impl.InterpretEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InterpretEngineTest {
    private InterpretEngine interpretEngine = new InterpretEngine();

    @Test
    void makeInterpretation() {
        Assertions.assertEquals(13<<2, interpretEngine.makeInterpretation("13<<2"));
        Assertions.assertEquals(3>>5, interpretEngine.makeInterpretation("3>>5"));
        Assertions.assertEquals((~6&9|(3&4)), interpretEngine.makeInterpretation("~6&9|(3&4)"));
        Assertions.assertEquals((1&2&(3|(4&(1^5|6&47)|3)|2)|1), interpretEngine.makeInterpretation("(1&2&(3|(4&(1^5|6&47)|3)|2)|1)"));
        Assertions.assertEquals((~71&(2&3|(3&(2&1>>2|2)&2)|10&2))|78, interpretEngine.makeInterpretation("(~71&(2&3|(3&(2&1>>2|2)&2)|10&2))|78"));
    }
}