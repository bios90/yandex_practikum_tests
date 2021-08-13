import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ClubsMerger
{
    public static void main(String[] args) throws IOException
    {
        int[][] nums = getNums();
        sortNums(nums);

        int sorted_pos = 0;
        for (int i = 1; i < nums.length; i++)
        {
            int[] sorted = nums[sorted_pos];
            int[] current = nums[i];

            if (current[0] <= sorted[1])
            {
                if (current[1] > sorted[1])
                {
                    sorted[1] = current[1];
                }
            } else
            {
                sorted_pos++;
                nums[sorted_pos] = current;
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i <= sorted_pos; i++)
        {
            int[] pair = nums[i];
            out.append(pair[0]).append(" ").append(pair[1]).append("\n");
        }
        System.out.println(out.toString().trim());
    }

    private static void sortNums(int[][] nums)
    {
        Arrays.sort(nums, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] ints, int[] t1)
            {
                return ints[0] - t1[0];
            }
        });
    }

    private static int[][] getNums() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        int[][] nums = new int[count][];
        for (int i = 0; i < nums.length; i++)
        {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            nums[i] = new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
        }
        return nums;
    }
}
