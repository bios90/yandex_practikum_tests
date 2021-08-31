import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrafMatrix {
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder builder = new StringBuilder();
        for (int[] nums : matrix) {
            for (int i = 0; i < nums.length - 1; i++) {
                builder.append(nums[i]).append(" ");
            }
            builder.append(nums[nums.length - 1]).append("\n");
        }
        System.out.println(builder.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(line[0]);
        int countLines = Integer.parseInt(line[1]);

        matrix = new int[countNodes][countNodes];
        for (int i = 0; i < countLines; i++) {
            String[] lineNumsStr = reader.readLine().split(" ");
            int nodeFrom = Integer.parseInt(lineNumsStr[0]) - 1;
            int nodeTo = Integer.parseInt(lineNumsStr[1]) - 1;

            matrix[nodeFrom][nodeTo] = 1;
        }
    }
}
