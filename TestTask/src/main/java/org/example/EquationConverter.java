package org.example;

import java.util.Stack;

public class EquationConverter {

    public static String convertToPolishNotation(String equationPart) {
        StringBuilder polishNotation = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < equationPart.length(); i++) {
            char currentChar = equationPart.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                polishNotation.append(currentChar);
            } else if (Character.isLetter(currentChar)) {
                polishNotation.append(currentChar);
            } else if (isOperator(currentChar)) {
                polishNotation.append(" ");
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) &&
                        getOperatorPrecedence(currentChar) <= getOperatorPrecedence(operatorStack.peek())) {
                    polishNotation.append(operatorStack.pop());
                    polishNotation.append(" ");
                }
                operatorStack.push(currentChar);
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
            } else if (currentChar == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    polishNotation.append(" ");
                    polishNotation.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid expression: mismatched parentheses");
                }
                operatorStack.pop();
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                throw new IllegalArgumentException("Invalid expression: mismatched parentheses");
            }
            polishNotation.append(" ");
            polishNotation.append(operatorStack.pop());
        }

        return polishNotation.toString().trim();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' ;
    }

    private static int getOperatorPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

}


