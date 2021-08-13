import java.util.Random;

public class QuickSortTest
{
    public static void main(String[] args)
    {
        int[] nums = getNums();
        quickSort(nums, 0, nums.length - 1);
        Helper.printArray(nums);
    }

    private static int[] getNums()
    {
        return new int[]{12, -4, 7, 2, 55, 9};
//        int[] nums = new int[10];
//        for (int i = 0; i < nums.length; i++)
//        {
//            nums[i] = new Random().nextInt(100);
//        }
//        return nums;
    }

    private static void quickSort(int[] arr, int left, int right)
    {
        if (left < right)
        {
            int partition = makePartition(arr, left, right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
    }

    private static int makePartition(int[] nums, int left, int right)
    {
        System.out.println("Array before sort is ");
        Helper.printArray(nums);
        int pivot = nums[right];
        int index = left - 1;

        for (int j = left; j < right; j++)
        {
            if (nums[j] < pivot)
            {
                index++;
                swap(nums, j, index);
            }
        }

        swap(nums, right, ++index);
        System.out.println("Need to make partition from " + left + " - " + right + " Partition is " + index);
        System.out.println("Array after sort ");
        Helper.printArray(nums);
        System.out.println("*********************************");
        return index;
    }

    private static int makePartitionTest(int[] nums, int left, int right)
    {
        int pivot_index = new Random().nextInt(right);
        int pivot = nums[pivot_index];

        while (left < right)
        {
            while (nums[left] < pivot)
            {
                left++;
            }

            while (nums[right] > pivot)
            {
                right--;
            }

            Helper.swap(nums, left, right);
        }

        return right;
    }

    private static void swap(int[] nums, int i, int k)
    {
        if (i == k)
        {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }
}
