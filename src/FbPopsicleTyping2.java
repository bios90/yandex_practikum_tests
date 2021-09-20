import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FbPopsicleTyping2 {

    private static String[] words;
    public static final String PATH = "/Users/bios90/IdeaProjects/yandex_practikum_tests/src/input.txt";

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int handsChangeCount = 0;
            List<Hand> hands = wordToCharactersGroup(word);
            Hand previous = getStartingHand(hands);
            if (previous == null) {
                System.out.println("Case #" + (i + 1) + ": 0");
            } else {
                for (Hand h : hands) {
                    if (h != null && h != previous) {
                        previous = h;
                        handsChangeCount++;
                    }
                }
                System.out.println("Case #" + (i + 1) + ": " + handsChangeCount);
            }

        }
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

    private static List<Hand> wordToCharactersGroup(String word) {
        List<Hand> groups = new ArrayList<>();
        Hand currentHand = getCharactersHand(word.charAt(0));
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            Hand hand = getCharactersHand(c);

            if (hand != currentHand) {
                groups.add(hand);
            }
            currentHand = hand;
        }
        groups.add(currentHand);

        return groups;
    }

    private static Hand getStartingHand(List<Hand> hands) {
        for (Hand h : hands) {
            if (h != null) {
                return h;
            }
        }
        return null;
    }

    private static Hand getCharactersHand(char c) {
        if (c == 'X') {
            return Hand.LEFT;
        } else if (c == 'O') {
            return Hand.RIGHT;
        } else {
            return null;
        }
    }


    private enum Hand {

        LEFT, RIGHT
    }
}
