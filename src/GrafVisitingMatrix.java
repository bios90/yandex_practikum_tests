import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class GrafVisitingMatrix {
    private static int start;
    private static int[][] matrix;

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    public static void main(String[] args) throws IOException {
        initFields();
        int[] colors = new int[matrix.length];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int num = stack.pop();
            if (colors[num] == COLOR_BLACK) {
                continue;
            }
            if (colors[num] == COLOR_WHITE) {
                System.out.print((num+1)+" ");
                colors[num] = COLOR_GRAY;
                int[] connections = matrix[num];
                for (int neighbour = connections.length - 1; neighbour >= 0; neighbour--) {
                    if (connections[neighbour] != 0 && colors[neighbour] == COLOR_WHITE) {
                        stack.push(neighbour);
                    }
                }
            } else if (colors[num] == COLOR_GRAY) {
                colors[num] = COLOR_BLACK;
            }
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(line[0]);
        int countLines = Integer.parseInt(line[1]);

        matrix = new int[countNodes][countNodes];
        for (int i = 0; i < countLines; i++) {
            String[] lineNumsStr = reader.readLine().split(" ");
            int nodeFrom = Integer.parseInt(lineNumsStr[0]) - 1;
            int nodeTo = Integer.parseInt(lineNumsStr[1]) - 1;

            matrix[nodeFrom][nodeTo] = 1;
            matrix[nodeTo][nodeFrom] = 1;
        }

        start = Integer.parseInt(reader.readLine()) - 1;
    }
}
