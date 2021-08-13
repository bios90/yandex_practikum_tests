import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class ArrayMerger
{
    public static void main(String[] args)
    {
        merge();
    }

    public static void merge()
    {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;

        int index = m + n - 1;
        while (index >= 0)
        {
            Integer num_m = getOrZero(nums1, m - 1);
            Integer num_n = getOrZero(nums2, n - 1);

            if (num_m == null)
            {
                nums1[index] = num_n;
                m--;
            } else if (num_n == null)
            {
                nums1[index] = num_m;
                n--;
            } else if (num_m > num_n)
            {
                m--;
                nums1[index] = num_m;
            } else if (num_n > num_m)
            {
                n--;
                nums1[index] = num_n;
            } else
            {
                n--;
                m--;
                nums1[index] = num_m;
                nums1[--index] = num_m;
            }
            index--;
        }


        printArray(nums1);
    }

    private static Integer getOrZero(int[] arr, int index)
    {
        try
        {
            return arr[index];
        } catch (Exception e)
        {
            return null;
        }
    }

    public static void merge2()
    {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;

        for (int i = 0; i < n; i++)
        {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        printArray(nums1);
    }

    private static void shiftArrayByOne(int[] arr, int from)
    {
        for (int i = arr.length - 1; i > from; i--)
        {
            arr[i] = arr[i - 1];
        }
    }

    private static void printArray(int[] arr)
    {
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
        {
            output_buffer.append(arr[i]);
            output_buffer.append(" ");
        }
        System.out.println(output_buffer.toString().trim());
    }
}
