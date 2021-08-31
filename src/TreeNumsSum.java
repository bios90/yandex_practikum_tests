import java.util.LinkedList;
import java.util.Queue;

public class TreeNumsSum {

    public static void main(String[] args) {
        test();
    }

    private static int sum = 0;

    public static int treeSolution(Node head) {
        return countSumIterative(head);
    }

    private static void countSum(Node node, String str) {
        String currentStr = str + node.value;

        if (node.left == null && node.right == null) {
            int currentSum = Integer.parseInt(str + node.value);
            sum += currentSum;
        }
        if (node.left != null) {
            countSum(node.left, currentStr);
        }
        if (node.right != null) {
            countSum(node.right, currentStr);
        }
    }

    private static int countSumIterative(Node head) {
        int sum = 0;
        Queue<CountData> queue = new LinkedList<>();
        queue.add(new CountData(head, ""));
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                CountData data = queue.peek();
                queue.remove();
                String str = data.str + data.node.value;
                if (data.node.left == null && data.node.right == null) {
                    int num = Integer.parseInt(str);
                    sum += num;
                }
                if (data.node.left != null) {
                    queue.add(new CountData(data.node.left, str));
                }
                if (data.node.right != null) {
                    queue.add(new CountData(data.node.right, str));
                }
                count--;
            }
        }
        return sum;
    }

    private static class CountData {
        Node node;
        String str;

        public CountData(Node node, String str) {
            this.node = node;
            this.str = str;
        }
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
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        int result = treeSolution(node5);
        System.out.println(result);
        assert treeSolution(node5) == 275;
    }
}
