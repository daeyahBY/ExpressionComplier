public class Printer {

    public void print(Node node) {
        print(node, "", true);
    }

    private void print(Node node, String prefix, boolean isTail) {
        if (node == null) return;

        // print right subtree first 
        print(node.right, prefix + (isTail ? "│   " : "    "), false);

        // print this node
        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.value);

        // print left subtree
        print(node.left, prefix + (isTail ? "    " : "│   "), true);
    }
}
