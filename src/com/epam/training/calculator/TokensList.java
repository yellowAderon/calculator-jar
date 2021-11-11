package com.epam.training.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class TokensList {
    private List<Token> tokens;
    private int index = 0;

    TokensList(List<Token> expression) {
        this.tokens = expression;
    }

    List<Token> getLexemesList() {
        return tokens;
    }

    int getIndex() {
        return index;
    }

    Token getNext(){
        return tokens.get(index++);
    }

    void goBack(){
        if(index>0)
            index--;
    }

    static List<Token> getTokens(String expression) {
        List<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char c = expression.charAt(pos);
            switch (c) {
                case '(':
                    tokens.add(new Token(TokenType.LEFT_BRACKET, c));
                    pos++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RIGHT_BRACKET, c));
                    pos++;
                    break;
                case '+':
                    tokens.add(new Token(TokenType.OP_PLUS, c));
                    pos++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.OP_MINUS, c));
                    pos++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.OP_MULT, c));
                    pos++;
                    break;
                case '/':
                    tokens.add(new Token(TokenType.OP_DIV, c));
                    pos++;
                    break;
                default:
                    if (c >= '0' && c <= '9') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(pos);
                        } while (c >= '0' && c <= '9');
                        tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new RuntimeException();
                        }
                        pos++;
                    }
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokensList that = (TokensList) o;
        return Objects.equals(tokens, that.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }

    @Override
    public String toString() {
        return "TokensList{" +
                "Tokens=" + tokens +
                ", index=" + index +
                '}';
    }
}