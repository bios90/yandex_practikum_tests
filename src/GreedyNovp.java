public class GreedyNovp {

    private static int[][] novpCounts;
    private static int[] nums1 = new int[]{1, 3};
    private static int[] nums2 = new int[]{3, 8, 9, 1, 3, 1, 2, 1};

//    public static void main(String[] args) {
//        novpCounts = new int[nums1.length + 1][nums2.length + 1];
//        for (int i = 1; i <= nums1.length; i++) {
//            for (int j = 1; j <= nums2.length; j++) {
//                novpCounts[i][j] = novpCounts[i - 1][j];
//                if (nums1[i - 1] == nums2[j - 1]) {
//                    int max = 0;
//                    for (int j_prev = 1; j_prev < j; j_prev++) {
//                        if (nums2[j_prev] < nums2[j-1]) {
//                            max = Math.max(max, novpCounts[i - 1][j_prev]);
//                        }
//                    }
//                    novpCounts[i][j] = max + 1;
//                }
//            }
//        }
//
//        int answer = 0;
//        for (int j = 0; j <= nums2.length; j++) {
//            answer = Math.max(novpCounts[nums1.length][j], answer);
//        }
//
//        System.out.println(answer);
//    }

    public static void main(String[] args) {
        novpCounts = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    int max = 0;
                    for (int i_prev = 1; i_prev < i; i_prev++) {
                        if (nums1[i_prev] >= nums1[i]) {
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

        printArray(novpCounts);
//        System.out.println(answer);
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
