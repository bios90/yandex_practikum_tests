import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IslandMedian
{
    private static int[] north;
    private static int[] south;

    public static void main(String[] args) throws IOException
    {
        initFields();
        int[] sum = mergeTwoArrays();
        Helper.printArray(sum);

        double median = getMedian(sum);
        System.out.println(median);
    }

    private static int[] mergeTwoArrays()
    {
        int[] result = new int[north.length + south.length];
        int index_north = 0;
        int index_south = 0;
        int index_result = 0;

        while (index_north < north.length && index_south < south.length)
        {
            int num_north = north[index_north];
            int num_south = south[index_south];

            if (num_north < num_south)
            {
                result[index_result++] = num_north;
                index_north++;
            } else if (num_south < num_north)
            {
                result[index_result++] = num_south;
                index_south++;
            } else
            {
                result[index_result++] = num_south;
                result[index_result++] = num_north;
                index_north++;
                index_south++;
            }
        }

        while (index_north < north.length)
        {
            result[index_result++] = north[index_north++];
        }

        while (index_south < south.length)
        {
            result[index_result++] = south[index_south++];
        }

        return result;
    }

    private static double getMedian(int[] nums)
    {
        if (nums.length % 2 == 0)
        {
            int right_index = nums.length / 2;
            int left = nums[right_index - 1];
            int right = nums[right_index];
            return (left + right) / 2.0;
        } else
        {
            int index = (nums.length / 2);
            return nums[index];
        }
    }

    private static void initFields() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count_north = Integer.parseInt(reader.readLine());
        int count_south = Integer.parseInt(reader.readLine());

        north = new int[count_north];
        south = new int[count_south];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < north.length; i++)
        {
            north[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < south.length; i++)
        {
            south[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
