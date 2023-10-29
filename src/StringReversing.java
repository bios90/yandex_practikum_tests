import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReversing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        StringBuilder outMain = new StringBuilder();
        StringBuilder outLocal = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if (c == ' ') {
                if (outLocal.length() > 0) {
                    outMain.append(outLocal.reverse()).append(' ');
                    outLocal.setLength(0);
                }
            } else {
                outLocal.append(c);
            }
        }
        if(outLocal.length() > 0){
            outMain.append(outLocal.reverse());
        }
        System.out.println(outMain.toString().trim());
    }
}
