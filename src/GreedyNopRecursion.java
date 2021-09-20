import java.util.ArrayList;
import java.util.List;

public class GreedyNopRecursion {

    private static int[] nums1 = new int[]{1, 3, 2, 3, 5, 7};
    private static int[] nums2 = new int[]{5, 1, 2, 3, 5, 9, 7};

    public static void main(String[] args) {
        int maxNop = getMaxNop(nums1,nums2,nums1.length,nums2.length);
        System.out.println(maxNop);
    }

    private static int getMaxNop(int[] nums1, int[] nums2, int index1, int index2) {
        if (index1 == 0 || index2 == 0) {
            return 0;
        }
        int lastNum1 = nums1[index1 - 1];
        int lastNum2 = nums2[index2 - 1];
        if (lastNum1 == lastNum2) {
            return 1 + getMaxNop(nums1, nums2, index1 - 1, index2 - 1);
        } else {
            int nop1 = getMaxNop(nums1, nums2, index1 - 1, index2);
            int nop2 = getMaxNop(nums1, nums2, index1, index2 - 1);
            return Math.max(nop1, nop2);
        }
    }


    private static List<Integer> nop(int[] nums1, int[] nums2, int index1, int index2, List<Integer> result) {
        if (index1 == 0 || index2 == 0) {
            return result;
        }
        int lastNum1 = nums1[index1 - 1];
        int lastNum2 = nums2[index2 - 1];
        if (lastNum1 == lastNum2) {
            result.add(lastNum1);
            return nop(nums1, nums2, index1 - 1, index2 - 1, result);
        } else {
            List<Integer> ls1 = nop(nums1, nums2, index1 - 1, index2, result);
            List<Integer> ls2 = nop(nums1, nums2, index1, index2 - 1, result);
            if (ls1.size() > ls2.size()) {
                result.addAll(ls1);
            } else {
                result.addAll(ls2);
            }
            return result;
        }
    }

    private static Integer getFromArray(int[] nums, int index) {
        if (index < 0 || index > nums.length) {
            return null;
        }
        return nums[index];
    }
}
