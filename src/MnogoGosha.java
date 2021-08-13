import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MnogoGosha {
    private static int base = 1048576;
    private static int mode = 1000009;
    ;
    private static ArrayList<Integer> strOrigial;
    private static long hash;
    private static int window;
    private static int countToFount;
    private static Map<String, List<Integer>> subPoses;

    public static void main(String[] args) throws IOException {
        initFields();
        int right = window;
        int left = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < window; i++) {
            int num = strOrigial.get(i);
            builder.append((char) num);
        }
        String subStr = builder.toString();
        Set<String> founded = new HashSet<>();
        while (right < strOrigial.size()) {

            if (!founded.contains(subStr)) {
                if (subPoses.containsKey(subStr)) {
                    List<Integer> poses = subPoses.get(subStr);
                    poses.add(left);
                    if (poses.size() == countToFount) {
                        founded.add(subStr);
                        System.out.print(poses.get(0));
                        System.out.print(" ");
                    }
                } else {
                    List<Integer> poses = new ArrayList<>();
                    poses.add(left);
                    subPoses.put(subStr, poses);
                }
            }

            if (right < strOrigial.size()) {
                int num = strOrigial.get(right);
                subStr += (char) num;
                subStr = subStr.substring(1);
            }
            right++;
            left++;
        }
    }

    private static void initFieldsV2(BufferedReader reader) throws IOException {
        String[] numStr = reader.readLine().split(" ");
        window = Integer.parseInt(numStr[0]);
        countToFount = Integer.parseInt(numStr[1]);

        int aModded = base % mode;
        long sum = (long) reader.read() % mode;
        for (int i = 1; i < window; i++) {
            int num = reader.read();
            sum = ((sum % mode) * aModded) % mode;
            sum = (sum % mode) + (num % mode);
        }
        sum = sum % mode;
        hash = sum;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numStr = reader.readLine().split(" ");
        window = Integer.parseInt(numStr[0]);
        countToFount = Integer.parseInt(numStr[1]);
        strOrigial = new ArrayList<>();
        int cInt = reader.read();
        while (cInt > 13) {
            strOrigial.add(cInt);
            cInt = reader.read();
        }
        subPoses = new HashMap<>();
    }
}
