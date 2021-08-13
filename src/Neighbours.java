import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

public class Neighbours
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num_lines = getSingleLineIntFromReader(reader);
        int num_columns = getSingleLineIntFromReader(reader);

        int[][] matrix = getMatrixFromReader(num_lines, num_columns, reader);

        int coordinate_y = getSingleLineIntFromReader(reader);
        int coordinate_x = getSingleLineIntFromReader(reader);

        List<Integer> neighbours = getNeighbours(matrix, coordinate_y, coordinate_x);
        String result = getListAsString(neighbours);
        System.out.println(result);

    }

    private static int[][] getMatrixFromReader(int max_y, int max_x, BufferedReader reader) throws IOException
    {
        int[][] matrix = new int[max_y][max_x];
        for (int i = 0; i < max_y; i++)
        {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < max_x; j++)
            {
                int num = Integer.parseInt(tokenizer.nextToken());
                matrix[i][j] = num;
            }
        }
        return matrix;
    }

    private static List<Integer> getNeighbours(int[][] matrix, int y, int x)
    {
        Integer left = getNumByCoordinates(matrix, y, x - 1);
        Integer top = getNumByCoordinates(matrix, y - 1, x);
        Integer right = getNumByCoordinates(matrix, y, x + 1);
        Integer bottom = getNumByCoordinates(matrix, y + 1, x);

        List<Integer> result = new ArrayList<>(Arrays.asList(left, top, right, bottom));
        result.removeAll(Collections.singleton(null));
        Collections.sort(result);
        return result;
    }

    private static int getSingleLineIntFromReader(BufferedReader reader) throws IOException
    {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String str = tokenizer.nextToken();
        return Integer.parseInt(str);
    }

    private static int[] getIntegersFromReaderLine(BufferedReader reader, int num_of_elements) throws IOException
    {
        int[] numbers = new int[num_of_elements];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < num_of_elements; i++)
        {
            int num = Integer.parseInt(tokenizer.nextToken());
            numbers[i] = num;
        }

        return numbers;
    }


    private static Integer getNumByCoordinates(int[][] matrix, int y, int x)
    {
        try
        {
            return matrix[y][x];
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    private static String getListAsString(List<Integer> result)
    {
        StringBuilder output_buffer = new StringBuilder();
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext())
        {
            output_buffer.append(iterator.next());
            if (iterator.hasNext())
            {
                output_buffer.append(" ");
            }
        }

        return output_buffer.toString();
    }

}















//import java.io.InputStreamReader;
//        import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.lang.StringBuilder;
//        import java.util.*;
//
//public class MySolution
//{
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        int length = Integer.parseInt(reader.readLine());
//        int[] houses = getNumsFromReader(reader, length);
//        ArrayList<Integer> free_house_poses = new ArrayList();
//        for(int i = 0; i < houses.length; i++)
//        {
//            if(houses[i] == 0)
//            {
//                free_house_poses.add(i);
//            }
//        }
//
//        Integer free_left;
//        Integer free_right;
//        if(houses[0] == 0)
//        {
//            free_left == 0;
//
//        }
//    }
//
//    private static int[] getNumsFromReader(BufferedReader reader, int length) throws IOException
//    {
//        int[] nums = new int[length];
//        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
//        for(int i = 0; i < length; i++)
//        {
//            nums[i] = Integer.parseInt(tokenizer.nextToken());
//        }
//        return nums
//    }
//
//    private static Integer getNumOrNull(List<Integer> list, int index)
//    {
//        try
//        {
//            return list.get(index);
//        }
//        catch(IndexOutOfBoundsException e)
//        {
//            return null
//        }
//    }
//
//}

