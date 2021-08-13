import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TrashIndexes3
{
    private static int[] sizes;
    private static int k;

    public static void main(String[] args) throws IOException
    {
        initFields();
        Arrays.sort(sizes);

        int size_full = (sizes.length * (sizes.length - 1)) / 2;
        int size_after_k = size_full - k;
        ArrayList<Integer> results = new ArrayList<>();
        int last_diff = 0;

        for (int i = sizes.length - 1; i > 0; i--)
        {
            int current_num = sizes[i];
            int next_min_diff = sizes[i - 1] - sizes[0];

            if (i == 1)
            {
                results.add(sizes[1] - sizes[0]);
                continue;
            }

            System.out.println("Current num is "+current_num);
            int j = 0;

            while (j < sizes.length)
            {
                System.out.println("Num to compare is "+sizes[j]);
                if (j == i)
                {
                    j++;
                    continue;
                }
                int diff = current_num - sizes[j];
                if (diff < 0)
                {
                    diff *= -1;
                }

                if(diff < next_min_diff)
                {
                    break;
                }
//                if (diff < next_min_diff || (diff > last_diff && last_diff != 0))
//                {
//                    break;
//                }

                last_diff = diff;
                results.add(diff);
                j++;
            }
        }

        Helper.printList(results);
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
