import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommonSubArray {
    private static int[] nums1;
    private static int[] nums2;
    private static Map<Integer, List<Integer>> secondNumsPoses;

    public static void main(String[] args) throws IOException {
        initFields();
        int max = 0;
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            if (secondNumsPoses.containsKey(num)) {
                List<Integer> poses = secondNumsPoses.get(num);
                for (Integer k : poses) {
                    int index1 = i;
                    int index2 = k;
                    while (true) {
                        if (index1 == nums1.length || index2 == nums2.length) {
                            break;
                        }
                        if (nums1[index1] != nums2[index2]) {
                            break;
                        }
                        index1++;
                        index2++;
                    }

                    int diff = (index1 - i);
                    if (diff > max) {
                        max = diff;
                    }
                }
            }
        }

        System.out.println(max);
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count1 = Integer.parseInt(reader.readLine());
        nums1 = new int[count1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count1; i++) {
            nums1[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int count2 = Integer.parseInt(reader.readLine());
        nums2 = new int[count2];
        secondNumsPoses = new HashMap<>();
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count2; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            nums2[i] = num;
            if (secondNumsPoses.containsKey(num)) {
                List<Integer> poses = secondNumsPoses.get(num);
                poses.add(i);
            } else {
                List<Integer> poses = new ArrayList<>();
                poses.add(i);
                secondNumsPoses.put(num, poses);
            }
        }
    }
}
