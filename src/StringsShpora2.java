import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringsShpora2 {
    private static String text;
    private static HashMap<Integer, HashSet<String>> words;
    private static List<Integer> nodePoses = new ArrayList<>();
    private static int maxWordLength = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        initFields();
        nodePoses.add(0);
        for (int i = 0; i <= text.length(); i++) {
            int nodesCount = nodePoses.size();
            String currentText;
            for (int j = 0; j < nodesCount; j++) {
                int pos = nodePoses.get(j);
                currentText = text.substring(pos, i);
                if(currentText.length() > maxWordLength){
                    continue;
                }
                Set<String> wordsOfLength = words.get(currentText.length());
                if (wordsOfLength != null) {
                    if (wordsOfLength.contains(currentText)) {
                        if (i == text.length()) {
                            printYes();
                            return;
                        }
                        nodePoses.add(i);
                    }
                }
            }
        }
        printNo();
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
        int countWords = Integer.parseInt(reader.readLine());
        words = new HashMap<>();
        for (int i = 0; i < countWords; i++) {
            String word = reader.readLine();
            int length = word.length();
            if (words.containsKey(length)) {
                words.get(length).add(word);
            } else {
                HashSet<String> set = new HashSet<>();
                set.add(word);
                words.put(length, set);
            }
            if(word.length() > maxWordLength){
                maxWordLength = word.length();
            }
        }
    }
}
