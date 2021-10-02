import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GreedySimilarSums2 {
    private static int overallSum;
    private static int halfSum;
    private static int[] nums;
    private static List<ArrayList<Combo>> combos;

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Combo> line = new ArrayList<>();
            if (i == 0) {
                Combo combo = new Combo(i, nums[i]);
                line.add(combo);
            } else {
                List<Combo> previousLine = combos.get(i - 1);
                for (Combo previous : previousLine) {
                    for (int j = previous.lastAdded + 1; j < nums.length; j++) {
                        int sum = previous.sum + nums[j];
                        if(sum > halfSum){
                            continue;
                        }
                        System.out.println("Checking i is "+i+" last added previous is "+previous.lastAdded+" and current adding "+j+" = "+nums[j]);
                        if (overallSum - sum == sum) {
                            System.out.println("True");
                            return;
                        }
                        line.add(new Combo(j, sum));
                    }
                }
            }

            combos.add(line);
        }

        System.out.println("False");
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        nums = new int[count];
        combos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            overallSum += num;
            nums[i] = num;
        }
        halfSum = overallSum / 2;
    }

    private static class Combo {
        int lastAdded;
        int sum;

        public Combo(int lastAdded, int sum) {
            this.lastAdded = lastAdded;
            this.sum = sum;
        }
    }
}
