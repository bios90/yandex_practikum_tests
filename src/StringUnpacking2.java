public class StringUnpacking2 {
    public static void main(String[] args) {
        tests();
    }


    private static void tests() {
        String unpacked = getPackedSubstringFrom(1,"3[atrrt4[t]]");
        System.out.println(unpacked);
    }

    private static String getPackedSubstringFrom(int pos, String packed) {
        StringBuilder out = new StringBuilder();
        int countOpened = 1;
        for (int i = pos + 1; i < packed.length(); i++) {
            char c = packed.charAt(i);
            if (c == '[') {
                countOpened++;
            } else if (c == ']') {
                countOpened--;
            }
            if (countOpened == 0) {
                return out.toString();
            }
            out.append(c);
        }
        throw new RuntimeException();
    }
}
