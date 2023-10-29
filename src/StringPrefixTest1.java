public class StringPrefixTest1 {
    private static String str = "ГОГОЛЬ";

    public static void main(String[] args) {
        int[] prefixOverlaps = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            for (int k = i - 1; k > 0; k--) {
                String prefix = str.substring(0, k);
                String suffix = str.substring(i - k, i);
                if (prefix.equals(suffix)) {
                    prefixOverlaps[i-1] = k;
                    break;
                }
            }
        }

        printArray(prefixOverlaps);
    }

    private static void printArray(int[] nums) {
        StringBuilder out = new StringBuilder();
        for (int num : nums) {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}
