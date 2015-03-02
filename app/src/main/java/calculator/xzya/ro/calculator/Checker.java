package calculator.xzya.ro.calculator;

public class Checker {

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isFunction(String token) {
        if (
                token.equals("sqrt") ||
                        token.equals("sin") ||
                        token.equals("asin") ||
                        token.equals("cos") ||
                        token.equals("acos") ||
                        token.equals("tan") ||
                        token.equals("atan") ||
                        token.equals("log") ||
                        token.equals("log2")) {
            return true;
        }
        return false;
    }

    public static boolean isFunctionSeparator(String token) {
        if (
                token.equals(",")) {
            return true;
        }
        return false;
    }

    public static boolean isOperator(String token) {
        if (
                token.equals("+") ||
                        token.equals("-") ||
                        token.equals("*") ||
                        token.equals("/") ||
                        token.equals("^") ||
                        token.equals("%") ||
                        token.equals("!")) {
            return true;
        }
        return false;
    }

    public static boolean isLeftAssociative(String token) {
        if (
                token.equals("*") ||
                        token.equals("/") ||
                        token.equals("+") ||
                        token.equals("-") ||
                        token.equals("!")) {
            return true;
        }
        return false;
    }

    public static int getPrecedence(String token) {
        switch (token) {
            case "+":
                return 2;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 3;
            case "%":
                return 3;
            case "^":
                return 4;
            case "!":
                return 5;
            default:
                return 1;
        }
    }

    public static int numberOfParameters(String token) {
        switch (token) {
            case "+":
                return 2;
            case "-":
                return 2;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 2;
            case "%":
                return 2;
            case "!":
                return 1;
            case "sqrt":
                return 1;
            case "sin":
                return 1;
            case "asin":
                return 1;
            case "cos":
                return 1;
            case "acos":
                return 1;
            case "tan":
                return 1;
            case "atan":
                return 1;
            case "log":
                return 1;
            case "log2":
                return 1;
            default:
                return -1;
        }
    }

}
