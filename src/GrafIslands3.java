import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrafIslands3 {
    private static NeighboursLine[] nodes;
    private static int[][] weights;

    public static void main(String[] args) throws IOException {
        initFields();
        dijkstra(3);
//        for (int i = 0; i < nodes.length; i++) {
//            dijkstra(i);
//        }
        printArray(weights);
    }

    private static void dijkstra(int start) {
        int[] distances = weights[start];
        boolean[] visited = new boolean[distances.length];
        distances[start] = 0;
        visited[start] = true;
        int minNeighbour = getMinNotVisited(visited, start);
        while (minNeighbour != -1) {
            visited[minNeighbour] = true;
            NeighboursLine subNeighbours = nodes[minNeighbour];
            if (subNeighbours != null) {
                for (int subNeighbour : subNeighbours) {
                    relax(minNeighbour, subNeighbour, distances);
                }
            }
            minNeighbour = getMinNotVisited(visited, start);
        }
        weights[start] = distances;
    }

    private static void relax(int u, int v, int[] distances) {
        if (weights[u][v] == Integer.MAX_VALUE) {
            return;
        }
        int distanceThroughNode = distances[u] + weights[u][v];
        if (distanceThroughNode < distances[v]) {
            distances[v] = distanceThroughNode;
        }
    }

    private static int getMinNotVisited(boolean[] visited, int node) {
        int minDistance = Integer.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < nodes.length; i++) {
            if (visited[i] == false && weights[node][i] < minDistance) {
                minDistance = weights[node][i];
                minNode = i;
            }
        }
        return minNode;
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
    }

    private static class NeighboursLine extends ArrayList<Integer> {
    }
}
