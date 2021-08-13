import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PartialSort
{
    public static void main(String[] args) throws IOException
    {
        int[] nums = getNums();
        int index = 0;

        int groups_count = 0;

        List<Integer> sub_group = new ArrayList<>();
        boolean group_contains_min = false;

        for (int i = 0; i < nums.length; i++)
        {
            int num = nums[i];
            sub_group.add(num);
            if (num == index)
            {
                group_contains_min = true;
            }

            if (group_contains_min)
            {
                if (checkSubGroup(sub_group, index))
                {
                    index += sub_group.size();
                    groups_count++;
                    sub_group.clear();
                    group_contains_min = false;
                }
            }
        }

        System.out.println(groups_count);
    }

    private static boolean checkSubGroup(List<Integer> group, int min_index)
    {
        Collections.sort(group);
        for (int i = 0; i < group.size(); i++)
        {
            if (i + min_index != group.get(i))
            {
                return false;
            }
        }

        return true;
    }

    private static int[] getNums() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] nums = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < nums.length; i++)
        {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return nums;
    }
}
