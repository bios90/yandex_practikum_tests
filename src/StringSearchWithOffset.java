import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringSearchWithOffset {
    private static int[] nums;
    private static int[] pattern;

    public static void main(String[] args) throws IOException {
        initFields();
        List<Integer> foundPoses = new ArrayList<>();
        int pos;
        int start = 0;
        while ((pos = firstIndex(start)) != -1) {
            foundPoses.add(pos+1);
            start = pos + 1;
        }

        printList(foundPoses);
    }

    private static int firstIndex(int start) {
        for (int i = start; i <= nums.length - pattern.length; i++) {
            boolean match = true;
            int diff = nums[i] - pattern[0];
            for (int offset = 1; offset < pattern.length; offset++) {
                int numPattern = pattern[offset];
                int numInNums = nums[i + offset];
                if (diff == numInNums - numPattern) {
                    continue;
                } else {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    public static void printList(List<Integer> nums) {
        StringBuilder out = new StringBuilder();
        for (int num : nums) {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countNums = Integer.parseInt(reader.readLine());
        nums = new int[countNums];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < countNums; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int countPattern = Integer.parseInt(reader.readLine());
        pattern = new int[countPattern];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < countPattern; i++) {
            pattern[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
