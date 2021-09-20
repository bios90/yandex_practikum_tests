import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GrafMaxDepth {

    private static int[] colors;
    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 1;
    private static int startPoint;
    private static int maxDepth = 0;

    private static NeighboursLine[] nodes;

    public static void main(String[] args) throws IOException {
        initFields();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint);
        colors[startPoint] = COLOR_GRAY;

        while (!queue.isEmpty()) {
            int currentSize = queue.size();

            maxDepth++;

            while (currentSize > 0) {
                int num = queue.poll();
                NeighboursLine neighbours = nodes[num];
                if (neighbours != null) {
                    for (int neighbour : neighbours) {
                        if (colors[neighbour] == COLOR_WHITE) {
                            colors[neighbour] = COLOR_GRAY;
                            queue.add(neighbour);
                        }
                    }
                }
                currentSize--;
            }
        }
        System.out.println(maxDepth - 1);
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
            }
        }
        startPoint = Integer.parseInt(reader.readLine()) - 1;
        colors = new int[countNodes];

        for (NeighboursLine line : nodes) {
            if (line != null) {
                Collections.sort(line);
            }
        }
    }

    private static class NeighboursLine extends ArrayList<Integer> {
    }
}
