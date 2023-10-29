import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class StringInserter2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] words = new String[1000000];

        int wordsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < wordsCount; i++) {
            String[] strs = reader.readLine().split(" ");
            int pos = Integer.parseInt(strs[1]);
            words[pos] = strs[0];
        }

        StringBuilder out = new StringBuilder(1000000);
        for (int i = 0; i < line.length()+1; i++) {
            if (words[i] != null) {
                out.append(words[i]);
            }
            if( i < line.length()){
                out.append(line.charAt(i));
            }

        }
        System.out.println(out.toString().trim());
    }
}
