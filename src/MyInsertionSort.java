import java.io.IOException;

public class MyInsertionSort
{
    public static void main(String[] args) throws IOException
    {
        int[] nums = getNums();
        for (int sorted = 0; sorted < nums.length; sorted++)
        {
            int num = nums[sorted];
            int i = sorted - 1;
            while (i >= 0 && nums[i] > num)
            {
                nums[i+1] = nums[i];
                i--;
            }
            nums[i+1] = num;
        }

        printArray(nums);
    }

    private static int[] getNums() throws IOException
    {
        return new int[]{12, -4, 7, 2, 55, 9};
    }

    private static void swap(int[] arr, int i, int j)
    {
        if (i == j)
        {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
