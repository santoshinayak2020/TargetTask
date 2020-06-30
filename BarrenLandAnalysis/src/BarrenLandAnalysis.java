

import java.util.*;
import java.util.stream.Collectors;


public class BarrenLandAnalysis {

    private int[][] matrix;

    private int ROWS;
    private int COLS;
    private List<int[]> barrenLandRectangles;


  
    private class Tuple {

        private int x;
        private int y;

        Tuple(int x, int y) {
            assert (x >= 0 && x < ROWS) && y >= 0 && y < COLS : String.format("(%s,%s) value should not be greater than (%s,%s) ", x, y, ROWS - 1, COLS - 1);
            this.x = x;
            this.y = y;
        }

        boolean canFormValidRectangle(Tuple t2) {
            assert (t2.x - this.x >= 1 && t2.y - this.y >= 1) : String.format("Can not form a rectangle with %s, %s", this, t2);
            return true;
        }

        @Override
        public String toString() {
            return "(" + "x=" + x + ", y=" + y + ')';
        }
    }



    private BarrenLandAnalysis(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        this.matrix = new int[rows][cols];
        this.barrenLandRectangles = new ArrayList<>();
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }
    }

    BarrenLandAnalysis(int rows, int cols, String barrenLandRectanglesInputString) {

        this(rows, cols);
        this.processInputString(barrenLandRectanglesInputString);
        if (barrenLandRectangles != null)
            for (int[] barrenLand : barrenLandRectangles) {
                Tuple t1 = new Tuple(barrenLand[0], barrenLand[1]);
                Tuple t2 = new Tuple(barrenLand[2], barrenLand[3]);

                t1.canFormValidRectangle(t2);

                markBarrenLand(t1, t2);
            }
    }


    private void markBarrenLand(Tuple t1, Tuple t2) {
        for (int i = t1.x; i <= t2.x; i++) {
            for (int j = t1.y; j <= t2.y; j++)
                this.matrix[i][j] = 0;
        }
    }

  

    public List<Integer> findFertileLand() {

        Map<Integer, Integer> landAreas = new HashMap<>();
        Deque<Tuple> queue = new ArrayDeque<>();

        int fertileLandRegion = 1;
        int i = 0, j = 0;
        while (i < ROWS && j < COLS) {

            if (!queue.isEmpty()) {
                Tuple t = queue.poll();
                if (this.matrix[t.x][t.y] == 1) {
                    this.matrix[t.x][t.y] = fertileLandRegion;
                    landAreas.put(fertileLandRegion, landAreas.get(fertileLandRegion) + 1);

                    if (t.x < ROWS - 1)
                        queue.offer(new Tuple(t.x + 1, t.y));
                    if (t.x > 0)
                        queue.push(new Tuple(t.x - 1, t.y));
                    if (t.y < COLS - 1)
                        queue.offer(new Tuple(t.x, t.y + 1));
                    if (t.y > 0)
                        queue.offer(new Tuple(t.x, t.y - 1));
                }
            } else {
                //Queue is empty , initialize a new Land region
                if (this.matrix[i][j] == 1) {
                    queue.offer(new Tuple(i, j));
                    fertileLandRegion++;
                    landAreas.put(fertileLandRegion, 0);
                }
                if (j == COLS - 1) {
                    i++;
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return landAreas.values().stream().sorted().collect(Collectors.toList());
    }


    void printGrid() {
        for (int[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.printf("%2d ", aMatrix[j]);
            System.out.print("\n");
        }
    }


   
    private void processInputString(String barrenLandRectanglesInputString) {
        if (barrenLandRectanglesInputString == null || barrenLandRectanglesInputString.length() == 0)
            return;
        barrenLandRectanglesInputString = barrenLandRectanglesInputString.replaceAll("\\{|\\}", "");
        String[] rects = barrenLandRectanglesInputString.split("\\s*,\\s*");

        for (String r : rects) {
            r = r.replaceAll("\"|“|”", "");
            String coordinates[] = r.split(" ");
            if (coordinates.length == 4) {
                this.barrenLandRectangles.add(new int[]{
                        Integer.valueOf(coordinates[0]),
                        Integer.valueOf(coordinates[1]),
                        Integer.valueOf(coordinates[2]),
                        Integer.valueOf(coordinates[3])
                });
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(getInstance(400, 600).findFertileLand());
    }

 
    public static BarrenLandAnalysis getInstance(int rows, int cols) {
        System.out.println("Enter coordinates for barren land rectangles ");
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            return new BarrenLandAnalysis(rows, cols, input);

        }
    }
}
