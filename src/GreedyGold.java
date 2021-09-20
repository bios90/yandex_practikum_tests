import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyGold {

    private static int[] nums1;
    private static int[] nums2;
    private static int[][] field;
    private static int[] results1;
    private static int[] results2;

    public static void main(String[] args) throws IOException {
        initFields();
        buildField();
        int max = field[field.length - 1][field[0].length - 1];

        if (max == 0) {
            System.out.println(0);
            return;
        }

        StringBuilder out = new StringBuilder();
        out.append(max).append("\n");

        results1 = new int[max];
        results2 = new int[max];
        int i = field.length-1;
        int j = field[0].length-1;
        while (i != 0 && j != 0) {
            if (nums1[i-1] == nums2[j-1]) {
                max--;
                results1[max] = i;
                results2[max] = j;
                i--;
                j--;
            } else if (field[i][j] == field[i - 1][j]) {
                i--;
            } else {
                j--;
            }
        }

        for (int num : results1) {
            out.append(num).append(" ");
        }
        out.append("\n");
        for (int num : results2) {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }

    private static void buildField() {
        field = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= nums2.length; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    field[i][j] = field[i - 1][j - 1] + 1;
                } else {
                    int left = field[i][j - 1];
                    int top = field[i - 1][j];
                    field[i][j] = Math.max(left, top);
                }
            }
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count1 = Integer.parseInt(reader.readLine());
        nums1 = new int[count1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < nums1.length; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            nums1[i] = num;
        }

        int count2 = Integer.parseInt(reader.readLine());
        nums2 = new int[count2];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < nums2.length; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            nums2[i] = num;
        }
    }
}
