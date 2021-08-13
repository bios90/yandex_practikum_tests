import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllTPossiblities2
{
    private static boolean initial_out = true;

    public static void main(String[] args) throws IOException
    {
        int[] input = getInput();
        String result = "";
        getPossibilieties(input, 0, result);
    }

    private static void getPossibilieties(int[] nums, int pos, String result)
    {
        if (pos == nums.length)
        {
            printResult(result);
        } else
        {
            String copy = String.valueOf(result.toCharArray());
            int num = nums[pos];
            String letters = getLettersForNumber(num);
            for (int i = 0; i < letters.length(); i++)
            {
                String str = copy + letters.charAt(i);
                getPossibilieties(nums, pos + 1, str);
            }
        }
    }

    private static int[] getInput() throws IOException
    {
//        return new int[]{2, 3};
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

    private static void printResult(String result)
    {
        StringBuilder out = new StringBuilder();
        if (!initial_out)
        {
            out.append(" ");
        }
        out.append(result);

        System.out.print(out.toString());
        initial_out = false;
    }
}
