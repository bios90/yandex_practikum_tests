import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GreedyFlowersField {
    private static int[][] field;

    public static void main(String[] args) throws IOException {
        initFields();
        for (int y = field.length - 1; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                int max = Math.max(getBottom(y, x), getLeft(y, x));
                field[y][x] = field[y][x] + max;
            }
        }
        System.out.println(field[0][field[0].length - 1]);
    }

    private static int getBottom(int y, int x) {
        if (y == field.length - 1) {
            return 0;
        }
        return field[y + 1][x];
    }

    private static int getLeft(int y, int x) {
        if (x == 0) {
            return 0;
        }
        return field[y][x - 1];
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
        String[] strs = reader.readLine().split(" ");
        int height = Integer.parseInt(strs[0]);
        int width = Integer.parseInt(strs[1]);
        field = new int[height][width];
        for (int i = 0; i < height; i++) {
            char[] str = reader.readLine().toCharArray();
            for (int j = 0; j < width; j++) {
                field[i][j] = Character.getNumericValue(str[j]);
            }
        }
    }
}
