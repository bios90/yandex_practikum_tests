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

    private static ArrayList<ModelPoint> exitPoints = new ArrayList<>();
    private static ArrayList<ModelPoint> busPoints = new ArrayList<>();
    private static ModelSquare[][] squares;
    private static Map<ModelSquare, List<ModelSquare>> mapOfNeighbours = new HashMap();

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
        for (ModelPoint pointExit : exitPoints) {

            ModelSquare container = getSquareContainer(pointExit);
            List<ModelSquare> neighbours;

            if (mapOfNeighbours.containsKey(container)) {
                neighbours = mapOfNeighbours.get(container);
            } else {
                neighbours = getNearestSquares(container);
                mapOfNeighbours.put(container, neighbours);
            }

            int matchedCount = container.getStopsPoints().size();
            for (ModelSquare square : neighbours) {
                for (ModelPoint point : square.getStopsPoints()) {
                    if (areClose(point, pointExit)) {
                        matchedCount++;
                    }
                }
            }

            if (matchedCount > maxCount) {
                maxCount = matchedCount;
                bestExit = pointExit;
            }
        }

        System.out.println(bestExit.pos + 1);
    }

    private static List<ModelSquare> getNearestSquares(ModelSquare middle) {
        List<ModelSquare> nearest = new ArrayList<>();
        int middleY = middle.yIndex;
        int middleX = middle.xIndex;

        for (int[] poses : nearestPoses) {
            try {
                int posY = middleY + poses[0];
                int posX = middleX + poses[1];
                ModelSquare square = squares[posY][posX];
                nearest.add(square);
            } catch (IndexOutOfBoundsException e) {

            }
        }
        return nearest;
    }

    private static List<ModelSquare> getNearestSquares(ModelPoint point) {
        List<ModelSquare> nearest = new ArrayList<>();
        ModelSquare middle = getSquareContainer(point);
        int middleY = middle.yIndex;
        int middleX = middle.xIndex;
        nearest.add(middle);

        for (int[] poses : nearestPoses) {
            try {
                int posY = middleY + poses[0];
                int posX = middleX + poses[1];
                ModelSquare square = squares[posY][posX];
                nearest.add(square);
            } catch (IndexOutOfBoundsException e) {

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

        //        printSquares();
    }

    private static void fillSquares() {

        for (ModelPoint point : busPoints) {
            ModelSquare square = getSquareContainer(point);
            square.checkPointAndAddIfNeeded(point);
        }
    }

    static double getDistance(ModelPoint point1, ModelPoint point2) {
        int xDiff = point1.x - point2.x;
        int yDiff = point1.y - point2.y;
        int sum = (xDiff * xDiff) + (yDiff * yDiff);
        return Math.sqrt(sum);
    }

    static boolean areClose(ModelPoint point1, ModelPoint point2) {
        double diff = getDistance(point1, point2);
        return diff <= 20;
    }

    private static void printSquares() {
        StringBuilder out = new StringBuilder();
        for (int i = squares.length - 1; i >= 0; i--) {
            int y = squares[i][0].startY;
            String str = "|\n|" + y + "\n";
            if (i == 0) {
                ModelSquare[] row = squares[0];
                for (ModelSquare square : row) {
                    str += square.startX + "__";
                }
            }
            out.append(str);
        }

        System.out.println(out.toString());
    }

    private static void initFields() throws IOException {

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
        for (int i = 0; i < stopsCount; i++) {
            String[] numsStr = reader.readLine().split(" ");
            int x = Integer.parseInt(numsStr[0]);
            int y = Integer.parseInt(numsStr[1]);
            ModelPoint point = new ModelPoint(x, y, i);
            busPoints.add(point);

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
    }
}

class ModelSquare {
    int startX;
    int startY;
    int endX;
    int endY;
    int yIndex;
    int xIndex;

    private final Set<ModelPoint> stops = new HashSet<>();

    public ModelSquare(int startX, int startY, int yIndex, int xIndex) {
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.startX = startX;
        this.startY = startY;
        this.endX = startX + ClosestStop3.SQUARE_SIZE;
        this.endY = startY + ClosestStop3.SQUARE_SIZE;
    }

    public void checkPointAndAddIfNeeded(ModelPoint point) {
        boolean isXMatched = point.x >= startX && point.x < endX;
        boolean isYMatched = point.y >= startY && point.y < endY;
        if (isXMatched && isYMatched) {
            stops.add(point);
        }
    }

    public Set<ModelPoint> getStopsPoints() {
        return stops;
    }

    public String getSquareCoordinates() {
        String str = "";
        str += "(" + startX + "," + endY + ")----------(" + endX + "," + endY + ")\n";
        str +=
                "|                          |\n"
                        + "|                          |\n"
                        + "|                          |\n"
                        + "|                          |\n"
                        + "|                          |\n"
                        + "|                          |\n"
                        + "|                          |\n";
        str += "(" + startX + "," + startY + ")----------(" + endX + "," + startY + ")";
        return str;
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

    public String getAsString() {
        return "" + pos + " (" + x + "," + y + ")";
    }
}
