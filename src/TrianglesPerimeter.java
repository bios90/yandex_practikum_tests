import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglesPerimeter
{
    public static void main(String[] args) throws IOException
    {
        int[] sizes = getSizes();
        Arrays.sort(sizes);

        for (int i = sizes.length - 1; i >= 2; i--)
        {
            int c = sizes[i];
            for (int j = i - 1; j >= 1; j--)
            {
                int a = sizes[j];
                int b = sizes[j - 1];
                
                if (c < a + b)
                {
                    System.out.println(a + b + c);
                    return;
                }
            }
        }
    }

    private static int[] getSizes() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] sizes = new int[count];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++)
        {
            sizes[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return sizes;
    }
}
