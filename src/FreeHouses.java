import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

public class FreeHouses
{
    public static void main(String[] args) throws IOException
    {

        int length = 5;
        int[] houses = new int[]{0, 1, 4, 9, 0};
        ArrayList<Integer> free_house_poses = new ArrayList();
        for (int i = 0; i < houses.length; i++)
        {
            if (houses[i] == 0)
            {
                free_house_poses.add(i);
            }
        }


        String str = String.valueOf("sadfsadf".charAt(2));

        int right_index = 0;
        Integer free_left = null;
        Integer free_right = free_house_poses.get(0);

        System.out.println("Free left is "+free_left);
        System.out.println("Free right is "+free_right);

        for (int i = 0; i < houses.length; i++)
        {
            if (houses[i] == 0)
            {
                free_left = free_right;
                free_right = getNumOrNull(free_house_poses, ++right_index);
            } else
            {
                if (free_left == null)
                {
                    houses[i] = free_right - i;
                } else if (free_right == null)
                {
                    houses[i] = i - free_left;
                } else
                {
                    System.out.println("I is "+i+" Left is "+free_left+" Right is "+free_right);
                    houses[i] = Math.min(i - free_left, free_right - i);
                }
            }
        }

        printResult(houses);
    }

    private static void printResult(int[] houses)
    {
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < houses.length; i++)
        {
            output_buffer.append(houses[i]);
            output_buffer.append(" ");
        }
        System.out.println(output_buffer.toString().trim());
    }

    private static int[] getNumsFromReader(BufferedReader reader, int length) throws IOException
    {
        int[] nums = new int[length];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < length; i++)
        {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return nums;
    }

    private static Integer getNumOrNull(List<Integer> list, int index)
    {
        try
        {
            return list.get(index);
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

}

