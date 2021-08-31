import org.w3c.dom.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayDeque;

public class TreeRangeSearch {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Node root = getTestTree();
        String result = getValuesSortedNoRecursion(root);
        System.out.println(result);
    }

    public static void printRange(Node root, int L, int R, BufferedWriter writer)
            throws IOException {
        Node min = getLowestNode(root, L);
        getValuesSortedWithRange(root, min.value, R, writer);
    }

    private static void getValuesSorted(Node node, StringBuilder out) {
        if (node.left != null) {
            getValuesSorted(node.left, out);
        }
        out.append(node.value).append(" ");
        if (node.right != null) {
            getValuesSorted(node.right, out);
        }
    }

    private static String getValuesSortedNoRecursion(Node root) {
        StringBuilder out = new StringBuilder();

        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node current = root;
        while (current != null || stack.size() > 0) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            out.append(current.getValue()).append(" ");
            current = current.getRight();
        }

        return out.toString();
    }

    private static void getValuesSortedWithRange(Node node, int L, int R, BufferedWriter writer)
            throws IOException {
        if (node.left != null) {
            getValuesSortedWithRange(node.left, L, R, writer);
        }
        if (node.value > R) {
            return;
        } else if (node.value >= L) {
            writer.append(String.valueOf(node.value)).append(' ');
        }
        if (node.right != null) {
            getValuesSortedWithRange(node.right, L, R, writer);
        }
    }

    private static Node getLowestNode(Node root, int numToFind) {
        Node min = root;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.value < numToFind) {
                if (node.right != null) {
                    stack.add(node.right);
                }
            } else {
                min = node;
                if (node.left != null) {
                    stack.add(node.left);
                }
            }
        }
        return min;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(int value, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static Node getTestTree() {
        Node root =
                new Node(
                        5,
                        new Node(1, null, new Node(2, null, null)),
                        new Node(
                                10,
                                new Node(9, new Node(8, new Node(8, null, null), null), null),
                                null));

        return root;
    }

    private static void test() throws IOException {
        //        Node node1 = new Node(null, null, 2);
        //        Node node2 = new Node(null, node1, 1);
        //        Node node3 = new Node(null, null, 8);
        //        Node node4 = new Node(null, node3, 8);
        //        Node node5 = new Node(node4, null, 9);
        //        Node node6 = new Node(node5, null, 10);
        //        Node node7 = new Node(node2, node6, 5);
        //        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        //        printRange(node7, 2, 8, writer);
        //        writer.flush();
        // expected output: 2 5 8 8
    }
}
