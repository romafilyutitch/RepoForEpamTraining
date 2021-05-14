package com.epam.jwd.model.impl;

import com.epam.jwd.model.Lexeme;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Symbol implements Lexeme {
    private final String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getAsString() {
        return symbol;
    }

    @Override
    public List<Lexeme> getAsList() {
        return Collections.singletonList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return Objects.equals(symbol, symbol1.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
