import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoBycycles
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] days = getDays(reader);
        int price = getPrice(reader);

        int[] result = new int[]{-1, -1};

        for (int i = 0; i < days.length; i++)
        {
            int day = days[i];
            if (day >= price)
            {
                if (result[0] == -1)
                {
                    result[0] = i + 1;
                }
            }
            if (day >= price * 2)
            {
                result[1] = i + 1;
                break;
            }
        }

        System.out.print(result[0]);
        System.out.print(" ");
        System.out.print(result[1]);
    }

    private static int[] getDays(BufferedReader reader) throws IOException
    {
//        return new int[]{1, 2, 4, 4, 4, 4};
        int count = Integer.parseInt(reader.readLine());
        int[] days = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < days.length; i++)
        {
            days[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return days;
    }

    private static int getPrice(BufferedReader reader) throws IOException
    {
        int price = Integer.parseInt(reader.readLine());
        return price;
    }
}
