/*
Id успешного решения на контесте 53545513
-- ПРИНЦИП РАБОТЫ --
Первым шагом мы читаем входные данные в массив так же считаем их сумму. Если сумма чисел нечетная, то разбить массив на два равных по сумме группы невозможно, в таком случае выводим "False" и заканчиваем выполнение. Если сумма четная, то мы сортируем массив. Далее мы создаем два списка, делаем цикл по отсортированному массиву и каждое число массива кладем в наименьший по сумме элементов список, а так же считаем сумму элементов каждого списка. В итоге получаем массив чисел разбитый на два списка с максимальной разницей в размере 1. Далее мы вычисляем разницу сумм списков, сравниваем листы и пытаемся найти или то число, которое при перемещении из одного массива в другой уравняет их, или пару чисел при которые уравняют списки при взаимном перемещении. Если за текущий цикл уравнять списки не получилось, то из "правого" листа мы берем самое маленькое число и перемещаем его в "левый", и повторяем сравнение.



-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из описания алгоритма следует что мы получаем два списка с максимальной разницей в размере 1. Если списке равны по сумме то ответ найден. Если списке не равны по сумме, но могут быть уравнены, то существуют такое число или такие числа, которые при перемещении из одного списка в другой уравняют их, значит перемещая минимальные числа из большего по сумме массива в меньший, мы будем с каждым шагом приближаться к "выравниванию сумм массива". Так как каждую итерацию мы перемещаем наименьший элемент из большего по сумме списка в меньший, то в определенный момент у нас получится комбинация при которой:

- или два списка равны

- или есть элемент в левом списке который их уравняет при перемещении в правый

- или есть один элемент в левом списке и один элемент в правом списке при взаимном перемещении которых списки уравняются

В каждой итерации происходит проверка этих трех случаев.


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность алгоритма будет O(n^3), так как мы сортируем массив а после делаем сравнения каждого числа с каждым n/2 раз, то есть сложность (n/2 * n/2 * n/2) = n^3. Но так как мы останавливаем сравнение если понимаем что текущие листы уровнять не сможем, то реальное время работы будет близкое к n^2.



-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n) так как мы используем массива размера n и два листа суммарного размера n.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GreedySimilarSums {
    private static int overallSum;
    private static int[] nums;
    private static List<Integer> numsLeft;
    private static List<Integer> numsRight;
    private static int sumLeft;
    private static int sumRight;


    public static void main(String[] args) throws IOException {
        initFields();
        Arrays.sort(nums);
        if (overallSum % 2 == 1) {
            System.out.println("False");
            return;
        }
        for (int num : nums) {
            if (num != 0) {
                if (sumLeft > sumRight) {
                    numsRight.add(num);
                    sumRight += num;
                } else {
                    numsLeft.add(num);
                    sumLeft += num;
                }
            }
        }

        List<Integer> comboMax;
        List<Integer> comboMin;
        int sumMax;
        int sumMin;
        if (sumLeft > sumRight) {
            comboMax = numsLeft;
            comboMin = numsRight;
            sumMax = sumLeft;
            sumMin = sumRight;
        } else {
            comboMax = numsRight;
            comboMin = numsLeft;
            sumMax = sumRight;
            sumMin = sumLeft;
        }

        int maxComboLowIndex = 0;

        while (comboMin.size() < nums.length && maxComboLowIndex < comboMax.size()) {
            if (sumMax == sumMin) {
                printTrue();
                return;
            }

            int diff = (sumMax - sumMin) / 2;

            for (int i = comboMin.size() - 1; i >= 0; i--) {
                int numMin = comboMin.get(i);
                if (numMin == diff) {
                    printTrue();
                    return;
                }
                for (int j = comboMax.size() - 1; j >= maxComboLowIndex; j--) {
                    int numMax = comboMax.get(j);
                    if (numMax <= numMin) {
                        break;
                    } else if (numMax - numMin == diff) {
                        printTrue();
                        return;
                    }
                }
            }
            int numsRightMin = comboMax.get(maxComboLowIndex++);
            for (int i = 0; i < comboMin.size(); i++) {
                if (numsRightMin >= comboMin.get(i)) {
                    comboMin.add(i + 1, numsRightMin);
                    break;
                }
            }
            sumMax -= numsRightMin;
            sumMin += numsRightMin;
        }

        printFalse();
    }

    private static void printTrue() {
        System.out.println("True");
    }

    private static void printFalse() {
        System.out.println("False");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        nums = new int[count];
        numsLeft = new ArrayList<>();
        numsRight = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            overallSum += num;
            nums[i] = num;
        }
    }
}
