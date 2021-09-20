import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrafIslands2 {
    private static NeighboursLine[] nodes;
    private static int[][] weights;

    public static void main(String[] args) throws IOException {
        initFields();
        countDistances(3);
//        for (int i = 0; i < nodes.length; i++) {
//            countDistances(i);
//        }
        printArray(weights);
    }

    private static void countDistances(int num) {
        int[] distances = weights[num];
        boolean[] visited = new boolean[nodes.length];
        distances[num] = 0;

        int minNeighbour = getMinNeighbour(num, visited, distances);
        while (getMinNeighbour(num, visited, distances) > -1) {
            visited[minNeighbour] = true;
            NeighboursLine line = nodes[minNeighbour];
            if (line != null) {
                for (int neighbour : line) {
                    relax(minNeighbour, neighbour, distances);
                }
            }
            minNeighbour = getMinNeighbour(num, visited, distances);
        }
    }

    private static void relax(int u, int v, int[] distances) {
        if (weights[u][v] == Integer.MAX_VALUE || u == v) {
            return;
        }
        int distanceThroughNode = distances[u] + weights[u][v];
        if (distanceThroughNode < distances[v]) {
            distances[v] = distanceThroughNode;
        }
    }

    private static int getMinNeighbour(int node, boolean[] visited, int[] distances) {
        NeighboursLine line = nodes[node];
        if (line == null) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int minNeighbour = -1;
        for (Integer neighbour : line) {
            if (visited[neighbour]) {
                continue;
            }
            int distance = weights[node][neighbour];
            if (distance < min) {
                min = distance;
                minNeighbour = neighbour;
            }
        }
        return minNeighbour;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lineFirstSplitted = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(lineFirstSplitted[0]);
        int countLines = Integer.parseInt(lineFirstSplitted[1]);

        nodes = new NeighboursLine[countNodes];
        weights = new int[countNodes][countNodes];
        for (int[] row : weights) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < countLines; i++) {
            String[] lineNumsStr = reader.readLine().split(" ");
            int nodeFrom = Integer.parseInt(lineNumsStr[0]) - 1;
            int nodeTo = Integer.parseInt(lineNumsStr[1]) - 1;
            int distance = Integer.parseInt(lineNumsStr[2]);

            for (int j = 0; j < 2; j++) {
                int posFrom = j == 0 ? nodeFrom : nodeTo;
                int posTo = j == 1 ? nodeFrom : nodeTo;
                NeighboursLine line;
                if (nodes[posFrom] != null) {
                    line = nodes[posFrom];
                } else {
                    line = new NeighboursLine();
                    nodes[posFrom] = line;
                }
                line.add(posTo);
                if (distance < weights[posFrom][posTo]) {
                    weights[posFrom][posTo] = distance;
                }
            }
        }

        for (NeighboursLine line : nodes) {
            if (line != null) {
                Collections.sort(line);
            }
        }
    }

    private static void printArray(int[][] array) {
        int side_size = array.length;
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < side_size; i++) {
            for (int j = 0; j < side_size; j++) {
                if (i == j) {
                    output_buffer.append(0);
                } else if (array[i][j] == Integer.MAX_VALUE) {
                    output_buffer.append(-1);
                } else {
                    output_buffer.append(array[i][j]);
                }
                output_buffer.append(' ');
            }
            output_buffer.append("\n");
        }
        System.out.println(output_buffer.toString().trim());
    }


    private static class NeighboursLine extends ArrayList<Integer> {
    }
}
