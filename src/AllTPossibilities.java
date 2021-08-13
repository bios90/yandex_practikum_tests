import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllTPossibilities
{
    private static char[][] letters;
    private static String[] results;
    private static int result_index = 0;

    public static void main(String[] args) throws IOException
    {
        if (letters == null)
        {
            fillLetters();
        }

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String line = reader.readLine();
//
//        int[] input = new int[line.length()];
//        for (int i = 0; i < input.length; i++)
//        {
//            int num = line.charAt(i) - '0';
//            input[i] = num;
//        }

        int[] input = new int[]{2,3};

        int max = getCountFromInput(input);
        results = new String[max];
        result_index = 0;
        char[] chars = new char[max];
        char[][] lets = getLettersForButtons(input);
        getPossibilities(chars, 0, input.length, lets);
        printFinalResult();
    }

    private static void getPossibilities(char[] chars, int pos, int count, char[][] lets)
    {
        if (pos == count)
        {
            addResult(chars);
        } else
        {
            char[] number_letters = lets[pos];
            for (int i = 0; i < number_letters.length; i++)
            {
                chars[pos] = number_letters[i];
                getPossibilities(chars, pos + 1, count, lets);
            }
        }
    }

    private static void fillLetters()
    {
        letters = new char[8][];
        letters[0] = new char[]{'a', 'b', 'c'};
        letters[1] = new char[]{'d', 'e', 'f'};
        letters[2] = new char[]{'g', 'h', 'i'};
        letters[3] = new char[]{'j', 'k', 'l'};
        letters[4] = new char[]{'m', 'n', 'o'};
        letters[5] = new char[]{'p', 'q', 'r', 's'};
        letters[6] = new char[]{'t', 'u', 'v'};
        letters[7] = new char[]{'w', 'x', 'y', 'z'};
    }

    private static int getCountFromInput(int[] input)
    {
        int count = 1;
        for (int num : input)
        {
            int chars_size = letters[num - 2].length;
            count *= chars_size;
        }
        return count;
    }

    private static char[][] getLettersForButtons(int[] input)
    {
        char[][] l = new char[input.length][];
        for (int i = 0; i < input.length; i++)
        {
            l[i] = letters[input[i] - 2];
        }
        return l;
    }

    private static void printFinalResult()
    {
        StringBuilder out = new StringBuilder();
        for (String str : results)
        {
            out.append(str).append(" ");
        }
        System.out.print(out.toString().trim());
    }

    private static void addResult(char[] chars)
    {
        StringBuilder out = new StringBuilder();
        for (char c : chars)
        {
            out.append(String.valueOf(c));
        }
        results[result_index++] = out.toString();
    }
}
