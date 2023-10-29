import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringsBorderControl {
    private static String str1;
    private static String str2;

    public static void main(String[] args) throws IOException {
        initFields();

        if (Math.abs(str1.length() - str2.length()) > 1) {
            System.out.println("FAIL");
            return;
        }

        int posStr1 = 0;
        int posStr2 = 0;
        int numChanges = 0;
        while (posStr1 < str1.length() || posStr2 < str2.length()) {
            char c1 = 'c';
            char c2 = 'c';
            try {
                c1 = str1.charAt(posStr1);
                c2 = str2.charAt(posStr2);
                if (c1 == c2) {
                    posStr1++;
                    posStr2++;
                } else if (numChanges > 0) {
                    System.out.println("FAIL");
                    return;
                } else if (str1.charAt(posStr1 + 1) == str2.charAt(posStr2 + 1)) {
                    posStr1++;
                    posStr2++;
                    numChanges++;
                } else if (c1 == str2.charAt(posStr2 + 1)) {
                    posStr2++;
                    numChanges++;
                } else if (c2 == str1.charAt(posStr1 + 1)) {
                    posStr1++;
                    numChanges++;
                }
            } catch (Exception e) {
                if (numChanges == 0) {
                    System.out.println("OK");
                } else {
                    System.out.println("FAIL");
                }
                return;
            }
        }

        System.out.println("OK");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str1 = reader.readLine();
        str2 = reader.readLine();
    }
}
