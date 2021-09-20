import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class GrafTopologicalSort {

    private static NeighboursLine[] nodes;
    private static int[] colors;
    private static int resultIndex;
    private static int[] result;

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    public static void main(String[] args) throws IOException {
        initFields();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        NeighboursLine neighbours;
        for (int i = nodes.length - 1; i >= 0; i--) {
            if (isBlack(i)) {
                continue;
            }

            if (nodes[i] == null || nodes[i].isEmpty()) {
                colors[i] = COLOR_BLACK;
                addResult(i);
                continue;
            }

            stack.push(i);
            while (!stack.isEmpty()) {
                int num = stack.pop();
                if (isWhite(num)) {
                    colors[num] = COLOR_GRAY;

                    neighbours = nodes[num];
                    if (neighbours == null) {
                        colors[num] = COLOR_BLACK;
                        addResult(num);
                        continue;
                    }

                    stack.push(num);
                    if (!neighbours.isSorted) {
                        Collections.sort(neighbours);
                        neighbours.isSorted = true;
                    }

                    for (int j = neighbours.size() - 1; j >= 0; j--) {
                        int neighbour = neighbours.get(j);
                        if (isWhite(neighbour)) {
                            stack.push(neighbour);
                        }
                    }
                } else if (isGray(num)) {
                    colors[num] = COLOR_BLACK;
                    addResult(num);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < result.length; i++) {
            writer.append(String.valueOf(result[i] + 1)).append(' ');
        }
        writer.flush();
    }

    private static boolean isBlack(int num) {
        return colors[num] == COLOR_BLACK;
    }

    private static boolean isGray(int num) {
        return colors[num] == COLOR_GRAY;
    }

    private static boolean isWhite(int num) {
        return colors[num] == COLOR_WHITE;
    }

    private static void addResult(int num) {
        result[resultIndex--] = num;
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

        colors = new int[countNodes];
        result = new int[countNodes];
        resultIndex = countNodes - 1;
    }

    private static class NeighboursLine extends ArrayList<Integer> {
        boolean isSorted = false;
    }
}
