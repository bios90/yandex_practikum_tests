import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class GrafTimesExit {

    private static NeighboursLine[] nodes;
    private static int time = -1;
    private static int[] entries;
    private static int[] leaves;
    private static int[] colors;

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    public static void main(String[] args) throws IOException {
        initFields();

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int num = stack.pop();

            if (colors[num] == COLOR_WHITE) {
                time++;
                entries[num] = time;
                colors[num] = COLOR_GRAY;

                NeighboursLine neighbours = nodes[num];
                if (neighbours == null) {
                    colors[num] = COLOR_BLACK;
                    time++;
                    leaves[num] = time;
                    continue;
                }

                stack.push(num);
                if (!neighbours.isSorted) {
                    Collections.sort(neighbours);
                    neighbours.isSorted = true;
                }

                for (int i = neighbours.size() - 1; i >= 0; i--) {
                    int neighbour = neighbours.get(i);
                    if (colors[neighbour] == COLOR_WHITE) {
                        stack.push(neighbour);
                    }
                }

            } else if (colors[num] == COLOR_GRAY) {
                colors[num] = COLOR_BLACK;
                time++;
                leaves[num] = time;
            }
        }

        printResults();
    }

    private static void printResults() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = nodes.length;
        for (int i = 0; i < count; i++) {
            writer.write(entries[i] + " " + leaves[i]);
            writer.write("\n");
        }
        writer.flush();
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lineFirstSplitted = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(lineFirstSplitted[0]);
        int countLines = Integer.parseInt(lineFirstSplitted[1]);

        nodes = new NeighboursLine[countNodes];
        for (int i = 0; i < countLines; i++) {
            String[] lineNumsStr = reader.readLine().split(" ");
            int nodeFrom = Integer.parseInt(lineNumsStr[0]) - 1;
            int nodeTo = Integer.parseInt(lineNumsStr[1]) - 1;

            NeighboursLine line;
            if (nodes[nodeFrom] != null) {
                line = nodes[nodeFrom];
            } else {
                line = new NeighboursLine();
                nodes[nodeFrom] = line;
            }
            line.add(nodeTo);
        }

        entries = new int[countNodes];
        leaves = new int[countNodes];
        colors = new int[countNodes];
    }

    private static class NeighboursLine extends ArrayList<Integer> {
        boolean isSorted = false;
    }
}
