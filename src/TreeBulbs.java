import org.w3c.dom.Node;

import java.util.ArrayDeque;

public class TreeBulbs {

    public static void main(String[] args) {
        test();
    }

    public static int treeSolution(Node head) {
        int max = Integer.MIN_VALUE;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.add(head);
        while (stack.size() > 0) {
            Node node = stack.pop();
            if (node.value > max) {
                max = node.value;
            }
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return max;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        treeSolution(node4);
//        assert treeSolution(node4) == 3;
    }
}
