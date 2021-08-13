import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Competition {
    private static int[] nums;
    private static int full_sum;
    private static Map<Integer, Integer> map = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        initNums();
        if (nums == null || nums.length == 1) {
            System.out.println(0);
            return;
        }

        if (full_sum == 0) {
            System.out.println(nums.length);
        } else {
            map.put(0, -1);
            int max = checkNew();
            System.out.println(max);
        }
    }

    // 1 1 -1 -1 -1 -1 1 1 1 1 -1 -1 -1 -1 1 -1 -1

    private static Integer checkNew() {
        int sum = 0;
        int max_diff = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                int j = map.get(sum);
                int diff = (i - j);

                if (diff > max_diff) {
                    max_diff = diff;
                }
            } else if (sum == 0) {
                if (i + 1 > max_diff) {
                    max_diff = i + 1;
                }
            }
            else {
                map.put(sum, i);
            }
        }

        return max_diff;
    }

    private static void initNums() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        if (count == 0) {
            return;
        }
        nums = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (num == 0) {
                num = -1;
            }
            full_sum += num;
            nums[i] = num;
        }
    }
}
