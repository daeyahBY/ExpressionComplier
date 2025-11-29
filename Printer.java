public class Printer {
   public void print(Node node, String indent) {
        if (node == null) return;
        System.out.println(indent + node.value);
        print(node.left, indent + "  ");
        print(node.right, indent + "  ");
    }
}
