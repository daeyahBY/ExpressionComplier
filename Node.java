public class Node {
    String value; // could be operator or operand
    Node left, right; // left and right children

    //Constructor 
 public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }


    //leaf
    public static Node createLeaf(String value) { // operand node
        return new Node(value, null, null);
    }


    // binary operation node: + - * /
    public static Node binaryOpNode(String op, Node left, Node right) {
        return new Node(op, left, right);
    }

    // unary operation node: - (negation)
    public static Node unaryOpNode(String op, Node operand) {
        return new Node(op, operand, null);
    }
}
