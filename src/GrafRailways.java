import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrafRailways {

    private static int[][] railways;
    private static int COLOR_R = 1;
    private static int COLOR_B = 2;

    public static void main(String[] args) throws IOException {
        initFields();
        int[][] blue = buildBlue();
        int[][] red = buildRed();
        printArray(red);
        System.out.println("*************************");
        printArray(blue);

    }


    private static int[][] buildBlue() {
        int length = railways.length;
        int[][] railwaysBlack = new int[length][length];
        for (int i = 1; i < length; i++) {
            int[] line = railways[i];
            for (int j = 0; j < line.length; j++) {
                if (line[j] == 0) {
                    break;
                }
                boolean isBlue = line[j] == COLOR_B;
                if (isBlue) {
                    railwaysBlack[i][j] = COLOR_B;
                    for (int k = i + 1; k < length; k++) {
                        if (railways[k][i] == COLOR_B) {
                            railwaysBlack[k][j] = COLOR_B;
                        }
                    }
                }

            }
        }
        return railwaysBlack;
    }

    private static int[][] buildRed() {
        int length = railways.length;
        int[][] railwaysRed = new int[length][length];
        for (int i = 1; i < length; i++) {
            int[] line = railways[i];
            for (int j = 0; j < line.length; j++) {
                if (line[j] == 0) {
                    break;
                }
                boolean isRed = line[j] == COLOR_R;

                if (isRed) {
                    railwaysRed[i][j] = COLOR_R;
                    for (int k = i + 1; k < length; k++) {
                        if (railways[k][i] == COLOR_R) {
                            railwaysRed[k][j] = COLOR_R;
                        }
                    }
                }
            }
        }
        return railwaysRed;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        railways = new int[count][count];
        for (int i = count - 1; i > 0; i--) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (i == j) {
                    continue;
                }
                if (c == 'B') {
                    railways[i][j] = COLOR_B;
                } else if (c == 'R') {
                    railways[i][j] = COLOR_R;
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
