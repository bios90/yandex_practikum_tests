import java.util.*;
import java.util.function.BiConsumer;

class MyBricks3
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

    private static Map<Integer, Integer> map;

    public static int leastBricks(List<List<Integer>> wall)
    {
        map = new HashMap<>();
        countJoins(wall);
        int max_repeat = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if (entry.getValue() > max_repeat)
            {
                max_repeat = entry.getValue();
            }
        }
        return wall.size() - max_repeat;
    }


    private static void countJoins(List<List<Integer>> wall)
    {
        for (List<Integer> line : wall)
        {
            int sum = 0;
            for (int i = 0; i < line.size() - 1; i++)
            {
                int num = line.get(i);
                sum += num;
                Integer count = map.get(sum);
                if (count == null)
                {
                    count = 0;
                }
                map.put(sum, count + 1);
            }
        }
    }
}