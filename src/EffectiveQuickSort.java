import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class EffectiveQuickSort
{
    public static void main(String[] args) throws IOException
    {
        User[] users = getUsers();
        quickSort(users, 0, users.length - 1);
        printResult(users);
    }


    private static void quickSort(User[] users, int left, int right)
    {
        if (left < right)
        {
            int partition = makePartitionV2(users, left, right);
            quickSort(users, left, partition - 1);
            quickSort(users, partition + 1, right);
        }
    }

    private static int makePartitionV2(User[] users, int left, int right)
    {
        int pivot_index = new Random().nextInt((right - left) + 1) + left;
        User pivot_user = users[pivot_index];

        while (left < right)
        {
            while (users[left].compareTo(pivot_user) > 0)
            {
                left++;
            }

            while (pivot_user.compareTo(users[right]) > 0)
            {
                right--;
            }

            swap(users, left, right);
        }

        return right;
    }

    private static int makePartitionTest(int[] nums, int left, int right)
    {
        int pivot_index = new Random().nextInt(right) + left;
        int pivot = nums[pivot_index];

        while (left < right)
        {
            while (nums[left] < pivot)
            {
                left++;
            }

            while (nums[right] > pivot)
            {
                right--;
            }

            Helper.swap(nums, left, right);
        }

        return right;
    }


    private static User[] getUsers() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        User[] users = new User[count];

        for (int i = 0; i < count; i++)
        {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            users[i] = new User(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        return users;
    }

    public static <T> void swap(T[] a, int i, int j)
    {
        if (i == j)
        {
            return;
        }
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void printResult(User[] users)
    {
        StringBuilder out = new StringBuilder();
        for (int i = users.length - 1; i >= 0; i--)
        {
            out.append(users[i].getName()).append("\n");
        }
        System.out.println(out.toString().trim());
    }

}

class User implements Comparable<User>
{
    private String name;
    private int result;
    private int penalty;

    public User(String name, int result, int penalty)
    {
        this.name = name;
        this.result = result;
        this.penalty = penalty;
    }

    public String getName()
    {
        return name;
    }

    public int getResult()
    {
        return result;
    }

    public int getPenalty()
    {
        return penalty;
    }

    @Override
    public int compareTo(User user)
    {
        if (this.getResult() != user.getResult())
        {
            return user.getResult() - this.getResult();
        } else if (this.getPenalty() != user.getPenalty())
        {
            return this.getPenalty() - user.getPenalty();
        } else
        {
            return this.getName().compareTo(user.getName());
        }
    }
}
