import java.io.IOException;
import java.util.Arrays;

public class GrafMinTree {

    private static int[][] weights;

    public static void main(String[] args) throws IOException {
        initFields();
        int[] parents = new int[weights.length];
        int[] minDistances = new int[weights.length];
        boolean[] includedNodes = new boolean[weights.length];
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[0] = 0;
        parents[0] = -1;

        for (int i = 0; i < weights.length - 1; i++) {
            int minNode = findMinNode(includedNodes, minDistances);
            includedNodes[minNode] = true;

            for (int j = 0; j < weights.length; j++) {
                if (weights[minNode][j] != 0 && includedNodes[j] == false) {
                    if (weights[minNode][j] < minDistances[j]) {
                        parents[j] = minNode;
                        minDistances[j] = weights[minNode][j];
                    }
                }
            }
        }

        printResults(parents);
    }

    private static int findMinNode(boolean[] includedNodes, int[] minKeys) {
        int minDistance = Integer.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < weights.length; i++) {
            if (includedNodes[i] == false && minKeys[i] < minDistance) {
                minDistance = minKeys[i];
                minNode = i;
            }
        }
        return minNode;
    }

    private static void initFields() throws IOException {
        weights = new int[][]{  {0, 2, 0, 6, 0},
                                {2, 0, 3, 8, 5},
                                {0, 3, 0, 0, 7},
                                {6, 8, 0, 0, 9},
                                {0, 5, 7, 9, 0}};
    }

    private static void printResults(int parents[]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < weights.length; i++)
            System.out.println(parents[i] + " - " + i + "\t" + weights[i][parents[i]]);
    }
}
