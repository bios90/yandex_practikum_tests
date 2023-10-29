import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class StringsPrefixSearch {
    private static String text = "mississippi";
    private static String pattern = "sip";

    public static void main(String[] args) {
        List<Integer> result = getPositions(text, pattern);
        printList(result);
    }

    private static List<Integer> getPositions(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        String str = pattern + "#" + text;
        int[] prefixOverlaps = new int[str.length()];
        int pPrev = 0;
        for (int i = 1; i < str.length(); i++) {
            int k = pPrev;
            while (k > 0 && str.charAt(i) != str.charAt(k)) {
                k = prefixOverlaps[k - 1];
            }
            if (str.charAt(i) == str.charAt(k)) {
                k++;
            }
            if (i < pattern.length()) {
                prefixOverlaps[i] = k;
            }
            pPrev = k;

            if (k == pattern.length()) {
                result.add(i - (2 * pattern.length()));
            }
        }
        return result;
    }

    public static void printList(List<Integer> nums) {
        StringBuilder out = new StringBuilder();
        for (int num : nums) {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }
}
