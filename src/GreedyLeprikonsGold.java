import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyLeprikonsGold {
    private static int maxWeight;
    private static int[] golds;
    private static int[][] priceAndWeights;

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < priceAndWeights.length; i++) {
            int goldWeight = golds[i];
            int[] line = priceAndWeights[i];
            for (int j = 1; j < line.length; j++) {
                int thisPieceWeight = j >= goldWeight ? goldWeight : 0;
                int diff = j - goldWeight;
                if (diff < 0) {
                    diff = 0;
                }
                int weightFromPreviousDiffed = getFromPreviousLine(i, diff);
                int weightFromPreviousSame = getFromPreviousLine(i, j);
                int finalWeight = Math.max(weightFromPreviousSame, weightFromPreviousDiffed + thisPieceWeight);
                priceAndWeights[i][j] = finalWeight;
            }
        }
        int max = priceAndWeights[priceAndWeights.length - 1][priceAndWeights[0].length - 1];
        System.out.println(max);
    }

    private static int getFromPreviousLine(int line, int pos) {
        if (line == 0) {
            return 0;
        }
        return priceAndWeights[line - 1][pos];
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numStr = reader.readLine().split(" ");
        int goldsCount = Integer.parseInt(numStr[0]);
        maxWeight = Integer.parseInt(numStr[1])+1;

        golds = new int[goldsCount];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < goldsCount; i++) {
            golds[i] = Integer.parseInt(tokenizer.nextToken());
        }

        priceAndWeights = new int[goldsCount][maxWeight];
    }
}
