import java.util.ArrayList;
import java.util.List;

public class MostNumsByOne {
    public static void main(String[] args) {
        int[] nums = new int[] {0, 3, 5, 6, 7, 9, 10, 14, 15, 16, 17};
        int start = 0;
        int end = 0;
        int prev = nums[0];

        int maxLength = 0;
        List<Integer> maxList =  new ArrayList();
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if(current - prev == 1){
                maxLength++;
                maxList.add(current);
            } else {
//                printList(nums);
            }
        }
    }

    public static void printList(List<Integer> nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }

        System.out.println(out.toString().trim());
    }
}
