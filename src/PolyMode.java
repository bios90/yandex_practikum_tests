import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolyMode {
    private static int base;
    private static long mode;
    private static String str;

    public static void main(String[] args) throws IOException {
        initFields();
        System.out.println(getHashV3(str));
    }

    private static long getHashV3(String str) {
        long aModded = base % mode;
        long hash = 0;
        for (char c : str.toCharArray()) {
            hash = (hash * aModded + c) % mode;
        }
        return hash;
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        base = Integer.parseInt(reader.readLine());
        mode = Long.parseLong(reader.readLine());
        str = reader.readLine();
    }
}
