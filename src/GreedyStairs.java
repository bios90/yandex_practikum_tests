import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GreedyStairs {
    private static int[] stairs;
    private static int stepToFind;
    private static int jumpLength;
    private static int module = 1000000007;

    public static void main(String[] args) throws IOException {
        initFields();
        stairs[0] = 1;
        for (int i = 1; i < stairs.length; i++) {
            int sum = 0;
            int j = i - 1;
            int steps = jumpLength;
            while (j >= 0 && steps > 0) {
                sum = sumWithModule(sum, stairs[j]);
                j--;
                steps--;
            }
            stairs[i] = sum;
            if(i == stepToFind){
                System.out.println(sum);
                break;
            }
        }
    }

    private static int sumWithModule(int num1, int num2) {
        return (num1 % module + num2 % module) % module;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = reader.readLine().split(" ");
        stairs = new int[1000];
        stepToFind = Integer.parseInt(strs[0]) - 1;
        jumpLength = Integer.parseInt(strs[1]);
    }
}
