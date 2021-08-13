import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PrefixHashes {
    private static int a;
    private static long m;
    private static String str;
    private static Integer[][] pairs;
    private static Map<Integer[], HashMap> map = new HashMap();

    public static void main(String[] args) throws IOException {
        initFields();
        rightSolution();
    }

    private static void rightSolution() {
        for (int i = 0; i < pairs.length; i++) {
            //            int pair =
        }
    }

    private static void firstSolution() {
        for (int i = 0; i < pairs.length; i++) {
            Integer[] pair = pairs[i];

            String trimmed = subString(str, pair[0], pair[1]);
            System.out.println(getHashV2(trimmed, a, m));
        }
    }

    private static String subString(String str, int start, int end) {
        StringBuilder out = new StringBuilder();
        for (int i = start; i <= end; i++) {
            out.append(str.charAt(i));
        }
        return out.toString();
    }

    private static long getHashV2(String str, int a, long m) {
        long aModded = a % m;
        long sum = (long) str.charAt(0) % m;
        for (int i = 1; i < str.length(); i++) {
            sum = ((sum % m) * aModded) % m;
            sum = (sum % m) + (str.charAt(i) % m);
        }
        return sum % m;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(reader.readLine());
        m = Long.parseLong(reader.readLine());
        str = reader.readLine();
        int count = Integer.parseInt(reader.readLine());
        pairs = new Integer[count][2];
        StringTokenizer tokenizer;
        for (int i = 0; i < count; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int n1 = Integer.parseInt(tokenizer.nextToken());
            int n2 = Integer.parseInt(tokenizer.nextToken());
            pairs[i] = new Integer[] {n1 - 1, n2 - 1};
        }
    }
}
