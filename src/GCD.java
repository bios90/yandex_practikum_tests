public class GCD
{
    public static void main(String[] args)
    {
        int a = 24, b = 126;

        int gcd = findGcd(a, b);
        System.out.println("Gcd is "+gcd);
    }

    private static int findGcd(int a, int b)
    {
        if (b == 0)
        {
            return a;
        } else
        {
            return findGcd(b, a % b);
        }
    }
}
