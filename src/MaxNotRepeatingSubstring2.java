import java.io.*;
import java.util.*;

public class MaxNotRepeatingSubstring2 {
    private static int max = 1;
    private static int start;
    private static int[] poses;
    private static Integer previous;
    private static int diff = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        poses = new int[123 % 97];
        Arrays.fill(poses, -1);
        int end = 0;
        int cInt = reader.read();
        while (cInt > 13) {
            int arrPos = cInt % 97;
            previous = poses[arrPos];
            if (previous != -1) {
                diff = (end - start);
                max = Math.max(max, diff);
                start = Math.max(start, previous + 1);
            }
            poses[arrPos] = end;

            end++;
            cInt = reader.read();
        }
        max = Math.max(max, (end - start));
        System.out.println(max);
    }
}
