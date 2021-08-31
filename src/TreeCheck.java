import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class TreeCheck {
    private static Set<Integer> set = new HashSet<>();
    private static boolean isValid = true;

    public static void main(String[] args) {
        test();
    }

    public static boolean treeSolution(Node head) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.add(head);
        while (stack.size() > 0) {
            Node node = stack.pop();
            if (set.contains(node.value)) {
                isValid = false;
                break;
            }

            set.add(node.value);

            if (node.left != null) {
                if (node.left.value > node.value) {
                    isValid = false;
                    break;
                }
                stack.add(node.left);
            }

            if (node.right != null) {
                if (node.right.value < node.value) {
                    isValid = false;
                    break;
                }
                stack.add(node.right);
            }
        }

        return isValid;
    }

    private static void makeCheck(Node node) {
        if (set.contains(node.value)) {
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        set.add(node.value);

        if (node.left != null) {
            if (node.left.value > node.value) {
                isValid = false;
                return;
            }
            makeCheck(node.left);
        }
        if (node.right != null) {
            if (node.right.value < node.value) {
                isValid = false;
                return;
            }
            makeCheck(node.right);
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
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}
