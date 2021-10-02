import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CupDrawingWords {
    private static int pixelSize;
    private static int[][] fields;

    public static void main(String[] args) throws IOException {
        initFields();
        reformatFields();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            StringBuilder builder = new StringBuilder();
            builder.setLength(0);
            for (int j = 0; j < fields[i].length; j++) {
                builder.append(fields[i][j]);
                if (builder.length() == 8) {
                    String byteString = builder.toString();
                    if (!byteString.equals("00000000")) {
                        char c = (char) Integer.parseInt(byteString, 2);
                        out.append(c);
                        builder.setLength(0);
                    }
                }
            }
        }
        String result = out.toString();
        System.out.println(result);
    }

    private static void reformatFields() {
        if (pixelSize > 1) {
            int widthNew = fields[0].length / pixelSize;
            int heightNew = fields.length / pixelSize;
            int[][] newFields = new int[heightNew][widthNew];

            for (int i = 0; i < fields.length; i += pixelSize) {
                for (int j = 0; j < fields[i].length; j += pixelSize) {
                    newFields[i / pixelSize][j / pixelSize] = fields[i][j];
                }
            }
            fields = newFields;
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split(" ");
        int height = Integer.parseInt(nums[0]);
        int width = Integer.parseInt(nums[1]);
        pixelSize = Integer.parseInt(nums[2]);
        fields = new int[height][width];
        for (int i = 0; i < height; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < width; j++) {
                int num = strToNum(tokenizer.nextToken()) >= 10 ? 0 : 1;
                fields[i][j] = num;
            }
        }
    }

    private static int strToNum(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += Character.getNumericValue(str.charAt(i));
        }
        return sum;
    }
}
