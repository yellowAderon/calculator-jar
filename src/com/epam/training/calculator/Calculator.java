package com.epam.training.calculator;

import java.util.List;

public class Calculator {

    private Calculator(){}

    public static int calculate(String expression){
        List<Token> tokens = TokensList.getTokens(expression);
        TokensList tokenList = new TokensList(tokens);
        return count(tokenList);
    }

    private static int count(TokensList tokens) {
        Token token = tokens.getNext();
        if (token.getType() == TokenType.EOF) {
            return 0;
        } else {
            tokens.goBack();
            return plusMinus(tokens);
        }
    }

    private static int plusMinus(TokensList tokens) {
        int value = multDiv(tokens);
        while (true) {
            Token token = tokens.getNext();
            switch (token.getType()) {
                case OP_PLUS:
                    value += multDiv(tokens);
                    break;
                case OP_MINUS:
                    value -= multDiv(tokens);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    tokens.goBack();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private static int multDiv(TokensList lexemes) {
        int value = number(lexemes);
        while (true) {
            Token lexeme = lexemes.getNext();
            switch (lexeme.getType()) {
                case OP_MULT:
                    value *= number(lexemes);
                    break;
                case OP_DIV:
                    value /= number(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.goBack();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private static int number(TokensList lexemes) {
        Token lexeme = lexemes.getNext();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                int value = plusMinus(lexemes);
                lexeme = lexemes.getNext();
                if (lexeme.getType() != TokenType.RIGHT_BRACKET) {
                    throw new RuntimeException();
                }
                return value;
            default:
                throw new RuntimeException();
        }
    }
}