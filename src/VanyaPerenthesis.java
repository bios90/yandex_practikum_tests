import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// работает, но медленно: тесты не проходит из-за тайм-аута

public class VanyaPerenthesis
{
    public static void main(String[] args)
    {
        List<String> strs = balancedParens(6);
        for(String str : strs)
        {
            System.out.println(str);
        }
    }

    public static List<String> balancedParens(int n)
    {
        String s = "";
        for (int i = 0; i < n; i++)
        {
            s += "()";
        }
        List<String> list = getPermutations(s);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            if (validParens(list.get(i)))
            {
                list2.add(list.get(i));
            }
        }
        Set<String> set = new HashSet<>(list2);
        list2.clear();
        list2.addAll(set);
        return list2;
    }

    private static List<String> getPermutations(String s)
    {
        if (s == null) return null;
        List<String> result = new ArrayList<String>();
        if (s.length() < 2)
        {
            result.add(s);
            return result;
        }
        int length = s.length();
        char current_char;
        for (int i = 0; i < length; i++)
        {
            current_char = s.charAt(i);
            String sub = s.substring(0, i) + s.substring(i + 1);
            List<String> sub_permutations = getPermutations(sub);
            for (String item : sub_permutations)
            {
                result.add(current_char + item);
            }
        }
        return result;
    }

    private static boolean validParens(String parens)
    {
        while (parens.contains("(") && parens.contains(")"))
        {
            parens = parens.replaceAll("\\([^()]*\\)", "");
            if (!parens.matches(".*\\(+.*\\)+.*"))
                break;
        }
        return !parens.contains("(") && !parens.contains(")");
    }
}