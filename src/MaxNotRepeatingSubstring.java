import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxNotRepeatingSubstring {
    private static int max = 1;
    private static int start;
    private static final HashMap<Integer, Integer> map = new HashMap<>();
    private static Integer previous;
    private static int diff = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int end = 0;
        int cInt = reader.read();
        while (cInt > 13) {
            previous = map.get(cInt);
            if (previous != null) {
                diff = end - start;
                max = Math.max(max, diff);
                start = Math.max(start, previous + 1);
            }
            map.put(cInt, end);
            end++;
            cInt = reader.read();
        }
        max = Math.max(max, end - start);
        System.out.println(max);
    }
}
