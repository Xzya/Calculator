package calculator.xzya.ro.calculator;

import java.util.LinkedList;
import java.util.Stack;

/**
 * This is a simple postfix calculator implementation.
 *
 * @author Dumitru Mihail Cristian
 * @see    //http://en.wikipedia.org/wiki/Reverse_Polish_notation
 */

public class Postfix {

    private Parser parser;

    public Postfix() {
        this.parser = new Parser();
    }

    /**
     * Evaluates the expression
     *
     * @param input - input linked list, each operator/number is
     *              stored in the list as an element
     * @return string - result as a string
     */
    public String evaluate(LinkedList<String> input) {
        String result = null;
        LinkedList<String> output = parser.parse(input);
        if (output == null){
            return "ERROR";
        }

        result = evaluater(output);

        return result;
    }

    /**
     * Helper function
     *
     * @param incomingOutput - output from the parser
     * @return string - result as a string
     */
    private String evaluater(LinkedList<String> incomingOutput) {
        Stack<String> stack = new Stack<String>();
        LinkedList<String> output = (LinkedList<String>) incomingOutput.clone();

        while (!output.isEmpty()) {
            String token = output.removeFirst();

            if (Checker.isNumeric(token)) {
                stack.push(token);
            } else {
                int n = Checker.numberOfParameters(token);
                if (stack.size() < n) {
                    System.err.println("There are insufficient values in the expression.");
                } else {
                    LinkedList<String> arguments = new LinkedList<String>();
                    for (int i = 0; i < n; i++) {
                        arguments.push(stack.pop());
                    }
                    Double result = evaluateOperator(token, arguments);
                    if (result != null){
                        stack.push(result.toString());
                    }
                }
            }
        }
        if (stack.size() == 1) {
            if (!stack.isEmpty()){
                return stack.pop();
            }
        } else {
            System.err.println("The user input has too many values.");
            System.out.println(stack.toString());
        }

        return "ERROR";
    }

    /**
     * Helper function
     * Checks the operator and applies the corresponding operation
     *
     * @param token     - the operator
     * @param arguments - list of arguments for the operator
     * @return
     */
    private Double evaluateOperator(String token, LinkedList<String> arguments) {
        Double result = null;

        switch (token) {
            case "+":
                result = add(arguments.pop(), arguments.pop());
                break;
            case "-":
                result = subtract(arguments.pop(), arguments.pop());
                break;
            case "*":
                result = multiply(arguments.pop(), arguments.pop());
                break;
            case "/":
                result = divide(arguments.pop(), arguments.pop());
                break;
            case "^":
                result = pow(arguments.pop(), arguments.pop());
                break;
            case "!":
                Integer temp = factorial(arguments.pop());
                if (temp != null){
                    result = temp.doubleValue();
                } else {
                    return null;
                }
                break;
            case "sqrt":
                result = sqrt(arguments.pop());
                break;
            case "sin":
                result = sin(arguments.pop());
                break;
            case "asin":
                result = asin(arguments.pop());
                break;
            case "cos":
                result = cos(arguments.pop());
                break;
            case "acos":
                result = acos(arguments.pop());
                break;
            case "tan":
                result = tan(arguments.pop());
                break;
            case "atan":
                result = atan(arguments.pop());
                break;
            case "log":
                result = log(arguments.pop());
                break;
            case "log2":
                result = log2(arguments.pop());
                break;
        }


        return result;
    }

    /**
     * Adds two numbers together
     *
     * @param a first number
     * @param b second number
     * @return result
     */
    private double add(String a, String b) {
        Double valA = Double.parseDouble(a);
        Double valB = Double.parseDouble(b);

        return valA + valB;
    }

    /**
     * Subtracts two numbers
     *
     * @param a first number
     * @param b second number
     * @return result
     */
    private double subtract(String a, String b) {
        Double valA = Double.parseDouble(a);
        Double valB = Double.parseDouble(b);

        return valA - valB;
    }

    /**
     * Multiply two numbers together
     *
     * @param a first number
     * @param b second number
     * @return result
     */
    private double multiply(String a, String b) {
        Double valA = Double.parseDouble(a);
        Double valB = Double.parseDouble(b);

        return valA * valB;
    }

    /**
     * Divides two numbers
     *
     * @param a first number
     * @param b second number
     * @return result
     */
    private double divide(String a, String b) {
        Double valA = Double.parseDouble(a);
        Double valB = Double.parseDouble(b);

        return valA / valB;
    }

    /**
     * Raises the first number to the power of the second number
     *
     * @param a first number
     * @param b second number
     * @return result
     */
    private double pow(String a, String b) {
        Double valA = Double.parseDouble(a);
        Double valB = Double.parseDouble(b);

        return Math.pow(valA, valB);
    }

    /**
     * Gives the square root of the number
     *
     * @param a the number
     * @return result
     */
    private double sqrt(String a) {
        Double valA = Double.parseDouble(a);

        return Math.sqrt(valA);
    }

    /**
     * Gives the sine
     * @param a the number
     * @return result
     */
    private double sin(String a){
        Double valA = Double.parseDouble(a);

        return Math.sin(valA);
    }

    /**
     * Gives the arcsine
     * @param a the number
     * @return result
     */
    private double asin(String a){
        Double valA = Double.parseDouble(a);

        return Math.asin(valA);
    }

    /**
     * Gives the cosine
     * @param a the number
     * @return result
     */
    private double cos(String a){
        Double valA = Double.parseDouble(a);

        return Math.cos(valA);
    }

    /**
     * Gives the arccosine
     * @param a the number
     * @return result
     */
    private double acos(String a){
        Double valA = Double.parseDouble(a);

        return Math.acos(valA);
    }

    /**
     * Gives the tangent
     * @param a the number
     * @return result
     */
    private double tan(String a){
        Double valA = Double.parseDouble(a);

        return Math.tan(valA);
    }

    /**
     * Gives the arctangent
     * @param a the number
     * @return result
     */
    private double atan(String a){
        Double valA = Double.parseDouble(a);

        return Math.atan(valA);
    }

    /**
     * Gives the log
     * @param a the number
     * @return result
     */
    private Double log(String a){
        Double valA = Double.parseDouble(a);

        return Math.log10(valA);
    }

    /**
     * Gives the log2
     * @param a the number
     * @return result
     */
    private double log2(String a){
        Double valA = Double.parseDouble(a);

        return Math.log(valA)/Math.log(2);
    }


    private Integer factorial(String a) {
        Integer n = null;
        try {
            n = Integer.parseInt(a);
        } catch(NumberFormatException e){
            e.printStackTrace();
            return null;
        }

        int p = 1;
        while (n != 0) {
            p *= n;
            n -= 1;
        }
        return p;
    }

    public static void main(String[] args) {

        Postfix evaluator = new Postfix();

        String s1 = "1+((2*5)^(3/5)/sqrt(5))";

    }

}
