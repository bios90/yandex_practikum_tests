import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class StringsShpora {
    private static String text;
    private static String[] words;
    private static boolean[] usedWords;

    public static void main(String[] args) throws IOException {
        initFields();
        sortWords();
        for (String word : words) {
            int position = getFirstPosition(word);
            if (position < 0) {
                printNo();
                return;
            }
            for (int i = position; i < position + word.length(); i++) {
                usedWords[i] = true;
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

    private static void sortWords() {
        Arrays.sort(words, Comparator.comparingInt(String::length));
    }

    private static int getFirstPosition(String pattern) {
        String str = pattern + "#" + text;
        int[] prefixOverlaps = new int[str.length()];
        int pPrev = 0;
        for (int i = 1; i < str.length(); i++) {
            int k = pPrev;
            int posIInWord = i - (pattern.length() + 1);
            while (k > 0 && (str.charAt(i) != str.charAt(k) || usedWords[posIInWord])) {
                k = prefixOverlaps[k - 1];
            }
            boolean isUsed = posIInWord >= 0 && usedWords[posIInWord];
            if (str.charAt(i) == str.charAt(k) && !isUsed) {
                k++;
            }
            if (i < pattern.length()) {
                prefixOverlaps[i] = k;
            }
            pPrev = k;

            if (k == pattern.length()) {
                return i - (2 * pattern.length());
            }
        }
        return -1;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine();
        int countWords = Integer.parseInt(reader.readLine());
        usedWords = new boolean[text.length()];
        words = new String[countWords];
        for (int i = 0; i < countWords; i++) {
            words[i] = reader.readLine();
        }
    }
}
