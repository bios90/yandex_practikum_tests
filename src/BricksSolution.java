import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution
{
    public int leastBricks(List<List<Integer>> wall)
    {
        int max = getMax(wall);
        int min_collision = wall.size();
        for (int i = 1; i < max; i++)
        {
            int collisions = wall.size();
            for (List<Integer> line : wall)
            {
                if (hasJointAtPos(line, i))
                {
                    collisions--;
                }
            }
            if (collisions < min_collision)
            {
                min_collision = collisions;
            }
        }
        return min_collision;
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

    private static boolean hasJointAtPos(List<Integer> line, int pos)
    {

        int sum = 0;
        for (int num : line)
        {
            sum += num;
            if (sum == pos)
            {
                return true;
            }
            if (sum > pos)
            {
                break;
            }
        }

        return false;
    }
}