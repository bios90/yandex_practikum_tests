public class ToBinaryStringConverter
{
    public static void main(String[] args)
    {
        String binary = convertToBinary(4);
        System.out.println(binary);
    }

    private static String convertToBinary(int num)
    {
        if (num == 0 || num == 1)
        {
            return String.valueOf(num);
        }

        int digit = num % 2;
        return convertToBinary(num / 2) + digit;
    }
}
