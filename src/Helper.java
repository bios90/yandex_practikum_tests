import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Helper
{
    public static void printArray(int[] nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }
    public static void printArray(long[] nums)
    {
        StringBuilder out = new StringBuilder();
        for (long num : nums)
        {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }

    public static void printList(List<Integer> nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }

    public static void printListOfLongs(List<Long> nums)
    {
        StringBuilder out = new StringBuilder();
        for (long num : nums)
        {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }

    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void printDiffs(int[] nums)
    {
        ArrayList<DiffData> diffs = new ArrayList<>();

        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                int num2 = nums[j];
                int num1 = nums[i];
                int diff = num2 - num1;
                diffs.add(new DiffData(num1, num2, diff));
            }
        }

        Collections.sort(diffs, new Comparator<DiffData>()
        {
            @Override
            public int compare(DiffData diffData, DiffData t1)
            {
                return diffData.getDiff() - t1.getDiff();
            }
        });

        int pos = 1;
        StringBuilder out = new StringBuilder();
        for (DiffData data : diffs)
        {
            out.append(pos++).append(" - ").append(data.getDiff()).append(" (").append(data.getNum2()).append(" - ").append(data.getNum1()).append(")").append(" | ");
        }
        System.out.println(out.toString());
    }
}

class DiffData
{
    private int num1;
    private int num2;
    private int diff;

    public DiffData(int num1, int num2, int diff)
    {
        this.num1 = num1;
        this.num2 = num2;
        this.diff = diff;
    }

    public int getNum1()
    {
        return num1;
    }

    public int getNum2()
    {
        return num2;
    }

    public int getDiff()
    {
        return diff;
    }
}
