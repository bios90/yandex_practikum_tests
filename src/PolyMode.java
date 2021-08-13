import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolyMode {
    private static int a;
    private static long m;
    private static String str;

    public static void main(String[] args) throws IOException {
        initFields();
        //        System.out.println(getHashFinal());
        if (str.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(getHashV2(str));
        }
    }

    private static long getHashV2(String str) {
        long aModded = a % m;
        long sum = (long) str.charAt(0) % m;
        for (int i = 1; i < str.length(); i++) {
            sum = ((sum % m) * aModded) % m;
            sum = (sum % m) + (str.charAt(i) % m);
        }
        return sum % m;
    }

    private static long multiplyWithMod(long i, long j, long mod) {
        return ((i % mod) * (j % mod)) % mod;
    }

    private static long addWithMod(long i, long j, long mod) {
        return ((i % mod) + (j % mod));
    }

    static long getHashFinal() {
        long sum = 0;
        long a_powed = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            long c = str.charAt(i) % m;
            sum += (c * a_powed) % m;
            a_powed = (a_powed * (a % m)) % m;
        }

        return sum % m;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(reader.readLine());
        m = Long.parseLong(reader.readLine());
        str = reader.readLine();
    }
}
