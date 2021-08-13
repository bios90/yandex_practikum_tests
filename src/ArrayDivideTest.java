import java.util.Random;

public class ArrayDivideTest
{
    private static int counter = 0;
    private static int sort_fun_called = 0;

    public static void main(String[] args)
    {
        int[] nums = getNums();
        divide(nums);
        System.out.println("Counter is " + counter);
        System.out.println("Sort fun called  " + sort_fun_called);
    }

    private static void divide(int[] nums)
    {
        sort_fun_called++;
        if (nums.length == 1)
        {
            return;
        }

        counter++;
        int mid = nums.length / 2;
        int[] left = getSubArray(nums, 0, mid);
        int[] right = getSubArray(nums, mid, nums.length);

        System.out.print("Nums is : ");
        printArray(nums);
        System.out.print("Left is : ");
        printArray(left);
        System.out.print("Right is : ");
        printArray(right);
        System.out.println("Counter - "+counter);
        System.out.println("******************************");

        divide(left);
        divide(right);
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
//        return new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int max = 21;
        int[] nums = new int[max];
        nums[0] = new Random().nextInt(max);
        for (int i = 1; i < max; i++)
        {
            nums[i] = new Random().nextInt(max * 10) + nums[i - 1];
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
