import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClosestStop {
    private static int[][] metroExits;
    private static int[][] busStops;

    public static void main(String[] args) throws IOException {

        int[] t1 = new int[] {2, 5};
        int[] t2 = new int[] {22, 6};
        int hash1 = getCustomHash(t1);
        int hash2 = getCustomHash(t2);
        double diff = countDistance(t1,t2);
        System.out.println("Hash1 :"+hash1+", Hash2 :" +hash2+". And real diff is "+diff);

        //        initFields();
        //        int maxStopNear = -1;
        //        int bestExit = -1;
        //
        //        for (int i = 0; i < metroExits.length; i++) {
        //            int closeExitsCount = 0;
        //            int[] exit = metroExits[i];
        //            ArrayList<Integer[]> matchedStops = getMatchedBusStops(exit);
        //            for (int j = 0; j < matchedStops.size(); j++) {
        //                Integer[] stop = matchedStops.get(j);
        //                double distance = countDistance(exit, stop);
        //                //                System.out.println("" + i + " and " + j + " distance is
        // " +
        //                // distance);
        //                if (distance <= 20) {
        //                    closeExitsCount++;
        //                }
        //            }
        //            if (closeExitsCount > maxStopNear) {
        //                maxStopNear = closeExitsCount;
        //                bestExit = i;
        //            }
        //        }
        //        System.out.println((bestExit + 1));
    }

    private static int mod = 100007;

    private static int getCustomHash(int[] nums) {
        int sum = (nums[0] * nums[0]) + (nums[1] * nums[1]);
        double sqrt = Math.sqrt(sum);
        if ((nums[0] < 0 && nums[1] >= 0) || (nums[1] < 0 && nums[0] >= 0)) {
            sqrt = -sqrt;
        }
        return (int)sqrt % mod;
    }

    private static ArrayList<Integer[]> getMatchedBusStops(int[] exit) {
        ArrayList<Integer[]> matched = new ArrayList<>();
        int xExit = exit[0];
        int yExit = exit[1];
        for (int i = 0; i < busStops.length; i++) {
            int[] stop = busStops[i];
            int xStop = stop[0];
            int yStop = stop[1];
            int diffX = Math.abs(xExit - xStop);
            int diffY = Math.abs(yExit - yStop);

            if (diffX <= 20 && diffY <= 20) {
                matched.add(new Integer[] {xStop, yStop});
            }
        }
        return matched;
    }

    private static double countDistance(int[] exit, int[] stop) {
        double xDiff = Math.pow(exit[0] - stop[0], 2);
        double yDiff = Math.pow(exit[1] - stop[1], 2);
        return Math.sqrt(xDiff + yDiff);
    }

    private static double countDistance(int[] exit, Integer[] stop) {
        double xDiff = Math.pow(exit[0] - stop[0], 2);
        double yDiff = Math.pow(exit[1] - stop[1], 2);
        return Math.sqrt(xDiff + yDiff);
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int exitsCount = Integer.parseInt(reader.readLine());
        metroExits = new int[exitsCount][];
        for (int i = 0; i < exitsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            metroExits[i] = new int[] {Integer.parseInt(numsStr[0]), Integer.parseInt(numsStr[1])};
        }

        int stopsCount = Integer.parseInt(reader.readLine());
        busStops = new int[stopsCount][];
        for (int i = 0; i < stopsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            busStops[i] = new int[] {Integer.parseInt(numsStr[0]), Integer.parseInt(numsStr[1])};
        }
    }
}
