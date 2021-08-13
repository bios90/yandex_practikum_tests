import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyTrashFinderTest
{
    private static int[] nums = new int[]{27, 67, 3, 4, 17, 98};
    private static ArrayList<Integer> diffs = new ArrayList<>();

    public static void main(String[] args)
    {
//        int[] arr_1 = new int[]{1, 7, 12, 14};
//        int[] arr_2 = new int[]{2, 4, 9};
//
//        int[] result = mergeArrays(arr_1,arr_2);
//        Helper.printArray(result);
        int[] sorted = mergeSort(nums);
        Helper.printArray(sorted);
        Collections.sort(diffs);
        Helper.printList(diffs);
    }

    private static int[] mergeSort(int[] nums)
    {
        if (nums.length == 1)
        {
            return nums;
        }

        int median = nums.length / 2;
        int[] left = getSubArray(nums, 0, median);
        int[] right = getSubArray(nums, median, nums.length);

        left = mergeSort(left);
        right = mergeSort(right);

        int[] result = mergeArrays(left, right);
        return result;
    }

    private static int[] getSubArray(int[] nums, int start, int end)
    {
        int size = end - start;
        int[] nums_new = new int[size];
        for (int i = 0; i < size; i++)
        {
            nums_new[i] = nums[i + start];
        }
        return nums_new;
    }

    private static int[] mergeArrays(int[] left, int[] right)
    {
        int[] result = new int[left.length + right.length];
        int index_left = 0;
        int index_right = 0;
        int index_result = 0;

        while (index_left < left.length && index_right < right.length)
        {
            int num_left = left[index_left];
            int num_right = right[index_right];

            if (num_left < num_right)
            {
                result[index_result++] = num_left;
                index_left++;
            } else if (num_right < num_left)
            {
                result[index_result++] = num_right;
                index_right++;
            } else
            {
                result[index_result++] = num_left;
                result[index_result++] = num_right;
                index_left++;
                index_right++;
            }
        }

        while (index_left < left.length)
        {
            result[index_result++] = left[index_left++];
        }

        while (index_right < right.length)
        {
            result[index_result++] = right[index_right++];
        }

        if (result.length == 1)
        {
            diffs.add(result[0]);
        } else
        {
            diffs.add(result[1] - result[0]);
        }

        return result;
    }

}
