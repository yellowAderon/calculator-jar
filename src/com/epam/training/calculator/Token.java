package com.epam.training.calculator;

import java.util.Objects;

class Token {

    private TokenType type;
    private String value;

    Token(TokenType type, char value) {
        this.type = type;
        this.value = Character.toString(value);
    }

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    TokenType getType() {
        return type;
    }

    String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return value == token.value && type == token.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Sign{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}