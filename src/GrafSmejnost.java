import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GrafSmejnost {
    private static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder builder = new StringBuilder();
        for (List<Integer> nums : nodes) {
            if (nums == null || nums.size() == 0) {
                builder.append("0").append("\n");
            } else {
                builder.append(nums.size()).append(" ");
                for (int i = 0; i < nums.size() - 1; i++) {
                    builder.append(nums.get(i));
                    builder.append(" ");
                }
                builder.append(nums.get(nums.size() - 1)).append("\n");
            }
        }
        System.out.println(builder.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int countNodes = Integer.parseInt(line[0]);
        int countLines = Integer.parseInt(line[1]);

        nodes = new ArrayList[countNodes];
        for (int i = 0; i < countLines; i++) {
            String[] lineNumsStr = reader.readLine().split(" ");
            int nodeFrom = Integer.parseInt(lineNumsStr[0]);
            int nodeTo = Integer.parseInt(lineNumsStr[1]);

            List<Integer> nums;
            if (nodes[nodeFrom - 1] != null) {
                nums = nodes[nodeFrom - 1];
            } else {
                nums = new ArrayList<>();
                nodes[nodeFrom - 1] = nums;
            }
            nums.add(nodeTo);
        }
    }
}
