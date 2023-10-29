/*
Id успешного решения на контесте 54818182

-- ПРИНЦИП РАБОТЫ --
При чтении входящих строк происходит их распаковка. Распаковка происходит рекурсивно, на каждом шаге мы проверяем является символ обычным символом или же это начало запакованной строки. Если это простой символ то добавляем его в результат, если же это запакованная строка то находим ее границы и получаем запакованную подстроку, распаковываем ее и добавляем в результат. После распаковки всех строк остается только сравнить их по длине минимальной строки и понять есть ли у них расхождения.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность будет O(n), где n это общее количество запакованных строк. Так же будет проведено k * m сравнений символов, где k - количество входных строк, m - длина минимальной строки. Сложность этих сравнений так же будет O(n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n) для хранения распакованных строк.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringUnpacking {
    private static int minLength = Integer.MAX_VALUE;
    private static String[] words;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < minLength; i++) {
            char c = words[0].charAt(i);
            boolean matches = true;
            for (int j = 1; j < words.length; j++) {
                if (words[j].charAt(i) != c) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                out.append(c);
            } else {
                break;
            }
        }

        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        words = new String[count];
        for (int i = 0; i < count; i++) {
            String unpacked = unpack(reader.readLine());
            words[i] = unpacked;
            if (unpacked.length() < minLength) {
                minLength = unpacked.length();
            }
        }
    }

    private static String unpack(String str) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                String p = getPackedSubstringFrom(i, str);
                String u = unpack(p);
                int count = Character.getNumericValue(c);
                for (int j = 0; j < count; j++) {
                    out.append(u);
                }
                i += (p.length() + 2);
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    private static String getPackedSubstringFrom(int pos, String p) {
        StringBuilder out = new StringBuilder();
        int countOpened = 1;
        for (int i = pos + 2; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '[') {
                countOpened++;
            } else if (c == ']') {
                countOpened--;
            }
            if (countOpened == 0) {
                return out.toString();
            } else {
                out.append(c);
            }
        }
        throw new RuntimeException("Got wrong formatted string");
    }
}
