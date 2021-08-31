import java.util.ArrayList;

public class GetAllTreePosibilities {
    private static ArrayList<int[]> results = new ArrayList<>();
    private static int count = 8;

    public static void main(String[] args) {
//        int[] nums = new int[] {2, 3, 1, 4};
//        boolean isValid = checkIfValidNums(nums);
//        System.out.println(isValid);
        int[] nums = new int[0];
        fillNums(nums);
        printResults();
        System.out.println("Results count is " + results.size());
    }

    private static void fillNums(int[] nums) {
        if (nums.length == count) {
            results.add(nums);
//            boolean isValid = checkIfValidNums(nums);
//            System.out.println(arrToString(nums)+" Is valid "+isValid);
//            if (checkIfValidNums(nums)) {
//                results.add(nums);
//            }
            return;
        }

        int[] numsCopy = copyWithAdding(nums);
        int posToFill = nums.length;
        for (int i = 0; i < count; i++) {
            if (!contains(nums, i + 1)) {
                numsCopy[posToFill] = i + 1;
                fillNums(numsCopy);
            }
        }
    }

    private static boolean checkIfValidNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i + 2 < nums.length) {
                int numLeft = nums[i + 1];
                int numRight = nums[i + 2];
                if (numLeft > num && numRight > num) {
//                    if(numLeft > numRight){
//                        return false;
//                    }
                    continue;
                }
                if (numLeft < num && numRight < num) {
                    continue;
                }
                if (numRight < numLeft) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean contains(int[] nums, int num) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                return true;
            }
        }
        return false;
    }

    private static int[] copyWithAdding(int[] nums) {
        int[] numsCopy = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            numsCopy[i] = nums[i];
        }
        return numsCopy;
    }

    private static String arrToString(int[] nums) {
        StringBuilder out = new StringBuilder();
        for (int num : nums) {
            out.append(num);
        }
        return out.toString();
    }

    private static void printResults() {
        StringBuilder out = new StringBuilder();
        for (int[] result : results) {
            out.append(arrToString(result)).append("\n");
        }
        System.out.println();
        System.out.println(out.toString());
    }
    //    public static void main(String[] args) {
    //        String nums = new String();
    //        fillNums(nums);
    //        StringBuilder out = new StringBuilder();
    //        for (String result : results) {
    //            out.append(result).append("\n");
    //        }
    //        System.out.println();
    //        System.out.println(out.toString());
    //    }
    //
    //    private static void fillNums(String str) {
    //        if (str.length() == 4) {
    //            results.add(str);
    //            return;
    //        }
    //
    //        String copy = String.valueOf(str.toCharArray());
    //        for (int i = 0; i < count; i++) {
    //            String newStr = copy + String.valueOf(i + 1);
    //            fillNums(newStr);
    //        }
    //    }
}
