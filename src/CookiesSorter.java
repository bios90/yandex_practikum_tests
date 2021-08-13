import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CookiesSorter
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] greed_values = getIntArray(reader);
        int[] cookie_sizes = getIntArray(reader);


        sort(greed_values, 0, greed_values.length - 1);
        sort(cookie_sizes, 0, cookie_sizes.length - 1);

        Helper.printArray(greed_values);
        Helper.printArray(cookie_sizes);

        int happy_child_count = 0;
        int cookie_index = 0;
        for (int i = 0; i < greed_values.length; i++)
        {
            if (cookie_index == cookie_sizes.length)
            {
                break;
            }

            for (int j = cookie_index; j < cookie_sizes.length; j++)
            {
                if (cookie_sizes[j] >= greed_values[i])
                {
                    happy_child_count++;
                    cookie_index++;
                    break;
                }

                cookie_index++;
            }
        }

        System.out.println(happy_child_count);
    }

    private static int[] getIntArray(BufferedReader reader) throws IOException
    {
        int count = Integer.parseInt(reader.readLine());
        int[] nums = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++)
        {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return nums;
    }

    private static void sort(int[] nums, int left, int right)
    {
        if (left < right)
        {
            int partition = getPartition(nums, left, right);
            sort(nums, left, partition - 1);
            sort(nums, partition + 1, right);
        }
    }

    private static int getPartition(int[] nums, int left, int right)
    {
        int num = nums[right];
        int index = left - 1;
        for (int i = left; i < right; i++)
        {
            if (nums[i] < num)
            {
                swap(nums, i, ++index);
            }
        }

        swap(nums, ++index, right);
        return index;
    }

    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
