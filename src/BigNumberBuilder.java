import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BigNumberBuilder
{
    public static void main(String[] args) throws IOException
    {
        int[] nums = getNums();
        for (int sorted = nums.length - 1; sorted > 0; sorted--)
        {
            for (int i = 0; i < sorted; i++)
            {
                int num_1 = nums[i];
                int num_2 = nums[i + 1];
                if (isFirstGreater(num_1, num_2))
                {
                    swap(nums, i, i + 1);
                }
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--)
        {
            out.append(nums[i]);
        }
        System.out.println(out.toString());
    }

    private static boolean isFirstGreater(int num1, int num2)
    {
        int num_1_num_2 = Integer.parseInt(String.valueOf(num1) + String.valueOf(num2));
        int num_2_num_1 = Integer.parseInt(String.valueOf(num2) + String.valueOf(num1));

        return num_1_num_2 > num_2_num_1;
    }

    private static int[] getNums() throws IOException
    {
//        return new int[]{15, 56, 2};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] nums = new int[count];
        for (int i = 0; i < nums.length; i++)
        {
            nums[i] = Integer.valueOf(tokenizer.nextToken());
        }
        return nums;
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
}
