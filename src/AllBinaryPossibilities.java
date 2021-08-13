public class AllBinaryPossibilities
{
    public static void main(String[] args)
    {
        generate("",4);
    }

    private static void generate(String str, int n)
    {
        if(n == 0)
        {
            System.out.println(str);
        }
        else
            {
                generate(str+"0",n-1);
                generate(str+"1",n-1);
            }
    }
}
