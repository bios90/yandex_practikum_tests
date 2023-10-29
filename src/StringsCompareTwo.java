import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringsCompareTwo {
    private static char[] rightChars =
            new char[] {'b', 'd', 'f', 'h', 'j', 'l', 'n', 'p', 'r', 't', 'v', 'x', 'z'};
    private static Set<Integer> set;
    private static String str1;
    private static String str2;

    public static void main(String[] args) throws IOException {
        set = new HashSet<>();
        for (char c : rightChars) {
            set.add((int) c);
        }
        initFields();
        int result = 0;
        int diff = str1.compareTo(str2);
        if(diff > 0){
            result = 1;
        }else if(diff < 0){
            result = -1;
        }
        System.out.println(result);
    }

    private static void initFields() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int c;
        while ((c = reader.read()) != -1) {
            if (c == (char) '\n') {
                if (str1 != null) {
                    break;
                }
                str1 = builder.toString().trim();
                builder.setLength(0);
            } else {
                if (set.contains(c)) {
                    builder.append((char) c);
                }
            }
        }
        str2 = builder.toString().trim();
    }
}
