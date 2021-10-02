public class GreedyNovp1 {
    private static int[][] novpCounts;
    private static int[] nums1 = new int[] {1, 3};
    private static int[] nums2 = new int[] {3, 8, 9, 1, 3, 1, 2, 1};

    public static void main(String[] args) {
        int n = nums1.length;
        int m = nums2.length;
        novpCounts = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    int maxLength = 0;
                    for (int i_prev = 1; i_prev < i; i_prev++) {
                        if (nums1[i_prev - 1] >= nums1[i - 1]) {
                            continue;
                        }
                        for (int j_prev = 1; j_prev < j; j_prev++) {
                            maxLength = Math.max(maxLength, novpCounts[i_prev][j_prev]);
                        }
                    }
                    novpCounts[i][j] = maxLength + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                answer = Math.max(answer, novpCounts[i][j]);
            }
        }
        printArray(novpCounts);
        System.out.println("Answer is "+answer);
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
}
