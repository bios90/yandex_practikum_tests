import java.util.Random;

public class ArrayTrasponizer
{
    public static void main(String[] args)
    {
        int[][] nums = generateArray(3);
        printArray(nums);

        for (int diagonal = 1; diagonal < nums.length; diagonal++)
        {
            for (int offset = diagonal - 1; offset >= 0; offset--)
            {
                int temp = nums[diagonal][offset];
                nums[diagonal][offset] = nums[offset][diagonal];
                nums[offset][diagonal] = temp;
            }
        }
        System.out.println("******");
        printArray(nums);
    }

    private static void printArray(int[][] array)
    {
        int side_size = array.length;
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < side_size; i++)
        {
            for (int j = 0; j < side_size; j++)
            {
                output_buffer.append(array[i][j]);
                output_buffer.append(' ');
            }
            output_buffer.append("\n");
        }
        System.out.println(output_buffer.toString().trim());
    }

    private static int[][] generateArray(int length)
    {
        int[][] arr = new int[length][length];
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                arr[i][j] = new Random().nextInt(90) + 10;
            }
        }

        return arr;
    }
}


//[1,2,3,8,9,0,0,0,0,0]
//        [2,5,6,9,12]

