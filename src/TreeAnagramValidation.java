import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeAnagramValidation {

    public static void main(String[] args) {
        test();
    }

    public static boolean treeSolution(Node head) {
        List<Node> levelNodes = new ArrayList<>();
        List<Node> tempNodes = new ArrayList<>();
        levelNodes.add(head);
        while (levelNodes.size() > 0) {
            tempNodes.clear();
            int left = 0;
            int right = levelNodes.size() - 1;
            while (left < right) {
                Node nodeLeft = levelNodes.get(left);
                Node nodeRight = levelNodes.get(right);
                System.out.println("Checking "+nodeLeft.value+" and "+nodeRight.value);
                if (nodeLeft.value != nodeRight.value) {
                    return false;
                }
                left++;
                right--;
            }

            for (Node node : levelNodes) {
                if (node.left != null) {
                    tempNodes.add(node.left);
                }
                if (node.right != null) {
                    tempNodes.add(node.right);
                }
            }
            levelNodes.clear();
            levelNodes.addAll(tempNodes);
        }

        return true;
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
        Node node1 = new Node(3, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        boolean result = treeSolution(node7);
        System.out.println("Result is " + result);
        //        assert treeSolution(node7);
    }
}
