public class MySelectionSort2
{
    public static void main(String[] args)
    {
        int[] nums = getNums();
        sortSelection(nums);
        printArray(nums);
    }

    private static void sortSelection(int[] nums)
    {
        for (int i = 0; i < nums.length - 1; i++)
        {
            int pos_to_compare = i;
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[j] < nums[pos_to_compare])
                {
                    pos_to_compare = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[pos_to_compare];
            nums[pos_to_compare] = temp;
        }
    }

    private static int[] getNums()
    {
        return new int[]{12, -4, 7, 8, 2, 55, 9, 112};
    }

    private static void printArray(int[] nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}
