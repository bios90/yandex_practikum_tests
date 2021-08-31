import java.util.*;

public class TreeMaxPath {
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        test();
    }

    public static int treeSolution(Node head) {
        return getMaxSumIterative(head);
//        getMaxSum(head);
//        return max;
    }

    private static int getMaxSum(Node node) {
        if (node.left == null && node.right == null) {
            return node.value;
        }

        int maxSumLeft = (node.left != null) ? getMaxSum(node.left) : 0;
        int maxSumRight = (node.right != null) ? getMaxSum(node.right) : 0;

        int sumMax = Math.max(maxSumLeft + node.value, maxSumRight + node.value);
        if (node.value > sumMax) {
            sumMax = node.value;
        }
        int sumTriple = maxSumLeft + node.value + maxSumRight;
        if (sumMax > max) {
            max = sumMax;
        }
        if (sumTriple > max) {
            max = sumTriple;
        }
        return sumMax;
    }

    private static int getMaxSumIterative(Node head) {
        int m = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        ArrayDeque<Node> stack = new ArrayDeque<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                Node node = queue.peek();
                queue.remove();
                stack.push(node);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
        }

        Map<Node, Integer> sizes = new HashMap<>();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            int sizeLeft = sizes.getOrDefault(node.left, 0);
            int sizeRight = sizes.getOrDefault(node.right, 0);

            int sumMax = Math.max(sizeLeft + node.value, sizeRight + node.value);
            int sumTriple = sizeLeft + node.value + sizeRight;

            if (node.value > sumMax) {
                sumMax = node.value;
            }
            if (sumMax > m) {
                m = sumMax;
            }
            if (sumTriple > m) {
                m = sumTriple;
            }
            sizes.put(node, sumMax);
        }

        return m;
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static void test() {
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);
        int max = treeSolution(node5);
        System.out.println(max);
        //        assert treeSolution(node5) == 6;
    }
}
