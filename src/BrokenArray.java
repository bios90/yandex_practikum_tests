public class BrokenArray
{
    public static void main(String[] args)
    {
        test();
    }

    public static int brokenSearch(int[] arr, int k)
    {
        int index = binarySearchBroken(arr, k, 0, arr.length - 1);
        return index;
    }


    private static int binarySearchBroken(int[] nums, int num, int start, int end)
    {
        int mid = (start + end) / 2;
        int num_mid = nums[mid];
        if (num == num_mid)
        {
            return mid;
        }

        if (nums[start] > nums[end])
        {
            if (num_mid > nums[start])
            {
                if (num >= nums[start] && num <= nums[mid])
                {
                    return binarySearch(nums, num, start, mid);
                } else
                {
                    return binarySearchBroken(nums, num, mid, end);
                }
            } else
            {
                if (num >= num_mid && num <= nums[end])
                {
                    return binarySearch(nums, num, mid, end);
                } else
                {
                    return binarySearchBroken(nums, num, start, mid);
                }
            }
        } else
        {
            return binarySearch(nums, num, start, end);
        }
    }

    private static int binarySearch(int[] nums, int num, int start, int end)
    {
        if (nums[start] == num)
        {
            return start;
        } else if (nums[end] == num)
        {
            return end;
        } else if (end <= start)
        {
            return -1;
        }

        int mid = (start + end) / 2;
        int num_mid = nums[mid];
        if (num_mid == num)
        {
            return mid;
        } else if (num < num_mid)
        {
            return binarySearch(nums, num, start, mid);
        } else
        {
            return binarySearch(nums, num, mid + 1, end);
        }
    }

    private static void test()
    {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }
}
