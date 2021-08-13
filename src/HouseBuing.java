import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HouseBuing
{
    private static int[] houses;
    private static int money_count;

    public static void main(String[] args) throws IOException
    {
        initFields();
        Arrays.sort(houses);

        int total_price = 0;
        int houses_to_buy = 0;
        int house_index = 0;
        while (total_price < money_count && house_index < houses.length)
        {
            int price = houses[house_index];
            if (total_price + price <= money_count)
            {
                total_price += price;
                house_index++;
                houses_to_buy++;
            } else
            {
                break;
            }
        }

        System.out.println(houses_to_buy);
    }

    private static void initFields() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int houses_count = Integer.parseInt(tokenizer.nextToken());
        houses = new int[houses_count];
        money_count = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < houses_count; i++)
        {
            houses[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
