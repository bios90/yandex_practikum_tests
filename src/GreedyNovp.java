public class GreedyNovp {

    private static int[][] novpCounts;
    private static int[] nums1 = new int[]{1, 3, 1, 2, 8, 9, 1};
    private static int[] nums2 = new int[]{3, 8, 9, 1, 3, 1, 2, 1};

    public static void main(String[] args) {
        novpCounts = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= nums2.length; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    int max = 0;
                    for (int i_prev = 1; i_prev < i; i_prev++) {
                        if (nums1[i_prev] >= num1) {
                            continue;
                        }
                        for (int j_prev = 1; j_prev < j; j_prev++) {
                            max = Math.max(max, novpCounts[i_prev][j_prev]);
                        }
                    }
                    novpCounts[i][j] = max + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                answer = Math.max(answer, novpCounts[i][j]);
            }
        }

        System.out.println(answer);
    }
}
