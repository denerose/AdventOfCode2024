package Day6;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 6");

        char[][] grid = new char[10][10];
        int[] startPos = new int[2];
        Direction facingDirection = Direction.UP;
        int firstTraversal = 0;
        ArrayList<int[]> visited = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Day6/test.txt"))) {
            int i = 0;
            while (scanner.hasNextLine() && i < grid.length) {
                String line = scanner.nextLine();
                if (line.contains("^")) {
                    startPos[0] = i;
                    startPos[1] = line.indexOf("^");
                }
                grid[i] = line.toCharArray();
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        System.out.println("Start position: " + startPos[0] + ", " + startPos[1]);
        char[][] gridCopy = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);

        firstTraversal = traverse(startPos, gridCopy, facingDirection, visited);
        System.out.println("Part 1: " + firstTraversal);

        int validLoops = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == startPos[0] && j == startPos[1]) {
                    continue;
                }

                System.out.println("Obstruction at: " + i + ", " + j);
                char[][] newGrid = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
                newGrid[i][j] = 'O';

                boolean valid = traverseWithObstruction(startPos, newGrid, facingDirection);

                System.out.println("Valid: " + valid);
                if (valid) {
                    validLoops++;
                }
            }
        }

        System.out.println("Part 2: " + validLoops);

    }

    static boolean traverseWithObstruction(int[] startPos, char[][] grid, Direction facingDirection) {

        int xPos = startPos[1];
        int yPos = startPos[0];

        while (xPos >= 0 && xPos < grid[0].length && yPos >= 0 && yPos < grid.length) {
            int[] nextPos = step(xPos, yPos, facingDirection);

            if (isPathClear(grid, nextPos[0], nextPos[1])) {
                xPos = nextPos[0];
                yPos = nextPos[1];
            } else {
                facingDirection = facingDirection.next();
            }
        }
        return false;
    }

    static int traverse(int[] startPos, char[][] grid, Direction facingDirection, ArrayList<int[]> visited) {
        int xPos = startPos[1];
        int yPos = startPos[0];
        int stepCount = 1;

        while (xPos >= 0 && xPos < grid[0].length && yPos >= 0 && yPos < grid.length) {
            int[] nextPos = step(xPos, yPos, facingDirection);

            if (isPathClear(grid, nextPos[0], nextPos[1])) {
                xPos = nextPos[0];
                yPos = nextPos[1];
                if (yPos >= 0 && yPos < grid.length && xPos >= 0 && xPos < grid[0].length && grid[yPos][xPos] == '.') {
                    grid[yPos][xPos] = 'X';
                    visited.add(new int[] { yPos, xPos });
                    stepCount++;
                }
            } else {
                facingDirection = facingDirection.next();
            }
        }

        return stepCount;
    }

    static boolean isPathClear(char[][] grid, int x, int y) {
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length) {
            return grid[y][x] != '#' && grid[y][x] != 'O';
        }
        return true;
    }

    static char peek(char[][] grid, int x, int y) {
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length) {
            return grid[y][x];
        } else {
            return 'E';
        }
    }

    static int[] step(int x, int y, Direction facingDirection) {
        switch (facingDirection) {
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
        }

        return new int[] { x, y };

    }

    static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // ENUM for directions
    enum Direction {
        UP, RIGHT, DOWN, LEFT;

        public Direction next() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                default:
                    return UP;
            }
        }
    }
}
