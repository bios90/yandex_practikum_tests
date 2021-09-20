import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GrafIslands {
    private static NeighboursLine[] nodes;
    private static boolean[] visited;
    private static int[][] distances;

    public static void main(String[] args) throws IOException {
        initFields();

        for (int i = 0; i < nodes.length; i++) {
            int minNeighbour = getMinimumNeighbour(i);
            Arrays.fill(visited, false);
            while (minNeighbour > -1) {
                visited[minNeighbour] = true;
                relaxNeighbours(minNeighbour, i);
                minNeighbour = getMinimumNeighbour(i);
            }
        }

        printArray(distances);
    }

    private static int getMinimumNeighbour(int node) {
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
            int distance = distances[node][neighbour];
            if (distance < min) {
                min = distance;
                minNeighbour = neighbour;
            }
        }
        return minNeighbour;
    }

    private static void relaxNeighbours(int node, int parent) {
        NeighboursLine line = nodes[node];
        if (line == null) {
            return;
        }
        for (int i = 0; i < line.size(); i++) {
            int neighbour = line.get(i);
            if (parent == neighbour) {
                continue;
            }
            int distanceOriginal = distances[parent][neighbour];
            int distanceThroughNode = distances[parent][node] + distances[node][neighbour];
            if (distanceThroughNode < distanceOriginal) {
                distances[parent][neighbour] = distanceThroughNode;
                distances[neighbour][parent] = distanceThroughNode;
            }

        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lineFirstSplitted = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(lineFirstSplitted[0]);
        int countLines = Integer.parseInt(lineFirstSplitted[1]);

        nodes = new NeighboursLine[countNodes];
        visited = new boolean[countNodes];
        distances = new int[countNodes][countNodes];
        for (int[] row : distances) {
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
                if (distance < distances[posFrom][posTo]) {
                    distances[posFrom][posTo] = distance;
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
