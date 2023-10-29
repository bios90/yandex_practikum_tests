import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class StringInserter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder(1000000);
        out.append(reader.readLine());

        Map<Integer, String> wordsToInsert = new TreeMap<>();
        int wordsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < wordsCount; i++) {
            String[] strs = reader.readLine().split(" ");
            int pos = Integer.parseInt(strs[1]);
            wordsToInsert.put(pos, strs[0]);
        }

        int offset = 0;

        Iterator<Map.Entry<Integer, String>> it = wordsToInsert.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            Integer pos = entry.getKey();
            String word = entry.getValue();

            out.insert(offset + pos, word);
            offset += word.length();
        }
        System.out.println(out.toString().trim());
    }
}
