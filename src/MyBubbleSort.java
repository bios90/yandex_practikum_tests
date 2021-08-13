import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MyBubbleSort
{
    public static void main(String[] args) throws IOException
    {
        int[] nums = getNums();
        boolean was_printed = false;
        for (int last_sorted = nums.length - 1; last_sorted > 0; last_sorted--)
        {
            boolean need_to_print = false;
            for (int i = 0; i < last_sorted; i++)
            {
                if (nums[i] > nums[i + 1])
                {
                    need_to_print = true;
                    swap(nums, i, i + 1);
                }
            }
            if (need_to_print)
            {
                was_printed = true;
                printArray(nums);
            }
        }

        if(!was_printed)
        {
            printArray(nums);
        }
    }

    private static int[] getNums() throws IOException
    {
        return new int[]{12, 8, 9, 10, 11};
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int count = Integer.parseInt(reader.readLine());
//        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
//        int[] nums = new int[count];
//        for (int i = 0; i < nums.length; i++)
//        {
//            nums[i] = Integer.parseInt(tokenizer.nextToken());
//        }
//        return nums;
    }

    private static void swap(int[] arr, int i, int j)
    {
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
