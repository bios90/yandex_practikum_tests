import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FbHands3 {

    private static String[] words;
    public static final String PATH = "/Users/bios90/IdeaProjects/yandex_practikum_tests/src/input3.txt";
    private static final int MODULE = 1000000007;
    private static final Map<String, Integer> wordsCount = new HashMap<>();
    private static final int HAND_LEFT = -1;
    private static final int HAND_RIGHT = 1;
    private static final int HAND_ANY = 0;


    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int count = getWordChangesCountWithSubstring(word);
            System.out.println("Case #" + (i + 1) + ": " + count);
        }
    }

    private static int getWordChangesCountWithSubstring(String word) {
        int count = 0;
        int window = 1;
        while (window <= word.length()) {
            for (int i = 0; i + window <= word.length(); i++) {
                String subWord = word.substring(i, i + window);
                int subCount;
                if (wordsCount.containsKey(subWord)) {
                    subCount = wordsCount.get(subWord);
                } else {
                    subCount = getHandsChangeCountFromString(subWord) % MODULE;
                    wordsCount.put(subWord, subCount);
                }
                count = (count % MODULE + subCount % MODULE) % MODULE;
            }
            window++;
        }
        return count;
    }


    private static int getHandsChangeCountFromString(String str) {
        int handsChangeCount = 0;
        int previousHand = HAND_ANY;
        for (char c : str.toCharArray()) {
            if (getCharactersHand(c) != HAND_ANY) {
                previousHand = getCharactersHand(c);
                break;
            }
        }
        if (previousHand == HAND_ANY) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            int currentHand = getCharactersHand(str.charAt(i));
            if (currentHand != HAND_ANY && currentHand != previousHand) {
                handsChangeCount++;
            }
            if (currentHand != HAND_ANY) {
                previousHand = currentHand;
            }
        }
        return handsChangeCount;
    }


    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        int allCount = Integer.parseInt(reader.readLine());
        words = new String[allCount];
        for (int i = 0; i < allCount; i++) {
            Integer size = Integer.parseInt(reader.readLine());
            String word = reader.readLine();
            words[i] = word;
        }
    }

    private static int getCharactersHand(char c) {
        if (c == 'X') {
            return HAND_LEFT;
        } else if (c == 'O') {
            return HAND_RIGHT;
        } else {
            return HAND_ANY;
        }
    }

}
