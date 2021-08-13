public class MyMergeSort
{
    public static void main(String[] args)
    {
//        int[]{39, 28, 44, 4, 10, 83, 11};
        int[] nums = getNums();
        mergeSort(nums);
        printArray(nums);
    }

    private static void test()
    {
        int[] nums = new int[6];
        int[] left = new int[]{1, 5, 9, 12};
        int[] right = new int[]{3, 6};
        mergeArrays(nums, left, right);
        printArray(nums);
    }

    private static void mergeSort(int[] nums)
    {
        if (nums.length == 1)
        {
            return;
        }

        int mid = (nums.length / 2);
        int[] left = getSubArray(nums, 0, mid);
        int[] right = getSubArray(nums, mid, nums.length);

        mergeSort(left);
        mergeSort(right);

        mergeArrays(nums, left, right);
    }

    private static void mergeArrays(int[] nums, int[] left, int[] right)
    {
        int i = 0, j = 0, k = 0;
        while (j < left.length && k < right.length)
        {
            if (left[j] <= right[k])
            {
                nums[i] = left[j];
                j++;
            } else
            {
                nums[i] = right[k];
                k++;
            }
            i++;
        }

        while (j < left.length)
        {
            nums[i++] = left[j++];
        }

        while (k < right.length)
        {
            nums[i++] = right[k++];
        }
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


    private static int[] getNums()
    {
        return new int[]{39, 28, 44, 4, 10, 83, 11};
    }

    private static void printArray(int[] nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}
