import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StrangeComparisson {
    private static String str1;
    private static String str2;
    private static Map<Character, Character> map1;
    private static Map<Character, Character> map2;

    public static void main(String[] args) throws IOException {
        initFields();
        if (str1.equals(str2)) {
            printPositive();
            return;
        }
        if (str1.length() != str2.length()) {
            printNegative();
            return;
        }
        for (int i = 0; i < str1.length(); i++) {
            Character c1 = str1.charAt(i);
            Character c2 = str2.charAt(i);

            Character saved1 = map1.get(c1);
            if (saved1 != null && saved1 != c2) {
                printNegative();
                return;
            }

            Character saved2 = map2.get(c2);
            if (saved2 != null && saved2 != c1) {
                printNegative();
                return;
            }

            map1.put(c1, c2);
            map2.put(c2, c1);
        }

        printPositive();
    }

    private static void printPositive() {
        System.out.println("YES");
    }

    private static void printNegative() {
        System.out.println("NO");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str1 = reader.readLine();
        str2 = reader.readLine();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }
}
