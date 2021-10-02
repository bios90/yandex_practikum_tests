import java.util.HashMap;
import java.util.Map;

public class GreedyLisRecursivly {
    private static int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};

    private static int maxRef = 0;
    public static void main(String[] args) {
        int lis = getLis(arr, arr.length);
        System.out.println(lis);
        System.out.println(callTimes);
    }

    private static int callTimes = 0;
    private static Map<Integer,Integer> counts = new HashMap<>();

    private static int getLis(int[] nums, int n) {
        callTimes++;
        if (n == 1) {
            return 1;
        }
        int res;
        int maxEnding = 1;
        for (int i = 1; i < n; i++) {
            if(counts.containsKey(i)){
                res = counts.get(i);
            }else{
                res = getLis(nums, i);
                counts.put(i,res);
            }

            if (nums[i - 1] < nums[n - 1]) {
                res++;
            }
            if (res > maxEnding) {
                maxEnding = res;
            }
        }

        return maxEnding;
    }
}
