import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringPrefixTest2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        int[] prefixOverlaps = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int k = prefixOverlaps[i - 1];
            while (k > 0 && str.charAt(k) != str.charAt(i)) {
                k = prefixOverlaps[k - 1];
            }
            if (str.charAt(i) == str.charAt(k)) {
                k++;
            }
            prefixOverlaps[i] = k;
        }

        printArray(prefixOverlaps);
    }

    private static void printArray(int[] nums) {
        StringBuilder out = new StringBuilder();
        for (int num : nums) {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}
