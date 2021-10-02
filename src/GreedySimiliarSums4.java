import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedySimiliarSums4 {
    private static int sum;
    private static int[] nums;
    private static int[][] combinations;

    public static void main(String[] args) throws IOException {
        initFields();
        int width = combinations[0].length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < width; j++) {
                int num;
                if (i == 0) {
                    num = nums[j % nums.length];
                } else {
                    int nextPos = (j + i);
                    int jAdder = 0;
                    if (j >= nums.length) {
                        nextPos += (j / nums.length);
                        jAdder += (j / nums.length);
                    }
                    num = combinations[i - 1][j+jAdder] + nums[nextPos % nums.length];
                }
                if (sum - num == num) {
                    System.out.println("Success for num " + num);
                    System.out.println("True");
                    return;
                }
                combinations[i][j] = num;
            }
        }

        System.out.println("Sum is " + sum);
        printArray(combinations);
        System.out.println("False");
    }

    private static void printArray(int[][] array) {
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                output_buffer.append(array[i][j]);
                output_buffer.append(' ');
            }
            output_buffer.append("\n");
        }
        System.out.println(output_buffer.toString().trim());
    }


    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        nums = new int[count];
        int width = (count * count - 1) / 2;
        combinations = new int[count][width];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            sum += num;
            nums[i] = num;
        }
    }
}
