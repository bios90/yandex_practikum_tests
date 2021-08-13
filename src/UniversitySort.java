import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UniversitySort
{
    private static int[] student_universities_ids;
    private static int k;
    private static Map<Integer, Integer> university_popularity;

    public static void main(String[] args) throws IOException
    {
        initFields();

        int[] top_popularity = new int[k];
        for (int i = 0; i < student_universities_ids.length; i++)
        {
            int university = student_universities_ids[i];
            if (university_popularity.containsKey(university))
            {
                int popularity = university_popularity.get(university);
                university_popularity.put(university, popularity + 1);
            } else
            {
                university_popularity.put(university, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> sorted_list = new ArrayList<>(university_popularity.entrySet());
        sorted_list.sort(new Comparator<Map.Entry<Integer, Integer>>()
        {
            @Override
            public int compare(Map.Entry<Integer, Integer> integerIntegerEntry, Map.Entry<Integer, Integer> t1)
            {
                Map.Entry<Integer, Integer> entry_1 = integerIntegerEntry;
                Map.Entry<Integer, Integer> entry_2 = t1;

                if (entry_1.getValue().equals(entry_2.getValue()))
                {
                    return entry_2.getKey() - entry_1.getKey();
                } else
                {
                    return entry_1.getValue() - entry_2.getValue();
                }
            }
        });

        for (int i = top_popularity.length - 1; i >= 0; i--)
        {
            top_popularity[i] = sorted_list.get(sorted_list.size() - 1 - i).getKey();
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < top_popularity.length; i++)
        {
            out.append(top_popularity[i]).append(" ");
        }

        System.out.println(out.toString().trim());
    }


    private static void initFields() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        student_universities_ids = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++)
        {
            student_universities_ids[i] = Integer.parseInt(tokenizer.nextToken());
        }
        k = Integer.parseInt(reader.readLine());
        university_popularity = new HashMap<>();
    }
}
