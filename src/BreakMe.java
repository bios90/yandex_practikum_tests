import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class BreakMe {
    private static int a = 1000;
    private static int m = 123987123;
    private static Map<Long, String> hashes = new HashMap<>();
    private static Random rand = new Random();
    private static char[] allChars =
            new char[] {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            String str = checkRandom(10);
            if (str != null) {
                break;
            }
        }
    }

    private static String checkRandom(int length) {
        String str = getRandomString(length);
        long hash = getHashFinal(str);
        String alreadySaved = hashes.get(hash);
        if (alreadySaved != null && !str.equals(alreadySaved)) {
            System.out.println(
                    "Yeah found hash(" + hash + ") equals " + str + " --- " + alreadySaved);
            return str;
        }
        hashes.put(hash, str);

        return null;
    }

    private static String getRandomString(int length) {
        StringBuilder out = new StringBuilder();
        int arrLength = allChars.length;
        while (length > 0) {
            out.append(allChars[rand.nextInt(arrLength)]);
            length--;
        }
        return out.toString();
    }

    private static void checkRecursevly(int pos, String str) {

        if (str.length() > 4) {
            return;
        }

        String copy = str;
        for (int i = 0; i < allChars.length; i++) {
            String temp = copy + allChars[i];
            long hash = getHashFinal(temp);
            String alreadyContains = hashes.get(hash);
            if (alreadyContains != null) {
                System.out.println(
                        "Found!!! "
                                + hash
                                + " And str is "
                                + str
                                + " and copy is "
                                + alreadyContains);
                return;
            }
            hashes.put(hash, temp);
            checkRecursevly(pos + 1, temp);
        }
    }

    static Long getHashFinal(String str) {
        long sum = 0;
        long a_powed = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            long c = str.charAt(i) % m;
            sum += (c * a_powed) % m;
            a_powed = (a_powed * (a % m)) % m;
        }

        return sum % m;
    }
}
