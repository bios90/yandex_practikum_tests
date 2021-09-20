/*
Id успешного решения на контесте 52919828
-- ПРИНЦИП РАБОТЫ —
Суть алгоритма в нахождении цикла в дереве. Что бы цикл указал на пересечение определенных дорог, при чтении вводных данных ребра одного пути разворачиаюся в обратную сторону. Далее происходит обход DFS с остановкой, если найдем цикл.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность будет O(|V|+|E|) - сложность перебора DFS.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ —
Пространственная сложность будет V^2 так как для хранения графа используется матрица.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class GrafRailways4 {

    private static int[] colors;
    private static int[][] railways;
    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    public static void main(String[] args) throws IOException {
        initFields();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == COLOR_WHITE) {
                stack.push(i);
                while (!stack.isEmpty()) {
                    int num = stack.pop();
                    if (colors[num] == COLOR_WHITE) {
                        colors[num] = COLOR_GRAY;
                        stack.push(num);
                        int[] neighbours = railways[num];
                        for (int j = neighbours.length - 1; j >= 0; j--) {
                            boolean hasConnection = neighbours[j] != 0;
                            if (hasConnection) {
                                if (colors[j] == COLOR_GRAY) {
                                    printNo();
                                    return;
                                } else if (colors[j] == COLOR_WHITE) {
                                    stack.push(j);
                                }
                            }
                        }
                    } else if (colors[num] == COLOR_GRAY) {
                        colors[num] = COLOR_BLACK;
                    }
                }
            }
        }

        printYes();
    }

    private static void printNo() {
        System.out.println("NO");
    }

    private static void printYes() {
        System.out.println("YES");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        railways = new int[count][count];
        colors = new int[count];
        for (int i = 0; i < count - 1; i++) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                int connectionIndex = i + j + 1;
                if (c == 'B') {
                    railways[i][connectionIndex] = 1;
                } else {
                    railways[connectionIndex][i] = 1;
                }
            }
        }
    }
}
