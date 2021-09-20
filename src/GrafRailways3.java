import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrafRailways3 {
    private static int COLOR_R = 1;
    private static int COLOR_B = 2;

    private static int[][] railways;

    public static void main(String[] args) throws IOException {
        initFields();
        printArray(railways);
        int length = railways.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int num1 = railways[i][j];
                int num2 = railways[j][i];
                if (num1 == 0 || num2 == 0) {
                    continue;
                }

                if ((num1 == COLOR_R && num2 == COLOR_B) || (num1 == COLOR_B && num2 == COLOR_R)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        railways = new int[count][count];
        for (int i = 0; i < count - 1; i++) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                int connectionIndex = i + j + 1;
                if (c == 'R') {
                    railways[i][connectionIndex] = 1;
                } else {
                    railways[connectionIndex][i] = 1;
                }
            }
        }
    }

    private static void printArray(int[][] array) {
        int side_size = array.length;
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < side_size; i++) {
            for (int j = 0; j < side_size; j++) {
                output_buffer.append(array[i][j]);
                output_buffer.append(' ');
            }
            output_buffer.append("\n");
        }
        System.out.println(output_buffer.toString().trim());
    }
}
