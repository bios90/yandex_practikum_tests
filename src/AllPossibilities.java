public class AllPossibilities
{
    public static void main(String[] args)
    {
        int count = 3;
        double max = Math.pow((double) count, 2.0d);
        char[] chars = new char[(int) max];
        getPossibilities(chars, 0, count);
    }

    private static void getPossibilities(char[] chars, int pos, int count)
    {
        if (pos == count)
        {
            printChars(chars);
        } else
        {
            chars[pos] = '0';
            getPossibilities(chars, pos + 1, count);

            chars[pos] = '1';
            getPossibilities(chars, pos + 1, count);
        }
    }

    private static void printChars(char[] chars)
    {
        StringBuilder out = new StringBuilder();
        for (char c : chars)
        {
            out.append(c);
        }
        System.out.println(out.toString());
    }


}
