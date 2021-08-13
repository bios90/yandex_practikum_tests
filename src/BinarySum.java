import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

public class BinarySum
{
    public static void main(String[] args) throws IOException
    {
        initSet();
        for(int num : set)
        {
            System.out.println(num);
        }
    }

    private static int getSingleLineIntFromReader(BufferedReader reader) throws IOException
    {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String str = tokenizer.nextToken();
        return Integer.parseInt(str);
    }

    private static void addSumToList(int sum, ArrayList<Integer> list)
    {

    }

    private static Set<Integer> set = new HashSet();
    private static void initSet()
    {
        int max = 10000;
        int pow = 1;
        while (true)
        {
            int num = (int)Math.pow(4,pow);
            if(num > max)
            {
                break;
            }
            set.add(num);
            pow++;
        }
    }

}


    	