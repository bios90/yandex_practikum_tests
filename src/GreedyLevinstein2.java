import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GreedyLevinstein2 {
    private static String str1;
    private static String str2;

    public static void main(String[] args) throws IOException {
        initFields();
        String strMax = str1.length() >= str2.length() ? str1 : str2;
        String strMin = str1.length() >= str2.length() ? str2 : str1;

        int changes = 0;
        int indexMin = 0;
        int indexMax = 0;
        while (indexMin < strMin.length() && indexMax < strMax.length()) {
            char max = strMax.charAt(indexMax);
            char min = strMin.charAt(indexMin);
            if (max == min) {
                indexMax++;
                indexMin++;
            } else {
                int nextMax = indexMax + 1;
                int nextMin = indexMin + 1;
                if (nextMax < (strMax.length() - 1) && strMax.charAt(nextMax) == min){
                    indexMax++;
                } else if(nextMin < (strMin.length() -1) && strMin.charAt(nextMin) == max ){
                    indexMin++;
                }else{
                    indexMax++;
                    indexMin++;
                }

                changes++;
            }
        }
        System.out.println(changes);
    }

    public static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str1 = reader.readLine();
        str2 = reader.readLine();
    }
}
