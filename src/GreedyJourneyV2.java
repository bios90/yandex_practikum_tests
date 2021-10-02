import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class GreedyJourneyV2 {
    private static int[] nums;
    private static int[][] ratings;

    public static void main(String[] args) throws IOException {
        initFields();
        int maxSum = Integer.MIN_VALUE;
        int[] bestChildLine = new int[nums.length];
        int bestChildIndexMax = -1;
        for (int i = 0; i < nums.length; i++) {
            int[] childLine = new int[nums.length];
            Arrays.fill(childLine, -1);
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    int maxIndex = i;
                    for (int k = j - 1; k >= i; k--) {
                        if (nums[j] > nums[k] && nums[k] > nums[maxIndex]) {
                            if (ratings[i][k] > ratings[i][maxIndex]) {
                                maxIndex = k;
                            }
                        }
                    }

                    ratings[i][j] = ratings[i][maxIndex] + 1;
                    childLine[j] = maxIndex;
                }
                if (ratings[i][j] > maxSum) {
                    maxSum = ratings[i][j];
                    bestChildLine = childLine;
                    bestChildIndexMax = j;
                }
            }
        }

//        System.out.println("Best line is "+bestLine + " and best sum is "+maxSum);
//        printArray(ratings);
        int size = 0;
        List<Integer> path = new ArrayList<>();
        while (bestChildIndexMax >= 0) {
            size++;
            path.add(bestChildIndexMax);
            bestChildIndexMax = bestChildLine[bestChildIndexMax];
        }
        System.out.println(size);
        StringBuilder out = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            out.append(path.get(i) + 1).append(" ");
        }
        System.out.println(out.toString().trim());
//        List<Integer>


//        StringBuilder out = new StringBuilder();
//        out.append(bestLineSize).append("\n");
//        for (int i = 0; i < nums.length; i++) {
//            if (ratings[bestLine][i] != 0
//                    && (i == 0) || ratings[bestLine][i] > ratings[bestLine][i - 1]) {
//                out.append(i + 1).append(" ");
//            }
//        }
//        System.out.println(out.toString().trim());

//        int max = -1;
//        int bestLine = -1;
//        int bestLineStart = -1;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (ratings[i][j] > max && ratings[bestLine]) {
//                    max = ratings[i][j];
//                    bestLine = i;
//                }
//            }
//        }
    }

    private static int getSumFromChildLine(int[] line,int bestIndex){
        int sum = 0;
        while (bestIndex >= 0) {
            sum+=nums[bestIndex];
            bestIndex = line[bestIndex];
        }
        return sum;
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
        ratings = new int[count][count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < nums.length; i++) {
            ratings[i][i] = 1;
        }
    }
}
