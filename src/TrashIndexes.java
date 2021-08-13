import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TrashIndexes
{
    private static int[] sizes;
    private static int k;

    public static void main(String[] args) throws IOException
    {
        initFields();
        Arrays.sort(sizes);
        Helper.printArray(sizes);


        List<Integer> diffs = getDiffs();
        Helper.printList(diffs);

        int result = diffs.get(k - 1);
        System.out.println(result);
    }

    private static List<Integer> getDiffs()
    {
        List<Integer> results = new ArrayList<>();
        int diff = 1;
        while (results.size() < k)
        {
            for (int i = 0; i < sizes.length - diff; i++)
            {
                int d_to_next = sizes[i + diff] - sizes[i];
                results.add(d_to_next);

//                for (int j = 0; j < i; j++)
//                {
//                    int d_to_first = sizes[i] - sizes[j];
//                    if (d_to_first < d_to_next)
//                    {
//                        results.add(d_to_first);
//                    } else
//                    {
//                        break;
//                    }
//                }

            }
            diff++;
        }

        Collections.sort(results);
        return results;
    }


    private static List<Integer> getDiffsHard()
    {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < sizes.length; i++)
        {
            for (int j = i + 1; j < sizes.length; j++)
            {
                int diff = Math.abs(sizes[j] - sizes[i]);
                results.add(diff);
            }
        }
        Collections.sort(results);
        return results;
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
