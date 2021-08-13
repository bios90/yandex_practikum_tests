import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrashIndexes4
{
    private static int[] sizes;
    private static int k;
    private static int max_variants;

    public static void main(String[] args) throws IOException
    {
        initFields();
        Arrays.sort(sizes);

        getKIndex(0, sizes[sizes.length - 1]);
    }

    private static void getKIndex(int left, int right)
    {
        int mid_num = (right + left) / 2;

        int[] counts = getCountLower(mid_num);

        if (counts[0] == k || right == left)
        {
            System.out.println(counts[2]);
            return;
        }

        if (k < counts[0])
        {
            getKIndex(left, mid_num);
        } else
        {
            getKIndex(++mid_num, right);
        }
    }

    private static int[] getCountLower(int num)
    {
        int index;
        for (index = sizes.length - 1; index > 0; index--)
        {
            if (sizes[index] <= num)
            {
                break;
            }
        }

        int count = 0;
        int max_min_diff = -1;
        for (int j = 0; j < sizes.length; j++)
        {
            int num1 = sizes[j];
            for (int q = sizes.length - 1; q > j; q--)
            {
                int num2 = sizes[q];
                int diff = num2 - num1;
                if (diff < num)
                {
                    if (diff > max_min_diff)
                    {
                        max_min_diff = diff;
                    }
                    count += q - j;
                    break;
                }
            }
        }

        int count_after = max_variants - count;
        return new int[]{count, count_after, max_min_diff};
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
        max_variants = (count * (count - 1)) / 2;
    }
}
