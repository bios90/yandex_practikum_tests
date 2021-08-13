import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class MnogoGosha2 {
    //    private static int base = 1048576;
    //    private static int mode = 1000009;
    //    private static int base = 3;
    private static int base = 251;
    private static int mode = 256;
    private static String strOriginal;
    private static long hash;
    private static int window;
    private static int countToFount;
    private static Map<Long, List<Integer>> mapOfResults = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initFieldsV2(reader);
        initStartHash();
        int left = 0;
        int right = window - 1;

        //                long powModed = getPowModed();
        long powModed = 169;
        List<Integer> results = new ArrayList<>();
        while (right < strOriginal.length() - 1) {

            String str = strOriginal.substring(left, right + 1);
            System.out.println("Str is " + str);
            System.out.println("Right hash is " + getHashForStr(str));
            System.out.println("Counted hash is " + hash);

            right++;
            System.out.println("Will remove char " + strOriginal.charAt(left));
            long remove = (long) (base * Math.pow(base, window));
            hash = ((hash - remove) * base) + strOriginal.charAt(right);
            hash = Math.floorMod(hash, mode);
            //            long remove = ((strOriginal.charAt(left) % mode) * (powModed % mode)) %
            // mode;
            //            hash = (hash % mode) - (remove % mode);
            //            hash = ((hash % mode) * (base % mode)) % mode;
            //            System.out.println("Will add char "+strOriginal.charAt(right));
            //            hash = (hash % mode) + (strOriginal.charAt(right) % mode);
            //            hash = (hash + mode) % mode;
            left++;

            System.out.println("***********************************");

            ////

            //            if (mapOfResults.containsKey(hash)) {
            //                List<Integer> poses = mapOfResults.get(hash);
            //                poses.add(left);
            //                if (poses.size() == countToFount) {
            //                    results.add(poses.get(0));
            //                }
            //            } else {
            //                List<Integer> poses = new ArrayList<>();
            //                poses.add(left);
            //                mapOfResults.put(hash, poses);
            //            }
            //
            //            System.out.println("POses are " + left + " - " + right);
            //            System.out.println(
            //                    "Chars are " + strOriginal.charAt(left) + " - " +
            // strOriginal.charAt(right));
            //            System.out.println("LEft is " + left + " and hash is " + hash);
            //            int realHash = getHashForPoses(left, right);
            //            System.out.println("Real has " + realHash);
            //            System.out.println("**************************************");
            //
            //            right++;
            //            long remove = ((strOriginal.charAt(left) % mode) * (powModed % mode)) %
            // mode;
            //            hash = (hash % mode) - (remove % mode);
            //            hash = ((hash % mode) * (base % mode)) % mode;
            //            hash = (hash % mode) + (strOriginal.charAt(right) % mode);
            //            hash = (hash + mode) % mode;
            //
            //            left++;
        }

        StringBuilder out = new StringBuilder();
        for (Integer i : results) {
            out.append(i).append(" ");
        }
        System.out.println(out.toString().trim());
    }

    private static void initStartHash() {
        int baseModded = base % mode;
        int sum = ((strOriginal.charAt(0) % mode) * baseModded) % mode;
        for (int i = 1; i < window; i++) {
            int num = strOriginal.charAt(i) % mode;
            sum = (sum + num) % mode;
            if (i != window - 1) {
                sum = (sum * baseModded) % mode;
            }
        }
        hash = sum % mode;
    }

    private static int getHashForPoses(String str, int left, int right) {
        int baseModded = base % mode;
        int sum = ((str.charAt(left) % mode) * baseModded) % mode;
        for (int i = 1; i < right; i++) {
            int num = str.charAt(i) % mode;
            sum = (sum + num) % mode;
            if (i != right - 1) {
                sum = (sum * baseModded) % mode;
            }
        }
        sum %= mode;
        return sum;
    }

    private static int getPowModed() {
        int sum = base;
        for (int i = 1; i < window; i++) {
            sum = ((sum % mode) * (base % mode)) % mode;
        }
        return sum;
    }

    private static int getHashForStr(String s) {
        return getHashForPoses(s, 0, s.length());
    }

    private static void initFieldsV2(BufferedReader reader) throws IOException {
        String[] numStr = reader.readLine().split(" ");
        window = Integer.parseInt(numStr[0]);
        countToFount = Integer.parseInt(numStr[1]);
        strOriginal = reader.readLine();
    }
}
