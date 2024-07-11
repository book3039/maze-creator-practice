import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
        mazeGenerator.create();
        mazeGenerator.print();
    }

    private final int[][] maze;
    private final boolean[][] visited;
    private final int maxX;
    private final int maxY;

    private final int startX;
    private final int startY;
    private int depth;

    public MazeGenerator(int y, int x) {
        maze = new int[y][x];
        visited = new boolean[y][x];

        maxX = x;
        maxY = y;

        startX = generateRandomNumber(x);
        startY = generateRandomNumber(y);

        depth = 1;
        maze[startY][startX] = depth++;
        visited[startY][startX] = true;
    }

    private int generateRandomNumber(int max) {
        return new Random().nextInt(max);
    }

    public void print() {
        int maxDigits = String.valueOf(maxX * maxY).length();

        for (int[] row : maze) {
            for (int element : row) {
                System.out.printf("%" + maxDigits + "d ", element);
            }
            System.out.println();
        }
    }


    public void create() {
        dfs(startX, startY);
    }

    private void dfs(int startX, int startY) {

        visited[startX][startY] = true;

        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions);

        for (int direction : directions) {
            int newX = startX + DX[direction];
            int newY = startY + DY[direction];

            if (isValid(newX, newY)) {
                maze[newY][newX] = depth++;

                dfs(newX, newY);
            }
        }


    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY && !visited[x][y];
    }

}
