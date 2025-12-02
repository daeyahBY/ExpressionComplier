public class Printer {

    // Recursive helper
    private void print(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        // Print right
        print(node.right, prefix + (isLeft ? "|   " : "    "), false);

        // Print this node
        // Use "/--" for right child, "\--" for left child
        System.out.println(prefix + (isLeft ? "\\-- " : "/-- ") + node.value);

        // Print left
        print(node.left, prefix + (isLeft ? "    " : "|   "), true);
    }

    // Public entry point
    public void print(Node node) {
        print(node, "", false);
    }
}

