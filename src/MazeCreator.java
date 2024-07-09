import java.util.Random;

public class MazeCreator {

    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    public static void main(String[] args) {
        MazeCreator mazeCreator = new MazeCreator(10, 10);
        mazeCreator.create();
        mazeCreator.print();
    }

    private final int[][] maze;
    private final boolean[][] visited;
    private final int maxX;
    private final int maxY;

    private final int startX;
    private final int startY;
    private int depth;

    public MazeCreator(int y, int x) {
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
        for (int[] row : maze) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public void create() {
        dfs(startX, startY);
    }

    private void dfs(int startX, int startY) {
        int direction = generateRandomNumber(4);
        int newX = startX + DX[direction];
        int newY = startY + DY[direction];

        if (!isValid(newX, newY)) {
            for (int i = 0; i < 4; i++) {
                newX = startX + DX[i];
                newY = startY + DY[i];

                if (isValid(newX, newY)) {
                    break;
                }
            }
        }

        if (!isValid(newX, newY)) {
            return;
        }

        visited[newY][newX] = true;
        maze[newY][newX] = depth++;

        dfs(newX, newY);
    }

    private boolean isValid(int x, int y) {
        return !(x < 0 || x >= maxX || y < 0 || y >= maxY || visited[y][x]);
    }

}
