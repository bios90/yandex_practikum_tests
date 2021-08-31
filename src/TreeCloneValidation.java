import java.util.ArrayDeque;

public class TreeCloneValidation
{
    public static void main(String[] args){
        test();
    }

    public static boolean treeSolution(Node head1, Node head2) {
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        stack1.add(head1);
        ArrayDeque<Node> stack2 = new ArrayDeque<>();
        stack2.add(head2);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack1.size() != stack2.size()) {
                return false;
            }
            Node node1 = stack1.pop();
            Node node2 = stack2.pop();
            if (node1.value != node2.value) {
                return false;
            }
            if (node1.left == null && node2.left != null) {
                return false;
            }
            if (node2.left == null && node1.left != null) {
                return false;
            }
            if (node1.right == null && node2.right != null) {
                return false;
            }
            if (node2.right == null && node1.right != null) {
                return false;
            }
            if (node1.left != null) {
                stack1.push(node1.left);
            }
            if (node1.right != null) {
                stack1.push(node1.right);
            }
            if (node2.left != null) {
                stack2.push(node2.left);
            }
            if (node2.right != null) {
                stack2.push(node2.right);
            }
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
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(1, null, null);
        Node node5 = new Node(2, null, null);
        Node node6 = new Node(3, node4, node5);
        boolean result = treeSolution(node3,node6);
        System.out.println(result);
//        assert treeSolution(node3, node6);
    }
}
