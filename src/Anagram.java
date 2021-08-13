import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Anagram {
    private static Map<String, List<Integer>> wordsData;

    public static void main(String[] args) throws IOException {
        initFields();
        ArrayList<String> results = new ArrayList<>();
        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : wordsData.entrySet()) {
            List<Integer> wordIds = entry.getValue();
            for (int i : wordIds) {
                out.append(i).append(" ");
            }
            results.add(out.toString().trim());
            out.setLength(0);
        }

        Collections.sort(
                results,
                new Comparator<String>() {
                    @Override
                    public int compare(String s, String t1) {
                        Integer num1 = Integer.parseInt(s.split(" ")[0]);
                        Integer num2 = Integer.parseInt(t1.split(" ")[0]);
                        return num1 - num2;
                    }
                });
        for (String result : results) {
            out.append(result).append("\n");
        }
        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        wordsData = new HashMap<>();
        int count = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            char[] wordAsChars = tokenizer.nextToken().toCharArray();
            Arrays.sort(wordAsChars);
            String wordSorted = new String(wordAsChars);
            if (wordsData.containsKey(wordSorted)) {
                wordsData.get(wordSorted).add(i);
            } else {
                List<Integer> wordPoses = new ArrayList<>();
                wordPoses.add(i);
                wordsData.put(wordSorted, wordPoses);
            }
        }
    }

    private static boolean isStringInteger(String str) {
        return str.matches("-?\\d+");
    }
}
