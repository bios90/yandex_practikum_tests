public class TreeRemove {

    public static void main(String[] args) {
        test();
    }

    public static Node remove(Node root, int key) {
        Node[] nodeToDeleteWithParent = findNodeByKey(root, key);

        if (nodeToDeleteWithParent[0] == null) {
            return root;
        }

        Node nodeToDelete = nodeToDeleteWithParent[0];
        Node parent = nodeToDeleteWithParent[1];

        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (parent == null) {
                return null;
            } else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (nodeToDelete.getRight() == null) {
            if (parent == null) {
                root = nodeToDelete.getLeft();
            } else {
                if (parent.getLeft() == nodeToDelete) {
                    parent.setLeft(nodeToDelete.getLeft());
                } else {
                    parent.setRight(nodeToDelete.getLeft());
                }
            }
        } else if (nodeToDelete.getLeft() == null) {
            if (parent == null) {
                root = nodeToDelete.getRight();
            } else {
                if (parent.getLeft() == nodeToDelete) {
                    parent.setLeft(nodeToDelete.getRight());
                } else {
                    parent.setRight(nodeToDelete.getRight());
                }
            }

        } else {
            Node[] maxRightWithParent = findMaxChildFromLeft(nodeToDelete);
            swapNodesFromLeftTree(nodeToDeleteWithParent, maxRightWithParent);
            if (parent == null) {
                root = maxRightWithParent[0];
            }
        }

        return root;
    }

    private static void swapNodesFromLeftTree(Node[] nodesUp, Node[] nodesDown) {
        Node d = nodesUp[0];
        Node dParent = nodesUp[1];
        Node p = nodesDown[0];
        Node pParent = nodesDown[1];

        if (pParent == d) {
            if (dParent != null) {
                if (dParent.getLeft() == d) {
                    dParent.setLeft(p);
                } else {
                    dParent.setRight(p);
                }
            }
            p.setRight(d.getRight());
        } else {

            if (p.getLeft() != null) {
                pParent.setRight(p.getLeft());
                p.setLeft(null);
            } else {
                pParent.setRight(null);
            }

            if (dParent != null) {
                if (dParent.getLeft() == d) {
                    dParent.setLeft(p);
                } else {
                    dParent.setRight(p);
                }
            }
            p.setLeft(d.getLeft());
            p.setRight(d.getRight());
        }
    }

    private static Node[] findNodeByKey(Node root, int key) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            if (node.getValue() == key) {
                break;
            }
            parent = node;
            if (key > node.getValue()) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        return new Node[] {node, parent};
    }

    private static Node[] findMaxChildFromLeft(Node root) {
        Node parent = root;
        Node node = root.getLeft();
        while (node.getRight() != null) {
            parent = node;
            node = node.getRight();
        }
        return new Node[] {node, parent};
    }

    private static void test() {
        //        Node root = new Node(new Node(null, null, 2), null, 1);
        //        Node newHead = remove(root, 1);
        //        System.out.println(newHead.getValue());

        Node root =
                new Node(null, new Node(null, new Node(null, new Node(null, null, 4), 3), 2), 1);
        Node newHead = remove(root, 3);
        System.out.println(newHead.getRight().getRight().getValue());

        //        Node node32 = new Node(null, null, 32);
        //        Node node29 = new Node(null, node32, 29);
        //        Node node11 = new Node(null, null, 11);
        //        Node node20 = new Node(node11, node29, 20);
        //
        //        Node node99 = new Node(null, null, 99);
        //        Node node72 = new Node(null, null, 72);
        //        Node node91 = new Node(node72, node99, 91);
        //        Node node50 = new Node(null, null, 50);
        //        Node node65 = new Node(node50, node91, 65);
        //
        //        Node node41 = new Node(node20, node65, 41);
        //        Node newHead = remove(node41, 41);
        //        System.out.println(newHead.getValue());
        //        Node node1 = new Node(null, null, 2);
        //        Node node2 = new Node(node1, null, 3);
        //        Node node3 = new Node(null, node2, 1);
        //        Node node4 = new Node(null, null, 6);
        //        Node node5 = new Node(node4, null, 8);
        //        Node node6 = new Node(node5, null, 10);
        //        Node node7 = new Node(node3, node6, 5);
        //        Node newHead = remove(node7, 10);
        //
        //        System.out.println(
        //                "Result "
        //                        + newHead.getRight().getValue()
        //                        + " - "
        //                        + newHead.getRight().getLeft().getValue());
        //        //        System.out.println(
        //        //                "NewHead = "
        //        //                        + newHead.getValue()
        //        //                        + " and its right is "
        //        //                        + newHead.getRight().getValue());
        //        assert newHead.getValue() == 5;
        //        assert newHead.getRight() == node5;
        //        assert newHead.getRight().getValue() == 8;
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
}
