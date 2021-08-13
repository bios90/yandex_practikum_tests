import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MySelectionSort
{
//    return new int[]{12, -4 , 7 , 2, 55, 9};
    public static void main(String[] args) throws IOException
    {
        int[] nums = getNums();
        for (int sorted = 0; sorted < nums.length - 1; sorted++)
        {
            int min = sorted;
            for (int i = min + 1; i < nums.length; i++)
            {
                if (nums[i] < nums[min])
                {
                    min = i;
                }
            }
            swap(nums, sorted, min);
            printArray(nums);
        }
    }

    private static int[] getNums() throws IOException
    {
        return new int[]{12, -4 , 7 , 2, 55, 9};
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
