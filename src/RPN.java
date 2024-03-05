import java.util.Stack;

public class RPN {

    private static final String[] OP = { "+", "-", "*", "/", "%", "^", "\u221A" };

    public static Stack<RPNElement> inFixToRPN(Stack<String> problem) {
        Stack<RPNElement> problemRPN = new Stack<>();
        Stack<String> opStack = new Stack<>();
        boolean flag = false;
    
        for (String element : problem) {
            if (isNumber(element)) {
                double number = Double.parseDouble(element);
                if (flag) {
                    number *= -1;
                    flag = false;
                }
                problemRPN.push(new RPNElement(number));
            } else if (element.equals("-")) {
                if (problemRPN.isEmpty() || isOperator(problemRPN.peek().toString()) || element.equals("(") || element.equals(")")) {
                    opStack.push("-");//a-b
                } else {
                    //-n
                    flag = true;
                }
            } else if (isOperator(element)) {
                while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(element)) {
                    problemRPN.push(new RPNElement(opStack.pop()));
                }
                opStack.push(element);
            } else if (element.equals("(")) {
                opStack.push(element);
            } else if (element.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    problemRPN.push(new RPNElement(opStack.pop()));
                }
                opStack.pop();
            }
        }
        while (!opStack.isEmpty()) {
            problemRPN.push(new RPNElement(opStack.pop()));
        }
        return problemRPN;
    }

    private static int precedence(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "^":
            case "\u221A":
                return 3;
            default:
                return 0;
        }
    }

    private static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String str) {
        for (String op : OP) {
            if (op.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /*private static String toNegative(String number) {
        if (number.startsWith("-")) {
            return number.substring(1);
        } else {
            return "-" + number;
        }
    }*/

    public static double calculateRPN(Stack<RPNElement> rpnExpression) {
        Stack<Double> stack = new Stack<>();

        for (RPNElement element : rpnExpression) {
            if (element.isNumber()) {
                stack.push((Double) element.getValue());
            } else {
                double num2 = stack.isEmpty() ? 0 : stack.pop();
                double num1 = stack.isEmpty() ? 0 : stack.pop();
                String op = element.getOperator();
                double result = operate(num1, num2, op);
                stack.push(result);
            }
        }
        if (stack.size() != 1) {
            throw new IllegalStateException("Expressão mal formada ou incompleta");
        }
        //return stack.pop();
        return stack.isEmpty() ? 0 : stack.pop();
    }

    private static double operate(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Divisão por zero mano?");
                }
                return num1 / num2;
            case "%":
                return num1 * num2 / 100;
            case "^":
                return Math.pow(num1, num2);
            case "\u221A":
                return Math.pow(num2, 1 / num1);
            default:
                throw new IllegalArgumentException("Operador inválido: " + op);
        }
    }

    public static class RPNElement {
        private Object valor;
        private String op;

        public RPNElement(Object valor) {
            this.valor = valor;
            this.op = null;
        }

        public RPNElement(String op) {
            this.valor = null;
            this.op = op;
        }

        public Object getValue() {
            return valor;
        }

        public String getOperator() {
            return op;
        }

        public boolean isOperator() {
            return op != null;
        }

        public boolean isNumber() {
            return valor instanceof Double;
        }
    }
}
