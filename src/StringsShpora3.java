import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringsShpora3 {
    private static String text;
    private static Set<String> words;
    private static Deque<Integer> poses = new ArrayDeque<>();
    private static int maxWordLength = Integer.MIN_VALUE;
    private static String[] failedByLength;

    public static void main(String[] args) throws IOException {
        initFields();
        poses.add(0);
        StringBuilder sbCurrentText = new StringBuilder();
        for (int i = 0; i <= text.length(); i++) {
            boolean hasInWords = words.contains(sbCurrentText.toString());
            if (hasInWords && (failedByLength[i] == null || !failedByLength[i].equals(sbCurrentText.toString()))) {
                poses.push(i);
                failedByLength[i] = sbCurrentText.toString();
                sbCurrentText.setLength(0);
            }
            if (i == text.length()) {
                if (hasInWords) {
                    printYes();
                    return;
                } else {
                    i = poses.pop() - 1;
                    sbCurrentText.setLength(0);
                }
            }
            sbCurrentText.append(text.charAt(i));
            if (sbCurrentText.length() > maxWordLength) {
                i = poses.pop()-1;
                sbCurrentText.setLength(0);
                sbCurrentText.append(text.charAt(i));
            }
            //            if (sbCurrentText.length() > maxWordLength) {
            //                sbCurrentText.toString();
            //                i = poses.pop()-1;
            //                System.out.println("**** Resetting 2 I is " + i + " ****");
            //                sbCurrentText.setLength(0);
            //            } else {
            //                sbCurrentText.append(text.charAt(i));
            //            }
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
        failedByLength = new String[text.length()];
        int countWords = Integer.parseInt(reader.readLine());
        words = new HashSet<>();
        for (int i = 0; i < countWords; i++) {
            String word = reader.readLine();
            words.add(word);
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
        }
    }
}
