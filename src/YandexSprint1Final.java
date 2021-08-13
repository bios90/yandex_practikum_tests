////Id решения на контесте 51991319
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.lang.StringBuilder;
//import java.util.*;
//
//public class NearestZero
//{
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        int length = Integer.parseInt(reader.readLine());
//        int[] houses = getNumsFromReader(reader, length);
//        ArrayList<Integer> free_house_poses = new ArrayList();
//        for(int i = 0; i < houses.length; i++)
//        {
//            if(houses[i] == 0)
//            {
//                free_house_poses.add(i);
//            }
//        }
//
//        int right_index = 0;
//        Integer free_left = null;
//        Integer free_right = free_house_poses.get(0);
//
//        for(int i = 0; i < houses.length; i++)
//        {
//            if(houses[i] == 0)
//            {
//                free_left = free_right;
//                free_right = getNumOrNull(free_house_poses,++right_index);
//            }
//            else
//            {
//                if(free_left == null)
//                {
//                    houses[i] = free_right - i;
//                }
//                else if(free_right == null)
//                {
//                    houses[i] = i - free_left;
//                }
//                else
//                {
//                    houses[i] = Math.min(i - free_left,free_right - i);
//                }
//            }
//        }
//
//        printResult(houses);
//    }
//
//    private static int[] getNumsFromReader(BufferedReader reader, int length) throws IOException
//    {
//        int[] nums = new int[length];
//        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
//        for(int i = 0; i < length; i++)
//        {
//            nums[i] = Integer.parseInt(tokenizer.nextToken());
//        }
//        return nums;
//    }
//
//    private static Integer getNumOrNull(List<Integer> list, int index)
//    {
//        try
//        {
//            return list.get(index);
//        }
//        catch(IndexOutOfBoundsException e)
//        {
//            return null;
//        }
//    }
//
//    private static void printResult(int[] houses)
//    {
//        StringBuilder output_buffer = new StringBuilder();
//        for (int i = 0; i < houses.length; i++)
//        {
//            output_buffer.append(houses[i]);
//            output_buffer.append(" ");
//        }
//        System.out.println(output_buffer.toString().trim());
//    }
//}
//
////Id решения на контесте 51996990
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.lang.StringBuilder;
//import java.util.*;
//
//public class HandsAgility
//{
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        int touch_count = Integer.parseInt(reader.readLine()) * 2;
//        int[] nums_count = getNumsCounts(reader);
//        int success_count = 0;
//        for(int i = 0; i < 10; i++)
//        {
//            int num_count = nums_count[i];
//            if(num_count != 0 && touch_count >= num_count)
//            {
//                success_count++;
//            }
//        }
//        System.out.println(success_count);
//    }
//
//    private static int[] getNumsCounts(BufferedReader reader) throws IOException
//    {
//        int[] nums_count = new int[10];
//        for(int i = 0; i < 4; i++)
//        {
//            String str = reader.readLine();
//            for(int j = 0; j < 4; j++)
//            {
//                char c = str.charAt(j);
//                if(c != '.')
//                {
//                    int num = c - '0';
//                    nums_count[num] = nums_count[num] + 1;
//                }
//            }
//        }
//        return nums_count;
//    }
//}
