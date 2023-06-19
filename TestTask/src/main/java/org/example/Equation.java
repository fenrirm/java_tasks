package org.example;

public class Equation {
    private String equation;
    private String leftPartOfEquation;
    private String rightPartOfEquation;
    public String getEquation() {
        return equation;
    }
    public Equation(String equation){
        this.equation = equation.trim();
        this.leftPartOfEquation = equation.substring(0,equation.indexOf('='));
        this.rightPartOfEquation = equation.substring(equation.indexOf('=')+1);
    }
    public void setLeftPartOfEquation(String leftPartOfEquation) {
        this.leftPartOfEquation = leftPartOfEquation;
    }
    public void setRightPartOfEquation(String rightPartOfEquation) {
        this.rightPartOfEquation = rightPartOfEquation;
    }

    public String getLeftPartOfEquation() {
        return leftPartOfEquation;
    }

    public String getRightPartOfEquation() {
        return rightPartOfEquation;
    }

    @Override
    public String toString() {
        return "equation='" + equation + '\'' +", ";
    }
}
