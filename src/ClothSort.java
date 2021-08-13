import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClothSort
{
    public static void main(String[] args) throws IOException
    {
        int[] cloth = getCloth();
        int[] colors_count = new int[3];

        for (int color : cloth)
        {
            colors_count[color] = colors_count[color] + 1;
        }

        int[] result = new int[cloth.length];
        int index = 0;
        int color = 0;
        for (int count : colors_count)
        {
            for (int i = 0; i < count; i++)
            {
                result[index] = color;
                index++;
            }
            color++;
        }

        printResult(result);
    }

    private static int[] getCloth() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] cloth = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++)
        {
            cloth[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return cloth;
    }

    private static void printResult(int[] result)
    {
        StringBuilder out = new StringBuilder();
        for (int num : result)
        {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}
