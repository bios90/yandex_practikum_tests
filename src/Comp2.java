import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Comp2 {

    private static int maxBarCapacity;
    private static int maxVisitTime;
    private static int[] visitTimes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums1 = reader.readLine().split(" ");
        int guestsCount = Integer.parseInt(nums1[0]);
        maxBarCapacity = Integer.parseInt(nums1[1]);
        maxVisitTime = Integer.parseInt(nums1[2]);
        visitTimes = new int[1000000 + maxVisitTime];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < guestsCount; i++) {
            int time = Integer.parseInt(tokenizer.nextToken());
            checkGuest(time);
        }
    }

    private static void checkGuest(int time) {
        boolean canVisit = true;
        for (int i = time; i > time - maxVisitTime && i >= 0; i--) {
            if (visitTimes[i] >= maxBarCapacity) {
                canVisit = false;
                break;
            }
        }
        if (!canVisit) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (int i = time; i > time - maxVisitTime && i >= 0; i--) {
                visitTimes[i] = ++visitTimes[i];
            }
        }
    }
}