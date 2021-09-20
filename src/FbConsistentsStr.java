import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FbConsistentsStr {

    private static Set<Character> vowels;
    private static String[] requests;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < requests.length; i++) {
            int count = getStringChangeTime(requests[i]);
            out.append("Case #" + (i + 1) + ": " + count).append("\n");
        }
        System.out.println(out.toString().trim());
    }


    private static int getStringChangeTime(String s) {
        if (isStringConsistent(s)) {
            return 0;
        }

        Map<Character, Integer> vowelsRepeating = new HashMap<>();
        Map<Character, Integer> consonantsRepeating = new HashMap<>();

        Character maxRepeatedVowel = null;
        int maxRepeatedVowelCount = 0;

        Character maxRepeatedConsonant = null;
        int maxRepeatedConsonantCount = 0;

        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                int count = increaseAndGet(c, vowelsRepeating);
                if (count > maxRepeatedVowelCount) {
                    maxRepeatedVowelCount = count;
                    maxRepeatedVowel = c;
                }
            } else {
                int count = increaseAndGet(c, consonantsRepeating);

                if (count > maxRepeatedConsonantCount) {
                    maxRepeatedConsonantCount = count;
                    maxRepeatedConsonant = c;
                }
            }
        }

        if (maxRepeatedConsonantCount == 0 || maxRepeatedVowelCount == 0) {
            return s.length();
        }


        int countReplaceWofel = getCountsToReplace(s,maxRepeatedVowel);
        int countReplaceConsonant = getCountsToReplace(s,maxRepeatedConsonant);
        return Math.min(countReplaceWofel,countReplaceConsonant);
//        Character cForReplacing;
//        boolean replaceToVowel;
//        if (maxRepeatedConsonantCount > maxRepeatedVowelCount) {
//            cForReplacing = maxRepeatedVowel;
//            replaceToVowel = true;
//        } else {
//            cForReplacing = maxRepeatedConsonant;
//            replaceToVowel = false;
//        }
//
//        System.out.println("Char for replace is " + cForReplacing);
//
//        int seconds = 0;
//        for (char c : s.toCharArray()) {
//            if (c == cForReplacing) {
//                continue;
//            }
//
//            if (replaceToVowel) {
//
//                if (isVowel(c)) {
//                    seconds += 2;
//                } else {
//                    seconds += 1;
//                }
//
//            } else {
//                if (isVowel(c)) {
//                    seconds += 1;
//                } else {
//                    seconds += 2;
//                }
//            }
//        }
//
//        return seconds;
    }

    private static int getCountsToReplace(String s, Character cForReplacing) {

        int seconds = 0;
        boolean replaceToVowel = isVowel(cForReplacing);

        for (char c : s.toCharArray()) {
            if (c == cForReplacing) {
                continue;
            }

            if (replaceToVowel) {

                if (isVowel(c)) {
                    seconds += 2;
                } else {
                    seconds += 1;
                }

            } else {
                if (isVowel(c)) {
                    seconds += 1;
                } else {
                    seconds += 2;
                }
            }
        }

        return seconds;
    }

    private static int increaseAndGet(char c, Map<Character, Integer> map) {
        if (map.containsKey(c)) {
            int count = map.get(c);
            map.put(c, ++count);
            return count;
        } else {
            map.put(c, 1);
            return 1;
        }
    }

    private static boolean isVowel(char c) {
        return vowels.contains(c);
    }

    private static void initFields() throws IOException {
        vowels = new HashSet<>();
        vowels = new HashSet<>();

        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        requests = new String[count];
        for (int i = 0; i < count; i++) {
            requests[i] = reader.readLine();
        }
    }

    private static boolean isStringConsistent(String s) {
        String pattern = "([a-zA-Z])\\1*";
        return s.matches(pattern);
    }


}
