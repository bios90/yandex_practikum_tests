/*
Id успешного решения на контесте 52382929
-- ПРИНЦИП РАБОТЫ --
Процесс удаления из бинарного дерева происходит следующим образом. Сначала идет поиск узла в дереве с заданным ключем. Далее возможны 2 варианта. 1) У узла есть максимум 1 потомок. Тогда просто удаляем ссылку на этот узел у его родителя и подставляем один из его дочерних при необходимости. 2) У узла есть два дочерних узла. Находим самый правый узел в левом поддереве. Переставляем его значение найденному ранее узлу, а так же перезаписываем левое дерево самого правого узла его родителю.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность будет O(h) так как для решения задачи нужен максимум один проход в глубину дерева.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(1) так как хранение дополнительных переменных не зависит от размера дерева.
*/

public class TreeRemove2 {
    public static void main(String[] args) {
        test();
    }

    public static Node remove(Node root, int key) {
        return removeNodeIterative(root, key);
    }

    private static Node removeNodeIterative(Node root, int k) {
        Node parent = null;
        Node current = root;

        while (current != null && current.getValue() != k) {
            parent = current;
            if (k > current.getValue()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        if (current == null) {
            return root;
        }

        if (current.getLeft() == null || current.getRight() == null) {
            Node nodeFoReplacing;
            if (current.getLeft() == null) {
                nodeFoReplacing = current.getRight();
            } else {
                nodeFoReplacing = current.getLeft();
            }

            if (parent == null) {
                return nodeFoReplacing;
            }

            if (parent.getLeft() == current) {
                parent.setLeft(nodeFoReplacing);
            } else {
                parent.setRight(nodeFoReplacing);
            }
        } else {
            Node pParent = null;
            Node p = current.getLeft();
            while (p.getRight() != null) {
                pParent = p;
                p = p.getRight();
            }

            if (pParent != null) {
                pParent.setRight(p.getLeft());
            } else {
                current.setLeft(p.getLeft());
            }
            current.setValue(p.getValue());
        }

        return root;
    }

    private static Node findMaxInLeft(Node node) {
        if (node == null) {
            return null;
        }
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    private static void test() {
        Node root = new Node(new Node(null, null, 2), null, 1);
        Node newHead = remove(root, 1);
        System.out.println(newHead.getValue());

        //        Node root =
        //                new Node(null, new Node(null, new Node(null, new Node(null, null, 4), 3),
        // 2), 1);
        //        Node newHead = remove(root, 3);
        //        System.out.println(newHead.getRight().getRight().getValue());

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
