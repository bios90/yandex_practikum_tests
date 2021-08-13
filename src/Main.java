import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
//        Set<Integer> eratos = getEratsofeneSet(1000000000);

        SortedSet<Integer> set = getEratsofeneSet(10000);
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            int num = (int)iterator.next();
            System.out.println("Num is "+num);
        }
    }

    public static void test(String[] args) throws IOException
    {


//        int num = 14
        ArrayList<Integer> digits = new ArrayList();
        digits.get(digits.size() - 1);

//        while(num > 0)
//        {
//            digits.add(num % 2);
//            num /= 2;
//        }
//
//        StringBuilder output_buffer = new StringBuilder();
//        for(int i = digits.size() - 1; i >= 0; i++)
//        {
//            output_buffer.append(digits.get(i).toString());
//        }
//        System.out.println(output_buffer.toString());
//
//        int num11 = 123;
//        String.valueOf(123).length()
//        num11.toS
//        Math.max()
    }

    private static int getSingleLineIntFromReader(BufferedReader reader) throws IOException
    {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String str = tokenizer.nextToken();
        return Integer.parseInt(str);
    }

    private static void getNum()
    {
        ArrayList<Integer> binary_digits = new ArrayList<>();
        int num = 14;
        while (num > 0)
        {
            binary_digits.add(num % 2);
            num /= 2;
        }

        binary_digits.get(0).toString();
        printList(binary_digits);

//        String str = "A man, a plan, a canal: Panama".replaceAll("[^A-Za-z0-9]+", "");


//        System.out.println(str);
//        List<Integer> result = new ArrayList<>(Arrays.asList(num_1, num_2, num_3, num_4));
//        result.removeAll(Collections.singleton(null));
//        Collections.sort(result);
//
//        String str_result = getListAsString(result);
//        System.out.println(str_result);

    }

    private static String getListAsString(List<Integer> result)
    {
        StringBuilder output_buffer = new StringBuilder();
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext())
        {
            output_buffer.append(iterator.next());
            if (iterator.hasNext())
            {
                output_buffer.append(" ");
            }
        }

        return output_buffer.toString();
    }



//        ArrayList<ArrayList<Integer>> eratosphene_linear = getEratsofeneLinear(8);
//        printArrayList(eratosphene_linear.get(0));
//        printArrayList(eratosphene_linear.get(1));
//        ArrayList<Object> smaller_eratosphene = getEratsofene(27);
//        for (Object prime : smaller_eratosphene)
//        {
//            System.out.println(prime);
//        }
//        boolean is_simple = isPrimeSimple2(4);
//        System.out.println(is_simple);

//        ArrayList<Integer> smaller_primes = getLowerPrimes(19);
//        for (int prime : smaller_primes)
//        {
//            System.out.println(prime);
//        }

    private static void printList(List<Integer> nums)
    {
        for (int number : nums)
        {
            System.out.println(number);
        }
        System.out.println("*******************");
    }

    private static boolean isPrimeSimple(int num)
    {
        if (num == 1)
        {
            return false;
        }

        for (int i = 2; i < num; i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isPrimeSimple2(int num)
    {
        if (num == 1)
        {
            return false;
        }

        double square_root = Math.sqrt(num);
        for (int i = 2; i <= square_root; i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isPrimeSimple3(int num)
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

    private static ArrayList<Integer> getLowerPrimes(int num)
    {
        ArrayList<Integer> smaller_primes = new ArrayList<>();
        for (int i = 2; i < num; i++)
        {
            if (isPrimeSimple3(i))
            {
                smaller_primes.add(i);
            }
        }
        return smaller_primes;
    }

    private static ArrayList<Object> getEratsofene(int max)
    {
        ArrayList<Object> objs = new ArrayList<>(max + 1);
        for (int i = 0; i <= max; i++)
        {
            if (i == 0 || i == 1)
            {
                objs.add(false);
            } else
            {
                objs.add(i);
            }
        }

        for (int i = 0; i < objs.size(); i++)
        {
            if (i * i >= max)
            {
                break;
            }

            Object obj = objs.get(i);
            if (obj instanceof Boolean)
            {
                continue;
            }

            int num = (int) obj;
            int multiplier = 2;
            while (num * multiplier <= max)
            {
                objs.set(i * multiplier, false);
                multiplier++;
            }
        }

        return objs;
    }

    //    max = 8
    //    [0,0,2,0,2,0,0,0,0] lowest_delimeters
    //    [2,]                prime_numbers

    //    [0,1,2,3,4,5,6,7,8]
    //    [0,0,2,3,2,5,2,7,2]
    private static ArrayList<ArrayList<Integer>> getEratsofeneLinear(int max)
    {
        ArrayList<Integer> prime_numbers = new ArrayList<>();
        ArrayList<Integer> lowest_delimeters = new ArrayList(max + 1);
        for (int i = 0; i <= max; i++)
        {
            lowest_delimeters.add(0);
        }

        //i = 2
        for (int i = 2; i <= max; i++)
        {
            if (lowest_delimeters.get(i) == 0)
            {
                lowest_delimeters.set(i, i);
                prime_numbers.add(i);

                int multiplier = 2;
                while (i * multiplier <= max)
                {
                    int num = i * multiplier;
                    if (i <= lowest_delimeters.get(num))
                    {
                        break;
                    }
                    lowest_delimeters.set(num, i);

                    multiplier++;
                }

//                for (int prime : prime_numbers)
//                {
//                    int not_prime_number = prime * i;
//                    if (not_prime_number > max || not_prime_number > lowest_delimeters.get(i))
//                    {
//                        break;
//                    }
//                    lowest_delimeters.set(not_prime_number, prime);
//                }
            }
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(prime_numbers);
        result.add(lowest_delimeters);
        return result;
    }

    //max = 8
    //0,0,2,0,4,0,0,0
    //2,
    private static SortedSet<Integer> getEratsofeneSet(int max)
    {
        SortedSet<Integer> prime_numbers = new TreeSet<>();
        ArrayList<Integer> lowest_delimeters = new ArrayList(max + 1);
        for (int i = 0; i <= max; i++)
        {
            lowest_delimeters.add(0);
        }

        for (int i = 2; i <= max; i++)
        {
            if (lowest_delimeters.get(i) == 0)
            {
                lowest_delimeters.set(i, i);
                prime_numbers.add(i);

                int multiplier = 2;
                while (i * multiplier <= max)
                {
                    int num = i * multiplier;
                    multiplier++;
                    if (lowest_delimeters.get(num) != 0 && num >= lowest_delimeters.get(num))
                    {
                        continue;
                    }
                    lowest_delimeters.set(num, i);
                }
            }
        }

        return prime_numbers;
    }
}
