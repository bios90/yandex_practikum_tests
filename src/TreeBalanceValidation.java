import kotlin.Pair;
import org.w3c.dom.Node;

import java.util.*;

public class TreeBalanceValidation {

    private static boolean isValid = true;

    public static void main(String[] args) {
        Node root = getTestTree();
        boolean result = validateTreeBalanceIterative(root);
        System.out.println(result);
    }

    private static boolean validateTreeBalanceIterative(Node root) {
        int level = 1;
        int minLevel = Integer.MAX_VALUE;
        int maxLevel = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                Node node = queue.peek();
                queue.remove();

                if (node.left == null || node.right == null) {
                    if (level > maxLevel) {
                        maxLevel = level;
                    }
                    if (level < minLevel) {
                        minLevel = level;
                    }
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                levelSize--;
            }
            if (Math.abs(maxLevel - minLevel) > 1) {
                return false;
            }
            level++;
        }

        return true;
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private static int validateTreeBalance(Node node) {
        if (!isValid) {
            return -1;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        int heightLeft = node.left == null ? 0 : validateTreeBalance(node.left);
        int heightRight = node.right == null ? 0 : validateTreeBalance(node.right);

        if (heightLeft > heightRight) {
            if (heightLeft - heightRight > 1) {
                isValid = false;
                return -1;
            }
        } else {
            if (heightRight - heightLeft > 1) {
                isValid = false;
                return -1;
            }
        }

        int childHeight = Math.max(heightLeft, heightRight);
        return childHeight + 1;
    }

    private static Node getTestTree() {
        Node root = new Node(10);
        Node node1 = new Node(20);
        root.right = node1;
        Node node2 = new Node(30);
        root.left = node2;
        Node node3 = new Node(40);
        node2.right = node3;
        return root;
    }

    private static int getTreeHeight(Node node) {
        if (node.left == null && node.right == null) {
            return 1;
        }

        int heightLeft = node.left == null ? 0 : getTreeHeight(node.left);
        int heightRight = node.right == null ? 0 : getTreeHeight(node.right);

        int childHeight = Math.max(heightLeft, heightRight);
        return childHeight + 1;
    }

    private static int getHeightIterative(Node root) {
        ArrayDeque<Pair<Node, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 1));
        int max = 1;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> pair = stack.pop();
            if (pair.getSecond() > max) {
                max = pair.getSecond();
            }
            Node node = pair.getFirst();
            if (node.left != null) {
                stack.push(new Pair<>(node.left, pair.getSecond() + 1));
            }
            if (node.right != null) {
                stack.push(new Pair<>(node.right, pair.getSecond() + 1));
            }
        }

        return max;
    }

    private static int getHeightIterativeV2(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (true) {
            int nodeCount = queue.size();
            if (nodeCount == 0) {
                break;
            }

            height++;
            while (nodeCount > 0) {
                Node newNode = queue.peek();
                queue.remove();
                if (newNode.left != null) {
                    queue.add(newNode.left);
                }
                if (newNode.right != null) {
                    queue.add(newNode.right);
                }
                nodeCount--;
            }
        }

        return height;
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
}
