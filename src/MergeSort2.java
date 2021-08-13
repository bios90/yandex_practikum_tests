public class MergeSort2
{
    public static int[] merge(int[] arr, int left, int mid, int right)
    {
        int size = right - left;
        int[] sorted = new int[size];

        int i = left;
        int j = mid;
        int index = 0;

        while (i < mid && j < right)
        {
            int num_left = arr[i];
            int num_right = arr[j];
            if (num_left <= num_right)
            {
                sorted[index] = num_left;
                i++;
            } else
            {
                sorted[index] = num_right;
                j++;
            }
            index++;
        }

        while (i < mid)
        {
            sorted[index++] = arr[i++];
        }

        while (j < right)
        {
            sorted[index++] = arr[j++];
        }

        return sorted;
    }

    public static void merge_sort(int[] arr, int left, int right)
    {
        if (right - left == 1)
        {
            return;
        }

        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);
        int[] merged = merge(arr, left, mid, right);
        for (int i = 0; i < merged.length; i++)
        {
            arr[i + left] = merged[i];
        }
    }

    public static void main(String[] args)
    {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assert b.equals(expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        assert c.equals(expected2);
    }
}