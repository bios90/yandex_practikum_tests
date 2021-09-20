public class GreedyNopNoRecursion {
    private static int[][] nopCounts;
    private static String str1 = "bttad";
    private static String str2 = "atttba";

    public static void main(String[] args) {
        nopCounts = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            char c1 = str1.charAt(i - 1);
            for (int j = 1; j <= str2.length(); j++) {
                char c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    nopCounts[i][j] = nopCounts[i - 1][j - 1] + 1;
                } else {
                    int left = nopCounts[i][j - 1];
                    int top = nopCounts[i - 1][j];
                    nopCounts[i][j] = Math.max(left, top);
                }
            }
        }
        System.out.println(nopCounts[str1.length()][str2.length()]);
        System.out.println(getResultStr());
    }

    private static String getResultStr() {
        StringBuilder answer = new StringBuilder();
        int i = nopCounts.length - 1;
        int j = nopCounts[0].length - 1;

        while (i != 0 && j != 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                answer.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (nopCounts[i][j] == nopCounts[i - 1][j]) {
                i--;
            } else {
                j--;
            }
        }

        return answer.reverse().toString().trim();
    }
}
