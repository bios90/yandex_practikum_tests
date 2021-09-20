import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GrafRailways2 {
    private static int[][] railways;
    private static List<HashSet<Integer>> bluePaths;
    private static List<HashSet<Integer>> redPaths;
    private static int COLOR_R = 1;
    private static int COLOR_B = 2;

    public static void main(String[] args) throws IOException {
        initFields();
//        printArray(railways);
        if (checkDouble()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
//        int[][] railwaysBlue = buildBlue();
//        int[][] railwaysRed = buildRed();
//        int length = railways.length;
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < length; j++) {
//                if (railwaysBlue[i][j] != railwaysRed[i][j]) {
//                    if (railwaysBlue[i][j] != 0 && railwaysRed[i][j] != 0) {
//                        System.out.println("NO");
//                        return;
//                    }
//                }
//            }
//        }
//        System.out.println("YES");
    }

    private static int[][] buildBlue() {
        int length = railways.length;
        int[][] railwaysBlue = new int[length][length];

        for (int i = length - 2; i >= 0; i--) {
            int[] line = railways[i];
            for (int j = length - 1; j >= 0; j--) {
                if (line[j] == 0) {
                    break;
                }
                boolean isBlue = line[j] == COLOR_B;
                if (isBlue) {
                    railwaysBlue[i][j] = COLOR_B;
                    for (int k = i - 1; k >= 0; k--) {
                        if (railways[k][i] == COLOR_B) {
                            railwaysBlue[k][j] = COLOR_B;
                        }
                    }
                }
            }
        }
        return railwaysBlue;
    }

    private static boolean checkDouble() {
        int length = railways.length;

        for (int i = length - 2; i >= 0; i--) {
            int[] line = railways[i];
            for (int j = length - 1; j >= 0; j--) {
                if (line[j] == 0) {
                    break;
                }
                int color = line[j];
                Set<Integer> currentChildes = color == COLOR_R ? redPaths.get(i) : bluePaths.get(i);
                Set<Integer> childsToParent = color == COLOR_R ? bluePaths.get(j) : redPaths.get(j);
                if(!Collections.disjoint(currentChildes,childsToParent)){
                    return false;
                }
//                if (color == COLOR_R) {
//                    Set<Integer> blueToParent = bluePaths.get(j);
//                    for (Integer child : blueToParent) {
//                        if (railways[child][i] == COLOR_R) {
//                            return false;
//                        }
//                    }
//                } else {
//                    Set<Integer> redToParent = redPaths.get(j);
//                    for (Integer child : redToParent) {
//                        if (railways[child][i] == COLOR_B) {
//                            return false;
//                        }
//                    }
//                }
//                for (int k = 0; k < i; k++) {
//                    int colorToParent = railways[k][j];
//                    int colorToCurrent = railways[k][i];
//                    if (colorToCurrent == color && colorToParent != color) {
//                        return false;
//                    }
//                }
            }
        }
        return true;
    }

    private static int[][] buildRed() {
        int length = railways.length;
        int[][] railwaysRed = new int[length][length];

        for (int i = length - 2; i >= 0; i--) {
            int[] line = railways[i];
            for (int j = length - 1; j >= 0; j--) {
                if (line[j] == 0) {
                    break;
                }
                boolean isRed = line[j] == COLOR_R;
                if (isRed) {
                    railwaysRed[i][j] = COLOR_R;
                    for (int k = i - 1; k >= 0; k--) {
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
        bluePaths = createPathDestinations(count);
        redPaths = createPathDestinations(count);
        for (int i = 0; i < count - 1; i++) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                int connectionIndex = i + j + 1;
                List<HashSet<Integer>> destination;
                if (c == 'B') {
                    railways[i][connectionIndex] = COLOR_B;
                    destination = bluePaths;
                } else {
                    railways[i][connectionIndex] = COLOR_R;
                    destination = redPaths;
                }
                destination.get(connectionIndex).add(i);
            }
        }
    }

    private static ArrayList<HashSet<Integer>> createPathDestinations(int size) {
        ArrayList<HashSet<Integer>> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new HashSet<>());
        }
        return list;
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
