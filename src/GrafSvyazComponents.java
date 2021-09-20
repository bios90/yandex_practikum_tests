import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GrafSvyazComponents {

    private static NeighboursLine[] nodes;
    private static int[] colors;
    private static ArrayList<NeighboursLine> neighboursGrouped;

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == COLOR_WHITE) {
                NeighboursLine grouped = new NeighboursLine();
                ArrayDeque<Integer> stack = new ArrayDeque<>();
                stack.push(i);
                while (!stack.isEmpty()) {
                    int num = stack.pop();
                    if (colors[num] == COLOR_WHITE) {
                        colors[num] = COLOR_GRAY;
                        NeighboursLine line = nodes[num];
                        if (line == null) {
                            colors[num] = COLOR_BLACK;
                            grouped.add(num);
                            continue;
                        }
                        stack.push(num);
                        line.pushToStackDescending(stack);
                    } else if (colors[num] == COLOR_GRAY) {
                        colors[num] = COLOR_BLACK;
                        grouped.add(num);
                    }
                }
                Collections.sort(grouped);
                neighboursGrouped.add(grouped);
            }
        }
        printResult();
    }

    private static void printResult() {
        StringBuilder out = new StringBuilder();
        out.append(neighboursGrouped.size()).append("\n");
        for (NeighboursLine group : neighboursGrouped) {
            for (int num : group) {
                out.append(num + 1).append(" ");
            }
            out.append("\n");
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

        for (NeighboursLine line : nodes) {
            if (line != null) {
                Collections.sort(line);
            }
        }
        colors = new int[countNodes];
        neighboursGrouped = new ArrayList<>();
    }

    private static class NeighboursLine extends ArrayList<Integer> {
        public void pushToStackDescending(ArrayDeque<Integer> stack) {
            for (int i = this.size() - 1; i >= 0; i--) {
                int num = this.get(i);
                if (colors[num] == COLOR_WHITE) {
                    stack.push(num);
                }
            }
        }
    }
}

