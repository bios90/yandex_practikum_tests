import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBalanceValidation2 {

//    public static boolean treeSolution(Node head) {
//        return validateTreeBalanceIterative(head);
//    }
//
//    private static boolean validateTreeBalanceIterative(Node root) {
//        int level = 1;
//        int minLevel = Integer.MAX_VALUE;
//        int maxLevel = Integer.MIN_VALUE;
//
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            int levelSize = queue.size();
//            while (levelSize > 0) {
//                Node node = queue.peek();
//                queue.remove();
//
//                if (node.left == null || node.right == null) {
//                    if (level > maxLevel) {
//                        maxLevel = level;
//                    }
//                    if (level < minLevel) {
//                        minLevel = level;
//                    }
//                }
//
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//                levelSize--;
//            }
//            if (Math.abs(maxLevel - minLevel) > 1) {
//                return false;
//            }
//            level++;
//        }
//
//        return true;
//    }
}
