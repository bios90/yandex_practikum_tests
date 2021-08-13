import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllTPossibilitiesFinal
{
    private static boolean initial_out = true;

    public static void main(String[] args) throws IOException
    {
        initial_out = true;
        int[] input = getInput();
        int max = getCountFromInput(input);

        char[] chars = new char[max];
        String[] letters = getLettersForButtons(input);
        getPossibilities(chars, 0, input.length, letters);
    }


    private static void getPossibilities(char[] chars, int pos, int count, String[] letters)
    {
        if (pos == count)
        {
            printResult(chars);
            return;
        } else
        {
            String str = letters[pos];
            for (int i = 0; i < str.length(); i++)
            {
                chars[pos] = str.charAt(i);
                getPossibilities(chars, pos + 1, count, letters);
            }
        }
    }

    private static String[] getLettersForButtons(int[] input)
    {
        String[] letters = new String[input.length];
        for (int i = 0; i < letters.length; i++)
        {
            letters[i] = getLettersForNumber(input[i]);
        }
        return letters;
    }

    private static String getLettersForNumber(int num)
    {
        switch (num)
        {
            case 2:
            {
                return "abc";
            }
            case 3:
            {
                return "def";
            }
            case 4:
            {
                return "ghi";
            }
            case 5:
            {
                return "jkl";
            }
            case 6:
            {
                return "mno";
            }
            case 7:
            {
                return "pqrs";
            }
            case 8:
            {
                return "tuv";
            }
            case 9:
            {
                return "wxyz";
            }
            default:
                throw new RuntimeException("*** Error unknown number");

        }
    }

    private static int getCountFromInput(int[] input)
    {
        int count = 1;
        for (int num : input)
        {
            int size = getLettersForNumber(num).length();
            count *= size;
        }
        return count;
    }


    private static int[] getInput() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        int[] input = new int[line.length()];
        for (int i = 0; i < input.length; i++)
        {
            int num = Character.getNumericValue(line.charAt(i));
            input[i] = num;
        }

        return input;
    }

    private static void printResult(char[] chars)
    {
        StringBuilder out = new StringBuilder();
        if (!initial_out)
        {
            out.append(" ");
        }
        for (char c : chars)
        {
            out.append(c);
        }

        System.out.print(out.toString());
        initial_out = false;
    }
}
