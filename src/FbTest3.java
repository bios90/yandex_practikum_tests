import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FbTest3 {

    private static String[] words;
    public static final String PATH = "/Users/bios90/IdeaProjects/yandex_practikum_tests/src/input2.txt";
    private static int MODULE = 1000000007;
    private static Map<String, Integer> wordsCount = new HashMap<>();

//    public static void main(String[] args) {
//        String str = "OOXF";
//        int changeCOunt = getHandsChangeCountFromString(str);
//        System.out.println(changeCOunt);
//    }

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
        while (window < word.length()) {
            for (int i = 0; i + window < word.length() + 1; i++) {
                String subWord = word.substring(i, i + window);
                int subCount;
                if (wordsCount.containsKey(subWord)) {
                    System.out.println("For subword " + subWord + " changes count are " + wordsCount.get(subWord));
                    subCount = wordsCount.get(subWord);
                } else {
                    subCount = getHandsChangeCountFromString(subWord) % MODULE;
                    wordsCount.put(subWord, subCount);
                    System.out.println("For subword " + subWord + " changes count are " + subCount);

                }
                count += subCount;
                count %= MODULE;
            }
            window++;
        }
        return count;
    }

    private static String[] wordToSubWords(String word) {
        int count = (word.length() * (word.length() + 1)) / 2;
        String[] subwords = new String[count];
        int index = 0;
        int window = 1;
        while (window < word.length()) {
            for (int i = 0; i + window < word.length() + 1; i++) {
                subwords[index++] = word.substring(i, i + window);
            }
            window++;
        }
        subwords[index++] = word;
        return subwords;
    }

//    public static void main(String[] args) throws IOException {
//        initFields();
//        for (int i = 0; i < words.length; i++) {
//            String word = words[i];
//            int handsChangeCount = 0;
//            List<FbPopsicleTyping2.Hand> hands = wordToCharactersGroup(word);
//            FbPopsicleTyping2.Hand previous = getStartingHand(hands);
//            if (previous == null) {
//                System.out.println("Case #" + (i + 1) + ": 0");
//            } else {
//                for (FbPopsicleTyping2.Hand h : hands) {
//                    if (h != null && h != previous) {
//                        previous = h;
//                        handsChangeCount++;
//                    }
//                }
//                System.out.println("Case #" + (i + 1) + ": " + handsChangeCount);
//            }
//
//        }
//    }

    private static int getHandsChangeCountFromString(String str) {
        int handsChangeCount = 0;
        List<Hand> hands = wordToCharactersGroup(str);
        Hand previous = getStartingHand(hands);
        if (previous == null) {
            return handsChangeCount;
        } else {
            for (Hand h : hands) {
                if (h != null && h != previous) {
                    previous = h;
                    handsChangeCount++;
                }
            }
        }
        return handsChangeCount;
    }



    private static Hand getStartingHandForList(String[] words) {
        for (String str : words) {
            List<Hand> hands = wordToCharactersGroup(str);
            Hand startingHand = getStartingHand(hands);
            if (startingHand != null) {
                return startingHand;
            }
        }
        return null;
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
        groups.add(currentHand);
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

    private static class HandsData {
        int changeCount;
        Hand lastHand;

        public HandsData(int changeCount, Hand lastHand) {
            this.changeCount = changeCount;
            this.lastHand = lastHand;
        }
    }
}
