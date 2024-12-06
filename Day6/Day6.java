package Day6;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 6");

        char[][] grid = new char[130][130];
        int[] startPos = new int[2];
        Direction facingDirection = Direction.UP;
        int stepCount = 1;

        try (Scanner scanner = new Scanner(new File("Day6/input.txt"))) {
            int i = 0;
            while (scanner.hasNextLine() && i < grid.length) {
                String line = scanner.nextLine();
                System.out.println(line);
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

        int xPos = startPos[1];
        int yPos = startPos[0];

        while (xPos >= 0 && xPos < grid[0].length && yPos >= 0 && yPos < grid.length) {
            int[] nextPos = step(xPos, yPos, facingDirection);

            if (isPathClear(grid, nextPos[0], nextPos[1])) {
                xPos = nextPos[0];
                yPos = nextPos[1];
                if (yPos >= 0 && yPos < grid.length && xPos >= 0 && xPos < grid[0].length && grid[yPos][xPos] == '.') {
                    grid[yPos][xPos] = 'X';
                    stepCount++;
                }
            } else {
                facingDirection = facingDirection.next();
                // System.out.println("Turning to: " + facingDirection);
            }
        }

        printGrid(grid);
        System.out.println("Steps taken: " + stepCount);

    }

    static boolean isPathClear(char[][] grid, int x, int y) {
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length) {
            return grid[y][x] != '#';
        }
        return true;
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
