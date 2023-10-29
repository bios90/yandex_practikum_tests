
/*
Id успешного решения на контесте 54821995

-- ПРИНЦИП РАБОТЫ --
Для решения задачи используется динамическое программирование. Мы перебираем все символы строки, и на каждом перебираем все варианты слов. Если оказывается что в текущее положение можно попасть из какого то прошлого положения с добавлением текущего слова то сохраняем это позицию в массиве динамики как true. После всех переборов проверяем, если в массиве динамике последнее значение  true, то значит что мы смогли построить искомую строку используя входной набор слов

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность будет O(n*m), где n - количество символов в строке, m - количество слов.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n) так как хранить мы будем искомую строку длины n, массив boolean размера n+1 и набор слов максимального размера 100.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringsShpora4 {

    private static String text;
    private static Set<String> words;
    private static boolean[] poses;

    public static void main(String[] args) throws IOException {
        initFields();
        poses[0] = true;
        String strToCheck;
        for (int i = 1; i <= text.length(); i++) {
            for (String word : words) {
                if (word.length() <= i) {
                    int start = i - word.length();
                    if (!poses[start]) {
                        continue;
                    }
                    strToCheck = text.substring(start, i);
                    if (strToCheck.equals(word)) {
                        poses[i] = true;
                        break;
                    }
                }
            }
        }

        if (poses[poses.length - 1]) {
            printYes();
        } else {
            printNo();
        }
    }

    private static void printNo() {
        System.out.println("NO");
    }

    private static void printYes() {
        System.out.println("YES");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine();
        poses = new boolean[text.length() + 1];
        int countWords = Integer.parseInt(reader.readLine());
        words = new HashSet<>();
        for (int i = 0; i < countWords; i++) {
            String word = reader.readLine();
            words.add(word);
        }
    }
}
