import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyParathesisGenerator
{
    private static int countValid = 0;
    public static void main(String[] args) throws IOException
    {
        int count = getCount();
        String result = "";
        generate(result, 0, count);
        System.out.println(countValid);
    }

    private static int getCount() throws IOException
    {
        return 10;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int count = Integer.parseInt(reader.readLine());
//        return count;
    }

    private static void generate(String result, int pos, int count)
    {
        if (pos == count * 2)
        {
            if (isValidBraces(result))
            {
                countValid++;
                System.out.println(result);
            }
        } else
        {
            String copy = String.valueOf(result.toCharArray());

            if (countOfChar(copy, '(') < count)
            {
                String str = copy + "(";
                generate(str, pos + 1, count);
            }

            if (countOfChar(copy, ')') < count)
            {
                String str = copy + ")";
                generate(str, pos + 1, count);
            }

        }
    }

    private static int countOfChar(String str, char c)
    {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == c)
            {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidBraces(String str)
    {
        String copy = String.valueOf(str.toCharArray());

        for (int i = 0; i < str.length() / 2; i++)
        {
            copy = copy.replace("()", "");
            if (copy.length() == 0)
            {
                return true;
            }
        }
        return false;
    }
}

