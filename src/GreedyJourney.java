import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GreedyJourney {
    private static int[] nums;
    private static int[][] ratings;

    public static void main(String[] args) throws IOException {
        initFields();
        int maxSum = Integer.MIN_VALUE;
        int bestLine = -1;
        int[] bestChildLine = new int[nums.length];
        int bestChildIndexMax = -1;
        for (int i = 0; i < nums.length; i++) {
            int[] childLine = new int[nums.length];
            Arrays.fill(childLine,-1);
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    int max = nums[i];
                    int maxIndex = i;
                    for (int k = j-1 ; k >= i; k--) {
                        if(nums[j] > nums[k] && nums[k] > nums[maxIndex]){
                            maxIndex = k;
                        }
                    }

                    ratings[i][j] = ratings[i][maxIndex] + nums[j];
                    childLine[j] = maxIndex;
                    if(ratings[i][j] > maxSum){
                        maxSum = ratings[i][j];
                        bestLine = i;
                        bestChildLine = childLine;
                        bestChildIndexMax = j;
                    }
                }
            }
        }

//        System.out.println("Best line is "+bestLine + " and best sum is "+maxSum);
//        printArray(ratings);
        int size = 0;
        List<Integer> path = new ArrayList<>();
        while (bestChildIndexMax >= 0){
            size++;
            path.add(bestChildIndexMax);
            bestChildIndexMax= bestChildLine[bestChildIndexMax];
        }
        System.out.println(size);
        StringBuilder out = new StringBuilder();
        for(int num : path){
            out.append(num+1).append(" ");
        }
        System.out.println(out.reverse().toString().trim());
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

        for(int i = 0; i < nums.length; i++){
            ratings[i][i] = nums[i];
        }
    }
}
