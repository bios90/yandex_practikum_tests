import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyBricks2
{
    public static void main(String[] args)
    {
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 2));
        wall.add(Arrays.asList(2, 4));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 1, 1));
        int count = leastBricks(wall);
        System.out.println(count);
    }

    public static int leastBricks(List<List<Integer>> wall)
    {
        int max = getMax(wall);
        int[] results = new int[max];
        List<List<Integer>> joins = getJoins(wall);
        for (List<Integer> join : joins)
        {
            for (int num : join)
            {
                results[num] = results[num] + 1;
            }
        }
        int max_repeat = Integer.MIN_VALUE;
        for (int num : results)
        {
            if (num > max_repeat)
            {
                max_repeat = num;
            }
        }
        return wall.size() - max_repeat;
    }

    private static int getMax(List<List<Integer>> wall)
    {
        int max = 0;
        List<Integer> top_line = wall.get(0);
        for (int num : top_line)
        {
            max += num;
        }
        return max;
    }

    private static List<List<Integer>> getJoins(List<List<Integer>> wall)
    {
        List<List<Integer>> joins = new ArrayList<>();
        for (List<Integer> line : wall)
        {
            ArrayList<Integer> join = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < line.size() - 1; i++)
            {
                int num = line.get(i);
                sum += num;
                join.add(sum);
            }
            joins.add(join);
        }
        return joins;
    }
}