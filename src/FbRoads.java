import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FbRoads {

    public static final String PATH = "/Users/bios90/IdeaProjects/yandex_practikum_tests/src/inputTowns.txt";
    private static TownData[] inputData;

    public static void main(String[] args) throws IOException {
        initFields();
        for (int i = 0; i < inputData.length; i++) {
            TownData data = inputData[i];
            int[][] traffic = getTownPossibleData(data);
            if (traffic == null) {
                System.out.println("Case #" + (i + 1) + ": Impossible");
            } else {
                System.out.println("Case #" + (i + 1) + ": Possible");
                printArray(traffic);
            }
        }
    }

    private static int[][] getTownPossibleData(TownData townData) {
        int[][] traffic = new int[townData.nLines][townData.mColumns];
        for(int i = 0; i < traffic.length; i++){
            Arrays.fill(traffic[i],1);
        }
        int sideLength = townData.nLines + townData.mColumns - 1;
        int eachTime = townData.leftToRight / sideLength;
        if (eachTime > 1000 || eachTime == 0) {
            return null;
        }
        int additionalTime = 0;
        if (eachTime * sideLength < townData.leftToRight) {
            additionalTime = townData.leftToRight - (eachTime * sideLength);
        } else if (townData.leftToRight % eachTime != 0) {
            additionalTime = townData.leftToRight % eachTime;
        }


        for (int i = 0; i < townData.mColumns; i++) {
            traffic[0][i] = eachTime;
            if (i == 1) {
                traffic[0][i] = eachTime + additionalTime;
            }
        }
        for (int j = 1; j < townData.nLines; j++) {
            traffic[j][townData.mColumns - 1] = eachTime;
        }

        countRightToLeft(townData, traffic);

        return traffic;
    }

    private static void countRightToLeft(TownData townData, int[][] traffic) {
        int sideLength = townData.nLines + townData.mColumns - 1;
        int[] route = new int[sideLength];
        int rightSideSum = 0;
        for (int i = 0; i < townData.nLines; i++) {
            rightSideSum += traffic[i][townData.mColumns - 1];
            route[i] = traffic[i][townData.mColumns - 1];
        }

        int sumToFind = townData.rightToLeft - rightSideSum;
        int bottomLength = townData.mColumns - 1;
        int eachTime = sumToFind / bottomLength;
        int additional = 0;
        if (eachTime * bottomLength < sumToFind) {
            additional = sumToFind - (eachTime * bottomLength);
        } else if (sumToFind % eachTime != 0) {
            additional = sumToFind % eachTime;
        }

        for (int i = townData.mColumns - 2; i >= 0; i--) {
            traffic[townData.nLines - 1][i] = eachTime;
            if (i == 0) {
                traffic[townData.nLines - 1][i] = eachTime + additional;
            }
        }

//        route[0] = traffic[townData.nLines - 1][townData.mColumns - 1];
//        route[sideLength - 1] = traffic[0][0];
//        int sumToFind = (townData.rightToLeft - route[0]) - route[sideLength - 1];
//        int countForDividing = sideLength - 2;
//        int eachTime = sumToFind / countForDividing;
//        int additional = 0;
//        if (eachTime * sideLength < sumToFind) {
//            additional = sumToFind - (eachTime * sideLength);
//        } else if (sumToFind % eachTime != 0) {
//            additional = sumToFind % eachTime;
//        }
//
//        for (int i = townData.mColumns - 2; i >= 0; i--) {
//            traffic[townData.nLines - 1][i] = eachTime;
//            if (i == 0) {
//                traffic[townData.nLines - 1][i] = eachTime + additional;
//            }
//        }
//        for (int j = townData.nLines - 2; j > 0; j--) {
//            traffic[j][0] = eachTime;
//        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        int totalCount = Integer.parseInt(reader.readLine());
        inputData = new TownData[totalCount];
        for (int i = 0; i < totalCount; i++) {
            String[] nums = reader.readLine().split(" ");
            int n = Integer.parseInt(nums[0]);
            int m = Integer.parseInt(nums[1]);
            int a = Integer.parseInt(nums[2]);
            int b = Integer.parseInt(nums[3]);
            TownData data = new TownData(n, m, a, b);
            inputData[i] = data;
        }
    }

    private static void printArray(int[][] array) {
        int height = array.length;
        int width = array[0].length;
        StringBuilder output_buffer = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output_buffer.append(array[i][j]);
                if(j != width-1){
                    output_buffer.append(' ');
                }
            }
            output_buffer.append("\n");
        }
        System.out.println(output_buffer.toString().trim());
    }

    private static class TownData {
        private int nLines;
        private int mColumns;
        private int leftToRight;
        private int rightToLeft;

        public TownData(int nLines, int mColumns, int leftTopToRightBottomTime, int rightTopToLeftBottomTime) {
            this.nLines = nLines;
            this.mColumns = mColumns;
            this.leftToRight = leftTopToRightBottomTime;
            this.rightToLeft = rightTopToLeftBottomTime;
        }
    }
}
