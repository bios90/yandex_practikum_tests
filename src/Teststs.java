import java.util.Map;

public class Teststs
{
    public static void main(String[] args)
    {
        String str = compress("aabcccccaaa");
        System.out.println(str);
    }

    public static String compress(String str)
    {
        StringBuilder out = new StringBuilder();
        int count = 1;
        Character prev = null;
        for (char c : str.toCharArray())
        {
            if (prev == null)
            {
                prev = c;
                continue;
            } else if (c == prev)
            {
                count++;
            } else
            {
                out.append(prev).append(count);
                count = 1;
            }
            prev = c;
        }


        out.append(prev).append(count);

        return out.toString();
    }
}
