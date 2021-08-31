import javax.imageio.IIOException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class ClosestStop2 {
    private static ArrayList<ModelPoint> exitPoints = new ArrayList<>();
    private static TreeMap<Integer, ArrayList<ModelPoint>> stopPoints = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        ModelPoint point1 = new ModelPoint(1000, 1000, 1);
        ModelPoint point2 = new ModelPoint(1001, 1001, 2);
//        System.out.println("Point1 " + point1.getAsString());
//        System.out.println("Point2 " + point2.getAsString());
        //                System.out.println("Real diff is " + getDistance(point1, point2));
        //                System.out.println("Hash diffes " + (point1.hash - point2.hash));

        initFields();
        int maxMatched = -1;
        ModelPoint bestExitPont = null;

        for (ModelPoint pointExit : exitPoints) {
//            int mathced = 0;
//            int hash = pointExit.hash;
//
//            int diff = 1100;
//            System.out.println("For point " + pointExit.getAsString());
//            System.out.println("Found matched stops:");
//            List<ModelPoint> matchedPoints = new ArrayList<>();
//            stopPoints.subMap(hash - diff, hash + diff).values().forEach(matchedPoints::addAll);
//            for (ModelPoint stopPoint : matchedPoints) {
//                String str =
//                        stopPoint.getAsString()
//                                + " diff real is "
//                                + ClosestStop3.getDistance(pointExit, stopPoint)
//                                + " and hashes diffs are ";
//                System.out.println(str);
//                if (ClosestStop3.areClose(pointExit, stopPoint)) {
//                    mathced++;
//                }
//            }
//            System.out.println("Real matched counts are " + mathced);
//            System.out.println("********************************");

            //            for (int i = hash - diff; i <= hash + diff; i++) {
            //                if (stopPoints.containsKey(i)) {
            //                    for (ModelPoint pointStop : stopPoints.get(i)) {
            //                        if (areClose(pointExit, pointStop)) {
            //                            mathced++;
            //                        }
            //                    }
            //                }
            //            }

//            if (mathced > maxMatched) {
//                maxMatched = mathced;
//                bestExitPont = pointExit;
//            } else if (mathced == maxMatched && pointExit.pos < bestExitPont.pos) {
//                bestExitPont = pointExit;
//            }
        }

        System.out.println((bestExitPont.pos + 1));

        //        ArrayList<ModelPoint> closest = getClosestToPoint(bestExitPont);
        //        System.out.println("For point ");
        //        System.out.println(bestExitPont.getAsString());
        //        System.out.println("Closest are:");
        //        for (ModelPoint point : closest) {
        //            int hashDiffes = bestExitPont.hash - point.hash;
        //            String text =
        //                    point.getAsString()
        //                            + " and Diff - "
        //                            + getDistance(bestExitPont, point)
        //                            + " and hash diffes is "
        //                            + hashDiffes;
        //            System.out.println(text);
        //        }
        //        System.out.println("********************");

        ////////////////////////

        //        for (Map.Entry<Integer, ModelPoint> points : exitPoints.entrySet()) {
        //            ModelPoint pointExit = points.getValue();
        //
        //            if (pointExit.pos == 3 || pointExit.pos == 6) {
        //                ArrayList<ModelPoint> closest = getClosestToPoint(pointExit);
        //                System.out.println("For point ");
        //                System.out.println(pointExit.getAsString());
        //                System.out.println("Closest are:");
        //                for (ModelPoint point : closest) {
        //                    String text =
        //                            point.getAsString() + " and Diff - " + getDistance(pointExit,
        // point);
        //                    System.out.println(text);
        //                }
        //                System.out.println("********************");
        //            }
        //        }
    }

    private static void initFields() throws IOException {
        StringBuilder out = new StringBuilder();

        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int exitsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < exitsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            int x = Integer.parseInt(numsStr[0]);
            int y = Integer.parseInt(numsStr[1]);
            ModelPoint point = new ModelPoint(x, y, i);
            exitPoints.add(point);

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
        out.append("11111111111111111111111111111").append("\n");
        int stopsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < stopsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            int x = Integer.parseInt(numsStr[0]);
            int y = Integer.parseInt(numsStr[1]);
            ModelPoint point = new ModelPoint(x, y, i);
//            if (stopPoints.containsKey(point.hash)) {
//                ArrayList<ModelPoint> currentPoints = stopPoints.get(point.hash);
//                currentPoints.add(point);
//            } else {
//                ArrayList<ModelPoint> currentPoints = new ArrayList<>();
//                currentPoints.add(point);
////                stopPoints.put(point.hash, currentPoints);
//            }

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        System.out.println("X are (" + minX + "," + maxX + ")");
        System.out.println("Y are (" + minY + "," + maxY + ")");
        out.append("22222222222222222222222222222").append("\n");
        //                System.out.println(out.toString());
    }

    private static ArrayList<ModelPoint> getClosestToPoint(ModelPoint pointExit) {
        ArrayList<ModelPoint> points = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<ModelPoint>> entry : stopPoints.entrySet()) {

            ArrayList<ModelPoint> pointsToCheck = entry.getValue();
            for (ModelPoint pointStop : pointsToCheck) {
//                double distance = ClosestStop3.getDistance(pointStop, pointExit);
//                if (distance <= 20) {
//                    points.add(pointStop);
//                }
            }
        }
        return points;
    }



    private static boolean areCloseQuick(ModelPoint point1, ModelPoint point2) {
        int diffX = Math.abs(point1.x - point2.x);
        int diffy = Math.abs(point1.y - point2.y);
        return diffX < 21 && diffy < 21;
    }
}


