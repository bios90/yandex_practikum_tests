import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TrashIndexes2
{
    private static int[] sizes;
    private static int k;

    public static void main(String[] args) throws IOException
    {
        initFields();
        Arrays.sort(sizes);

        int median = sizes.length / 2;
        int[] poses = getLeftRight2(0, sizes.length, median);
        List<Integer> variants = getVariants(poses[0], poses[1]);

        System.out.println(variants.get(k - 1));
    }

    private static List<Integer> getVariants(int left, int right)
    {
        List<Integer> nums = new ArrayList<>();
        for (int i = left; i <= right; i++)
        {
            for (int j = i + 1; j <= right; j++)
            {
                nums.add(sizes[j] - sizes[i]);
            }
        }

        Collections.sort(nums);
        return nums;
    }

    private static int getVariantsCount(int num)
    {
        return (num * ((num - 1)) / 2);
    }

    private static int[] getLeftRight2(int left, int right, int median)
    {
        int size_left = (median - left) + 1;
        int variants_count_left = getVariantsCount(size_left);

        if (variants_count_left >= k)
        {
            return new int[]{left, median};
        } else
        {
            int new_median = (median + right) / 2;
            return getLeftRight2(left, right, new_median);
        }
    }

    private static void initFields() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        sizes = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++)
        {
            sizes[i] = Integer.parseInt(tokenizer.nextToken());
        }
        k = Integer.parseInt(reader.readLine());
    }
}
