import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyTimetable {
    private static Event[] timeTable;

    public static void main(String[] args) throws IOException {
        initFields();
        Arrays.sort(timeTable);
        List<Event> results = new ArrayList<>();
        double endTime = Integer.MIN_VALUE;
        for (Event e : timeTable) {
            if (e.timeStart.doubleValue() >= endTime) {
                results.add(e);
                endTime = e.timeEnd.doubleValue();
            }
        }
        StringBuilder out = new StringBuilder();
        out.append(results.size()).append("\n");
        for (Event e : results) {
            out.append(e.timeStart).append(" ").append(e.timeEnd).append("\n");
        }
        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        timeTable = new Event[count];
        for (int i = 0; i < count; i++) {
            String[] nums = reader.readLine().split(" ");
            Number timeStart = getNumberFromStr(nums[0]);
            Number timeEnd = getNumberFromStr(nums[1]);
            timeTable[i] = new Event(timeStart, timeEnd);
        }
    }

    private static Number getNumberFromStr(String str) {
        if (str.contains(".")) {
            return Double.parseDouble(str);
        } else {
            return Integer.parseInt(str);
        }
    }

    private static class Event implements Comparable<Event> {
        Number timeStart;
        Number timeEnd;

        public Event(Number timeStart, Number timeEnd) {
            this.timeStart = timeStart;
            this.timeEnd = timeEnd;
        }

        @Override
        public int compareTo(Event o) {
            double d1 = this.timeEnd.doubleValue();
            double d2 = o.timeEnd.doubleValue();
            if (d1 > d2) {
                return 1;
            } else if (d1 < d2) {
                return -1;
            } else {
                return Double.compare(this.timeStart.doubleValue(), o.timeStart.doubleValue());
            }
        }
    }
}
