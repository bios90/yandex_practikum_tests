import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Comp1 {

    private static int maxBarCapacity;
    private static int maxVisitTime;
    private static Map<Integer, Integer> visitTimes = new HashMap<>();
    private static HashMap<Integer, Integer> exactTimes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        initFields();
    }

    private static void increaseCapacity(int pos) {
        if (visitTimes.containsKey(pos)) {
            int count = visitTimes.get(pos);
            visitTimes.put(pos, ++count);
        } else {
            visitTimes.put(pos, 1);
        }
    }

    private static void increaseExactTimes(int time) {
        if (exactTimes.containsKey(time)) {
            int count = exactTimes.get(time);
            exactTimes.put(time, ++count);
        } else {
            exactTimes.put(time, 1);
        }
    }

    private static void checkGuest(int time) {
        if (exactTimes.getOrDefault(time, -1) >= maxBarCapacity) {
            System.out.println("NO");
            return;
        }
        boolean canVisit = true;
        for (int i = (time - maxVisitTime); i <= time; i++) {
            if (visitTimes.containsKey(i)) {
                int thisTimeCapacity = visitTimes.get(i);
                if (thisTimeCapacity >= maxBarCapacity) {
                    canVisit = false;
                    break;
                }
            }
        }
        if (!canVisit) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            increaseExactTimes(time);
            for (int i = time; i > time - maxVisitTime; i--) {
                increaseCapacity(i);
            }
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums1 = reader.readLine().split(" ");
        int guestsCount = Integer.parseInt(nums1[0]);
        maxBarCapacity = Integer.parseInt(nums1[1]);
        maxVisitTime = Integer.parseInt(nums1[2]);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < guestsCount; i++) {
            int time = Integer.parseInt(tokenizer.nextToken());
            checkGuest(time);
        }
    }

}