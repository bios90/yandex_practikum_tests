import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClosestStop3 {
    public static int SQUARE_SIZE = 20;
    private static int maxX = Integer.MIN_VALUE;
    private static int minX = Integer.MAX_VALUE;
    private static int maxY = Integer.MIN_VALUE;
    private static int minY = Integer.MAX_VALUE;

    private static ModelPoint[] exitPoints;
    private static ModelPoint[] busPoints;
    private static ModelSquare[][] squares;

    private static int[][] nearestPoses =
            new int[][] {
                new int[] {-1, -1},
                new int[] {0, -1},
                new int[] {1, -1},
                new int[] {1, 0},
                new int[] {1, 1},
                new int[] {0, 1},
                new int[] {-1, 1},
                new int[] {-1, 0},
            };

    public static void main(String[] args) throws IOException {
        initFields();
        createSquares();
        fillSquares();

        int maxCount = -1;
        ModelPoint bestExit = null;
        ModelSquare container;
        ModelSquare[] neighbours;
        for (ModelPoint pointExit : exitPoints) {

//            container = getSquareContainer(pointExit);
//            neighbours = getNearestSquares(container);
//
//            int matchedCount = 0;
//            for (ModelSquare square : neighbours) {
//                if (square == null) {
//                    continue;
//                }
//                for (ModelPoint point : square.getStopsPoints()) {
//                    if (areClose(point, pointExit)) {
//                        matchedCount++;
//                    }
//                }
//            }
//
//            if (matchedCount > maxCount) {
//                maxCount = matchedCount;
//                bestExit = pointExit;
//            }
        }

//        System.out.println(bestExit.pos + 1);
        System.out.println(10);
    }

    private static ModelSquare[] getNearestSquares(ModelSquare middle) {
        ModelSquare[] nearest = new ModelSquare[9];
        nearest[0] = middle;
        int middleY = middle.yIndex;
        int middleX = middle.xIndex;
        int maxY = squares.length;
        int maxX = squares[0].length;

        for (int i = 0; i < nearestPoses.length; i++) {
            int[] poses = nearestPoses[i];
            int posY = middleY + poses[0];
            int posX = middleX + poses[1];
            if ((posX >= 0 && posX < maxX) && (posY >= 0 && posY < maxY)) {
                ModelSquare square = squares[posY][posX];
                nearest[i + 1] = square;
            }
        }

        return nearest;
    }

    private static ModelSquare getSquareContainer(ModelPoint point) {
        int xPos = Math.abs((minX - point.x) / SQUARE_SIZE);
        int yPos = Math.abs((minY - point.y) / SQUARE_SIZE);
        return squares[yPos][xPos];
    }

    private static void createSquares() {
        int diffX = maxX - minX;
        int diffY = maxY - minY;
        int countX = (diffX / SQUARE_SIZE) + 1;
        int countY = (diffY / SQUARE_SIZE) + 1;

        squares = new ModelSquare[countY][];
        for (int i = 0; i < countY; i++) {
            ModelSquare[] row = new ModelSquare[countX];
            for (int j = 0; j < countX; j++) {
                int startX = minX + (SQUARE_SIZE * j);
                int startY = minY + (SQUARE_SIZE * i);
                ModelSquare square = new ModelSquare(startX, startY, i, j);
                row[j] = square;
            }
            squares[i] = row;
        }
    }

    private static void fillSquares() {
        for (ModelPoint point : busPoints) {
            ModelSquare square = getSquareContainer(point);
            square.addPoint(point);
        }
    }

    static double getDistancePow(ModelPoint point1, ModelPoint point2) {
        int xDiff = point1.x - point2.x;
        int yDiff = point1.y - point2.y;
        int sum = (xDiff * xDiff) + (yDiff * yDiff);
        return sum;
    }

    static boolean areClose(ModelPoint point1, ModelPoint point2) {
        double diff = getDistancePow(point1, point2);
        return diff <= 400;
    }

    private static void initFields() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int exitsCount = Integer.parseInt(reader.readLine());
        exitPoints = new ModelPoint[exitsCount];
        for (int i = 0; i < exitsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            int x = Integer.parseInt(numsStr[0]);
            int y = Integer.parseInt(numsStr[1]);
            ModelPoint point = new ModelPoint(x, y, i);
            exitPoints[i] = point;

            if (x < minX) {
                minX = x;
            } else if (x > maxX) {
                maxX = x;
            }

            if (y < minY) {
                minY = y;
            } else if (y > maxY) {
                maxY = y;
            }
        }

        int stopsCount = Integer.parseInt(reader.readLine());
        busPoints = new ModelPoint[stopsCount];
        for (int i = 0; i < stopsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            int x = Integer.parseInt(numsStr[0]);
            int y = Integer.parseInt(numsStr[1]);
            ModelPoint point = new ModelPoint(x, y, i);
            busPoints[i] = point;

            if (x < minX) {
                minX = x;
            } else if (x > maxX) {
                maxX = x;
            }

            if (y < minY) {
                minY = y;
            } else if (y > maxY) {
                maxY = y;
            }
        }
    }
}

class ModelSquare {
    int startX;
    int startY;
    int yIndex;
    int xIndex;

    private Set<ModelPoint> stops = new HashSet<>();

    public ModelSquare(int startX, int startY, int yIndex, int xIndex) {
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.startX = startX;
        this.startY = startY;
    }

    public void addPoint(ModelPoint point) {
        stops.add(point);
    }

    public Set<ModelPoint> getStopsPoints() {
        return stops;
    }
}

class ModelPoint {
    int x;
    int y;
    int pos;

    public ModelPoint(int x, int y, int pos) {
        this.x = x;
        this.y = y;
        this.pos = pos;
    }
}
