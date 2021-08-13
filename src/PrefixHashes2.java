import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixHashes2 {
    private static int base;
    private static long mod;
    private static String str;
    private static Integer[][] pairs;
    private static long[] pows;
    private static long[] prefixHases;

    public static void main(String[] args) throws IOException {
        initFields();

        for (Integer[] pair : pairs) {
            int start = pair[0];
            int end = pair[1];

            int powIndex = end - start + 1;
            long pow = pows[powIndex];
            long partBeforeLastMod =
                    (((prefixHases[end] + mod) - (prefixHases[start - 1] * pow) % mod));
            long result = partBeforeLastMod % mod;
            System.out.println(result);
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        base = Integer.parseInt(reader.readLine());
        mod = Long.parseLong(reader.readLine());
        str = reader.readLine();
        int count = Integer.parseInt(reader.readLine());
        pairs = new Integer[count][2];
        StringTokenizer tokenizer;
        for (int i = 0; i < count; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int n1 = Integer.parseInt(tokenizer.nextToken());
            int n2 = Integer.parseInt(tokenizer.nextToken());
            pairs[i] = new Integer[] {n1, n2};
        }

        pows = new long[str.length() + 1];
        pows[0] = 1;
        for (int i = 1; i <= str.length(); i++) {
            pows[i] = (pows[i - 1] * base) % mod;
        }

        prefixHases = new long[str.length() + 1];
        prefixHases[0] = 0;
        for (int i = 1; i <= str.length(); i++) {
            prefixHases[i] = (prefixHases[i - 1] * base % mod + str.charAt(i - 1)) % mod;
        }
    }
}
