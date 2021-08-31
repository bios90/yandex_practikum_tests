import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class GrafVisitingSmej {

    private static NeighboursLine[] nodes;
    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;
    private static int start;

    public static void main(String[] args) throws IOException {
        initFields();
        int[] colors = new int[nodes.length];

        StringBuilder builder = new StringBuilder();

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int num = stack.pop();
            if (colors[num] == COLOR_WHITE) {
                builder.append(num + 1).append(" ");
                colors[num] = COLOR_GRAY;

                NeighboursLine neighbours = nodes[num];
                if(neighbours == null){
                    continue;
                }
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
            }
        }

        System.out.println(builder.toString());
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

        start = Integer.parseInt(reader.readLine()) - 1;
    }

    private static void printAll() {
        StringBuilder builder = new StringBuilder();
        StringBuilder subBuilder = new StringBuilder();

        for (int i = 0; i < nodes.length; i++) {
            subBuilder.setLength(0);
            subBuilder.append("For node ").append(i + 1).append(" neighbours are : ");
            if (nodes[i] == null) {
                subBuilder.append(" NO NEIGHBOURS");
            } else {
                for (Integer neighbour : nodes[i]) {
                    subBuilder.append(neighbour + 1).append(" ");
                }
            }

            builder.append(subBuilder).append("\n");
        }

        System.out.println(builder.toString().trim());
    }

    private static class NeighboursLine extends ArrayList<Integer> {
        boolean isSorted = false;
    }
}
