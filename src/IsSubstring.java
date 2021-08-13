import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IsSubstring
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str_1 = reader.readLine();
        String str_2 = reader.readLine();
        if(isSubstring(str_1,str_2))
        {
            System.out.println("True");
        }
        else
            {
                System.out.println("False");
            }
    }

    private static boolean isSubstring(String str_1, String str_2)
    {
        if (str_1.length() > str_2.length())
        {
            return false;
        }

        int i;
        int j = 0;
        for (i = 0; i < str_1.length(); i++)
        {
            char c = str_1.charAt(i);
            while (j < str_2.length())
            {
                char c2 = str_2.charAt(j);
                if (c == c2)
                {
                    break;
                }
                if (j == str_2.length() - 1)
                {
                    return false;
                }
                j++;
            }
        }

        return true;
    }

}
