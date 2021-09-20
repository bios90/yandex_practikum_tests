import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FbPopsicleTyping {

    private static String[] words;
    public static final String PATH = "/Users/bios90/IdeaProjects/yandex_practikum_tests/src/input.txt";

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int handsChangeCount = 0;
            List<CharactersGroup> groups = wordToCharactersGroup(word);
            Hand previous = getStartingHand(groups);
            if (previous == null) {
                System.out.println("Case #" + (i + 1) + ": 0");
            } else {
                for (CharactersGroup group : groups) {
                    if (group.hand != null && group.hand != previous) {
                        previous = group.hand;
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
            for (int j = 0; j < size; j++) {

            }
            String word = reader.readLine();
            words[i] = word;
        }
    }

    private static List<CharactersGroup> wordToCharactersGroup(String word) {
        List<CharactersGroup> groups = new ArrayList<>();
        Hand currentHand = getCharactersHand(word.charAt(0));
        int start = 0;
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            Hand hand = getCharactersHand(c);

            if (hand != currentHand) {
                CharactersGroup group = new CharactersGroup(start, i, currentHand);
                groups.add(group);
                start = i;
            }
            currentHand = hand;
        }
        groups.add(new CharactersGroup(start, word.length(), currentHand));

        return groups;
    }

    private static Hand getStartingHand(List<CharactersGroup> groups) {
        for (CharactersGroup group : groups) {
            if (group.hand != null) {
                return group.hand;
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

    private static void printWordAsGroups(List<CharactersGroup> groups) {
        StringBuilder builder = new StringBuilder();
        for (CharactersGroup group : groups) {
            String h = group.hand == null ? "ANY" : group.hand.toString();
            builder.append("[ ").append(group.start).append(" - ").append(h).append(" - ").append(group.end).append(" ]").append(" ");
        }
        System.out.println(builder.toString());
    }

    private static class CharactersGroup {
        int start;
        int end;
        Hand hand;

        public CharactersGroup(int start, int end, Hand hand) {
            this.start = start;
            this.end = end;
            this.hand = hand;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public Hand getHand() {
            return hand;
        }

        public void setHand(Hand hand) {
            this.hand = hand;
        }
    }

    private enum Hand {

        LEFT, RIGHT
    }
}
