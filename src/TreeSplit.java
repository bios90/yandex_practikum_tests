import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeSplit {
    public static void main(String[] args) {
        test();
    }

    public static List<Node> split(Node root, int k) {
        if (root == null) {
            List<Node> list = new ArrayList<>();
            list.add(null);
            list.add(null);
            return list;
        }

        int sizeLeft = root.getLeft() != null ? root.getLeft().getSize() : 0;
        if (sizeLeft + 1 <= k) {
            int kFixed = k - sizeLeft - 1;
            List<Node> splitted = splitMy(root.getRight(), kFixed);
            root.setRight(splitted.get(0));
            List<Node> result = new ArrayList<>();
            result.add(root);
            result.add(splitted.get(1));
            getSize(root);
            return result;
        } else {
            List<Node> splitted = splitMy(root.getLeft(), k);
            root.setLeft(splitted.get(1));
            List<Node> result = new ArrayList<>();
            result.add(splitted.get(0));
            result.add(root);
            return result;
        }
    }

    private static int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        int sizeLeft = getSize(node.getLeft());
        int sizeRight = getSize(node.getRight());
        node.setSize(sizeLeft + sizeRight + 1);
        return node.getSize();
    }

    public static List<Node> splitMy(Node root, int k) {
        if (root == null) {
            //            System.out.println("Root null will return (null,null)");
            //            System.out.println("****************************");
            List<Node> list = new ArrayList<>();
            list.add(null);
            list.add(null);
            return list;
        }

        //        int sizeLeft = getSize(root.getLeft());
        int sizeLeft = root.getLeft() != null ? root.getLeft().getSize() : 0;
        if (sizeLeft + 1 <= k) {
            int kFixed = k - sizeLeft - 1;
            List<Node> splitted = splitMy(root.getRight(), kFixed);
            root.setRight(splitted.get(0));
            List<Node> result = new ArrayList<>();
            result.add(root);
            result.add(splitted.get(1));
            //            System.out.println(
            //                    "For num " + root.getValue() + " SL is lower, will go right with k
            // " + kFixed);
            //
            //            logResult(result);
            //            System.out.println("****************************");
            return result;
        } else {
            List<Node> splitted = splitMy(root.getLeft(), k);
            root.setLeft(splitted.get(1));
            List<Node> result = new ArrayList<>();
            result.add(splitted.get(0));
            result.add(root);
            //            System.out.println(
            //                    "For num " + root.getValue() + " SL is higher, will go left with k
            // " + k);
            //            logResult(result);
            //            System.out.println("****************************");
            return result;
        }
    }

    private static void logResult(List<Node> result) {
        Node tree1 = result.get(0);
        String strLeft = "";
        if (tree1 == null) {
            strLeft = "null";
        } else {
            strLeft = tree1.left == null ? "null" : String.valueOf(tree1.left.value);
            strLeft += " - " + tree1.getValue() + " - ";
            strLeft += tree1.right == null ? "null" : String.valueOf(tree1.right.value);
        }

        Node tree2 = result.get(1);
        String strRight = "";
        if (tree2 == null) {
            strRight = "null";
        } else {
            strRight = tree2.left == null ? "null" : String.valueOf(tree2.left.value);
            strRight += " - " + tree2.getValue() + " - ";
            strRight += tree2.right == null ? "null" : String.valueOf(tree2.right.value);
        }

        String resultStr = "( " + strLeft + " , " + strRight + ")";
        System.out.println(resultStr);
    }

    private static class Node {

        private Node left;
        private Node right;
        private int value;
        private int size;

        Node(Node left, Node right, int value, int size) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.size = size;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static void test() {
        Node node1 = new Node(null, null, 3, 1);
        Node node2 = new Node(null, node1, 2, 2);
        Node node3 = new Node(null, null, 8, 1);
        Node node4 = new Node(null, null, 11, 1);
        Node node5 = new Node(node3, node4, 10, 3);
        Node node6 = new Node(node2, node5, 5, 6);
        List<Node> res = split(node6, 4);
        System.out.println("Ressize left is " + res.get(0).getSize());
        assert res.get(0).getSize() == 4;
        assert res.get(1).getSize() == 2;
    }
}
