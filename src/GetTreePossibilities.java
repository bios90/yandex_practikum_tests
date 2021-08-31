import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetTreePossibilities {

    private static long[] factorials = new long[40];

    public static void main(String[] args) throws IOException {
        factorials[0] = 0;
        factorials[1] = 1;
        factorials[2] = 2;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        long result = getCatalanNumber(count);
        System.out.println(result);
    }

    private static long getFacotirial(int num) {
        if (factorials[num] != 0) {
            return factorials[num];
        } else {
            long sum = 1;
            for (int i = 1; i <= num; i++) {
                if (factorials[i] != 0) {
                    sum = factorials[i];
                } else {
                    sum *= i;
                }
            }
            factorials[num] = sum;
            return sum;
        }
    }

    private static long getCatalanNumber(int num) {
        int result = 0;
        if (num <= 1) {
            return 1;
        }
        for (int i = 0; i < num; i++) {
            result += getCatalanNumber(i) * getCatalanNumber(num - i - 1);
        }
        return result;
    }
}
