package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation("1.2*5/x=1.2");
        boolean isEquationCorrect = EquationHandler.checkEquation(equation);
        if(isEquationCorrect){

            DatabaseHandler.insertEquation(equation.getEquation());

            String leftPart = EquationConverter.convertToPolishNotation(equation.getLeftPartOfEquation());
            String rightPart = EquationConverter.convertToPolishNotation(equation.getRightPartOfEquation());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a root ");
            double root = scanner.nextDouble();
            boolean isRootCorrect = EquationSolver.solveEquation(leftPart, rightPart, root);
            if(isRootCorrect){
                int equationId = DatabaseHandler.getEquationId(equation);

                DatabaseHandler.insertRoot(equationId,root);

            }
            System.out.println(isRootCorrect);


            List<Equation> equations = DatabaseHandler.getEquationsByRoot(root);
            if (!equations.isEmpty()) {
                System.out.println("Equations with root " + root + ":");
                System.out.println(equations);
            } else {
                System.out.println("No equations found with root " + root);
            }
        }




    }
}