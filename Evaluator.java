public class Evaluator {
    double evaluate(Node node) throws EvaluationException {
        
        // Leaf node: operand
        if (node.left == null && node.right == null) {
            try {
                return Double.parseDouble(node.value);
            } catch (NumberFormatException e) {
                throw new EvaluationException("Invalid number: " + node.value);
            }
        }

        // Unary operation
        if (node.right == null) { // Unary operation
            double operand = evaluate(node.left);
            if (node.value.equals("-")) {
                return -operand;
            }
            throw new EvaluationException("Unknown unary operator: " + node.value);
        }

        // Binary operation
        double leftVal = evaluate(node.left);
        double rightVal = evaluate(node.right);

        // Perform the operation
        switch (node.value) {
            case "+":
                return leftVal + rightVal;
            case "-":
                return leftVal - rightVal;
            case "*":
                return leftVal * rightVal;
            case "/":
                if (rightVal == 0) {
                    throw new EvaluationException("Division by zero");
                }
                return leftVal / rightVal;
            default:
                throw new EvaluationException("Unknown operator: " + node.value);
        }
    }
}
