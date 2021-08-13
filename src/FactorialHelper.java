import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

public class FactorialHelper
{

    private static SortedSet<Integer> set;

    public static void main(String[] args) throws IOException
    {
        int num = 23342342;


        String str = "123123123123";
        str.isEmpty();
        str.length();
        str.replace(" ","");
        str.replaceFirst("c","");

        int[] nums = new int[10];
        int sum = 32423424;
        String str_sum = String.valueOf(sum);
        StringBuilder output_buffer = new StringBuilder();
        for(int i = 0; i < str_sum.length(); i++)
        {
            output_buffer.append(str_sum.charAt(i));
            output_buffer.append(' ');
        }
        System.out.println(output_buffer.toString());

        if(set == null)
        {
            initSet();
        }

        if(isPrime(num))
        {
            System.out.println(num);
        }
        else
        {
            ArrayList<Integer> result = new ArrayList();


            for(Integer prime_num : set)
            {
                while(num % prime_num == 0)
                {
                    result.add(prime_num);
                    num /= prime_num;
                    boolean is_remainder_prime = isPrime(num);
                    if(is_remainder_prime)
                    {
                        result.add(num);
                    }
                    if(num < 2 || is_remainder_prime)
                    {
                        printResult(result);
                        return;
                    }
                }
            }
        }
    }

    private static boolean isPrime(long num)
    {
        if (num == 1)
        {
            return false;
        }

        for (int i = 2; i * i <= num; i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private static void printResult(ArrayList<Integer> result)
    {
        StringBuilder output_buffer = new StringBuilder();
        for(int i = 0; i < result.size(); i++)
        {
            output_buffer.append(result.get(i).toString());
            output_buffer.append(" ");
        }
        System.out.println(output_buffer.toString().trim());
    }

    private static long getSingleLineIntFromReader(BufferedReader reader) throws IOException
    {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String str = tokenizer.nextToken();
        return Long.parseLong(str);
    }

    private static void initSet()
    {
        int max = (int) Math.sqrt(1000000000);

        set = new TreeSet<>();
        ArrayList<Integer> lowest_delimeters = new ArrayList((int) max + 1);
        for (int i = 0; i <= max; i++)
        {
            lowest_delimeters.add(0);
        }

        for (int i = 2; i <= max; i++)
        {
            if (lowest_delimeters.get(i) == 0)
            {
                lowest_delimeters.set(i, i);
                set.add(i);
            }

            for (int prime : set)
            {
                int not_prime = prime * i;
                if (prime > lowest_delimeters.get(i) || not_prime > max)
                {
                    break;
                }
                lowest_delimeters.set(not_prime, prime);
            }
        }
    }
}