// Id успешного решения на контесте 53545054
/*
-- ПРИНЦИП РАБОТЫ --
Для решения задачи используется принцип динамического программирования. В двухмерном массиве размером n * m вычисляются и записываются наилчушие варинты перестановок для всех комбинаций подстрок. На каждом шаге выбирается лучший вариант из 3х возможных операций со строкой.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность алгоритма будет O(n*m), где n и m это размер строк. Такая сложность будет из за того что мы пройдем по всем варинтам комбинаций подстрок по возрастанию.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n*m), так как вычисления колличества изменений хранятся в двухмерном массиве размере n * m.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GreedyLevinstein {

    private static String str1;
    private static String str2;
    private static int[][] changes;

    public static void main(String[] args) throws IOException {
        initFields();

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    changes[i][j] = j;
                } else if (j == 0) {
                    changes[i][j] = i;
                } else {
                    int thisChanges = (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1) + changes[i - 1][j - 1];
                    int top = changes[i - 1][j] + 1;
                    int left = changes[i][j - 1] + 1;
                    changes[i][j] = min(thisChanges, top, left);
                }
            }
        }

        int max = changes[str1.length()][str2.length()];
        System.out.println(max);
    }

    private static int min(int i, int j, int k) {
        return Math.min(Math.min(i, j), k);
    }

    public static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str1 = reader.readLine();
        str2 = reader.readLine();
        changes = new int[str1.length() + 1][str2.length() + 1];
    }
}