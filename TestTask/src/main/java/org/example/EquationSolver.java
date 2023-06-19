package org.example;

import java.util.Stack;

public class EquationSolver {
    public static boolean solveEquation(String leftPart, String rightPart, double x) {
        double leftResult = evaluateExpression(leftPart, x);
        double rightResult = evaluateExpression(rightPart, x);

        return leftResult == rightResult;
    }

    private static double evaluateExpression(String expression, double x) {
        String[] tokens = expression.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("x")) {
                stack.push(x);
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(token, operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static double performOperation(String operator, double operand1, double operand2) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }


}
