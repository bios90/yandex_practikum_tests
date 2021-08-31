import kotlin.Pair;

import java.util.ArrayDeque;

public class TreeHeightGetter {

    public static void main(String[] args){
        test();
    }

    public static int treeSolution(Node head) {
        int max = Integer.MIN_VALUE;
        ArrayDeque<Pair> stack = new ArrayDeque<>();
        stack.add(new Pair(head, 1));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int level = pair.level;
            if (level > max) {
                max = level;
            }
            if (pair.node.left != null) {
                stack.push(new Pair(pair.node.left, level + 1));
            }
            if (pair.node.right != null) {
                stack.push(new Pair(pair.node.right, level + 1));
            }
        }
        return max;
    }

    private static class Pair {
        Node node;
        int level;

        public Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node() {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        int result = treeSolution(node5);
        System.out.println(result);
//        assert treeSolution(node5) == 3;
    }
}
