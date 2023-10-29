import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringsPrefixReplacer {
    private static String text;
    private static String pattern;
    private static String replacer;

    public static void main(String[] args) throws IOException {
        initFields();
        List<Integer> result = getPositions(text, pattern);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (result.contains(i)) {
                out.append(replacer);
                i += pattern.length() - 1;
            } else {
                out.append(text.charAt(i));
            }
        }
        System.out.println(out.toString().trim());
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

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine();
        pattern = reader.readLine();
        replacer = reader.readLine();
    }
}
