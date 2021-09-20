/*
Id успешного решения на контесте 52921101
-- ПРИНЦИП РАБОТЫ —
Принцип работы - алгоритм Прима, только вместо поиска минимального остовного дерева происходит поиск максимального. На кажом шаге мы поочередно перебираем соседей, выбирая большего по длине пути. У большего соседа проверяем его соседей. Если расстояние через элемент больше текущего варианта в дерево,то меняем значение на большее.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность будет O(|V|*|E|). Мы переберем все вершины и для каждой вершины переберем всех ее соседей.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ —
Пространственная сложность будет V^2 так как для хранения графа используется матрица.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GrafMaxTree {

    private static int[][] weights;

    public static void main(String[] args) throws IOException {
        initFields();
        int[] maxDistances = new int[weights.length];
        boolean[] includedNodes = new boolean[weights.length];
        Arrays.fill(maxDistances, Integer.MIN_VALUE);
        maxDistances[0] = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            int maxNode = findMaxNode(includedNodes, maxDistances);
            if (maxNode == -1) {
                printOops();
                return;
            }
            includedNodes[maxNode] = true;

            for (int j = 0; j < weights.length; j++) {
                if (weights[maxNode][j] != 0 && !includedNodes[j]) {
                    if (weights[maxNode][j] > maxDistances[j]) {
                        maxDistances[j] = weights[maxNode][j];
                    }
                }
            }
        }

        int sum = 0;
        for (int num : maxDistances) {
            if (num == Integer.MIN_VALUE) {
                printOops();
                return;
            }
            sum += num;
        }
        System.out.println(sum);
    }

    private static void printOops() {
        System.out.println("Oops! I did it again");
    }

    private static int findMaxNode(boolean[] includedNodes, int[] maxDistances) {
        int maxDistance = Integer.MIN_VALUE;
        int maxNode = -1;
        for (int i = 0; i < weights.length; i++) {
            if (!includedNodes[i] && maxDistances[i] > maxDistance) {
                maxDistance = maxDistances[i];
                maxNode = i;
            }
        }
        return maxNode;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lineFirstSplitted = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(lineFirstSplitted[0]);
        int countLines = Integer.parseInt(lineFirstSplitted[1]);

        weights = new int[countNodes][countNodes];
        for (int i = 0; i < countLines; i++) {
            String[] numbers = reader.readLine().split(" ");
            int from = Integer.parseInt(numbers[0]) - 1;
            int to = Integer.parseInt(numbers[1]) - 1;
            int weight = Integer.parseInt(numbers[2]);
            if (weight > weights[from][to]) {
                weights[from][to] = weight;
                weights[to][from] = weight;
            }
        }
    }
}
