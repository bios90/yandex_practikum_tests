import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class GreedyStock {
    private static int[] days;

    public static void main(String[] args) {
        for (int i = 0; i < 75; i++) {
            System.out.println("buildingSeriesNames[" + i + "]");
        }
    }

//    public static void main(String[] args) throws IOException {
//        initFields();
//
//        int start = 0;
//        for (int i = 0; i < days.length - 1; i++) {
//            if (days[i] < days[i + 1]) {
//                start = i;
//                break;
//            }
//        }
//        int sum = 0;
//        int buyDay = start;
//        for (int i = start; i < days.length - 1; i++) {
//            int current = days[i];
//            int next = days[i + 1];
//            if (next < current) {
//                sum += current - days[buyDay];
//                buyDay = i+1;
//            }
//        }
//        if (buyDay < days.length) {
//            int maxDay = days[days.length - 1];
//            for (int i = days.length - 1; i > buyDay; i--) {
//                if (days[i] > maxDay) {
//                    maxDay = days[i];
//                }
//            }
//            sum += maxDay - days[buyDay];
//        }
//        System.out.println(sum);
//    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        days = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < days.length; i++) {
            days[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
