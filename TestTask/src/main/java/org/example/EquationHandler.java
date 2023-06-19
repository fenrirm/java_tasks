package org.example;

import java.util.Stack;

public class EquationHandler {

    public static boolean checkEquation(Equation equation) {
        if (!checkForParenthesesPosition(equation)) {
            System.out.println("There are problems with placement of parentheses! Check the equation, please!");
            return false;
        }
        if (!checkForParenthesesAmount(equation)) {
            System.out.println("There are some problems with parentheses! Check the equation, please!");
            return false;
        }
        if (!checkForOperatorsCorrectness(equation)) {
            System.out.println("There are some problems with operators! Check the equation, please!");
            return false;
        }

        System.out.println("Equation is correct!");
        return true;
    }


    private static boolean checkForParenthesesAmount(Equation equationPart) {
        int count = 0;
        for (char symbol : equationPart.toString().toCharArray()) {
            if (symbol == '(') {
                count++;
            } else if (symbol == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;

    }
    private static boolean checkForParenthesesPosition(Equation equation){
        if(equation.toString().charAt(0) == ')'){
            return false;
        }
        for (int i = 0; i < equation.toString().length()-1; i++) {
            if(equation.toString().charAt(i) == ')' && equation.toString().charAt(i+1) == '('){
                return false;
            }
        }
        return true;
    }


    private static boolean checkForOperatorsCorrectness(Equation equation) {
        if(equation.toString().charAt(0) == '*' || equation.toString().charAt(0) == '/'
                || isOperator(equation.toString().charAt(equation.toString().length()-1))){
            return false;
        }
        for (int i = 0; i < equation.toString().length(); i++) {
            if ((isOperator(equation.toString().charAt(i)) && isOperator(equation.toString().charAt(i+1)))) {
               /* if ((equationCharArray[i] == '(' && !(equationCharArray[i + 1] == '(')) ||
                        (equationCharArray[i] == ')' && !(equationCharArray[i + 1] == ')'))) {
                }*/
                if(isOperator(equation.toString().charAt(i)) && equation.toString().charAt(i+1) == '-'){
                    continue;
                }else {
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean isOperator(char symbol) {
        switch (symbol) {
            case '+', '-', '*', '/' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }



}
