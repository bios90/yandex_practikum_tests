import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GrafBfs {

    private static int[] colors;
    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static int startPoint;

    private static NeighboursLine[] nodes;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder out = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (colors[num] == COLOR_WHITE) {
                out.append(num+1).append(" ");
                colors[num] = COLOR_GRAY;
                NeighboursLine neighbours = nodes[num];
                if (neighbours != null) {
                    for (Integer neighbour : neighbours) {
                        if (colors[neighbour] == COLOR_WHITE) {
                            queue.add(neighbour);
                        }
                    }
                }
            }
        }
        System.out.println(out.toString().trim());
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
        startPoint = Integer.parseInt(reader.readLine())-1;
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
