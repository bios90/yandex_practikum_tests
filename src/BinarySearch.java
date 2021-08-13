import java.util.Random;

public class BinarySearch
{
    public static void main(String[] args)
    {
    }

    private static int binarySearch(int[] nums, int num, int start, int end)
    {
        if (end <= start)
        {
            return -1;
        }
        int mid = (start + end) / 2;
        int num_mid = nums[mid];
        if (num_mid == num)
        {
            return mid;
        } else if (num < num_mid)
        {
            return binarySearch(nums, num, start, mid);
        } else
        {
            return binarySearch(nums, num, mid+1, end);
        }
    }

    private static int[] getArray(int size)
    {
        int step = 10;
        int[] nums = new int[size];
        nums[0] = new Random().nextInt(step);
        for (int i = 1; i < nums.length; i++)
        {
            int n = nums[i - 1] + new Random().nextInt(step) + 1;
            nums[i] = n;
        }

        return nums;
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
