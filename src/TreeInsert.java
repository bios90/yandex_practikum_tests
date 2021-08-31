import org.w3c.dom.Node;

import java.util.ArrayDeque;

public class TreeInsert {

    public static void main(String[] args) {
        Node root = getTestTree();
        Node newRoot = insert(root, 6);
        System.out.println(newRoot.getLeft().getValue());
    }

    public static Node insert(Node root, int key) {
        insertIteratevily(root, key);
        return root;
    }

    private static void insertRecursevly(Node node, int key) {
        if (key < node.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(null, null, key));
            } else {
                insertRecursevly(node.getLeft(), key);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node(null, null, key));
            } else {
                insertRecursevly(node.getRight(), key);
            }
        }
    }

    private static void insertIteratevily(Node root, int key) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (key < node.getValue()) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(null, null, key));
                    break;
                } else {
                    stack.push(node.getLeft());
                }
            } else {
                if (node.getRight() == null) {
                    node.setRight(new Node(null, null, key));
                    break;
                } else {
                    stack.push(node.getRight());
                }
            }
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
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
        Node root = new Node(null, new Node(new Node(null, null, 7), null, 8), 7);
        return root;
    }

    private static void test() {
        Node node1 = new Node(null, null, 7);
        Node node2 = new Node(node1, null, 8);
        Node node3 = new Node(null, node2, 7);
        Node newHead = insert(node3, 6);
        assert newHead == node3;
        assert newHead.getLeft().getValue() == 6;
    }
}
