package Day6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 6");

        final int GRIDSIZE = 130;

        char[][] grid = new char[GRIDSIZE][GRIDSIZE];
        int[] startPos = new int[2];
        Direction facingDirection = Direction.UP;
        int firstTraversal = 0;
        ArrayList<int[]> visitedOriginal = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Day6/input.txt"))) {
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

        firstTraversal = traverse(startPos, gridCopy, facingDirection, visitedOriginal);
        System.out.println("Part 1: " + firstTraversal);

        int validLoops = 0;

        for (int[] pos : visitedOriginal) {
            char[][] newGrid = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
            newGrid[pos[0]][pos[1]] = 'O';
            System.out.println("Placing obstruction at: " + pos[0] + ", " + pos[1]);
            if (traverseWithObstruction(startPos, newGrid, facingDirection)) {
                validLoops++;
            }
        }

        System.out.println("Part 2: " + validLoops);

    }

    static boolean traverseWithObstruction(int[] startPos, char[][] currentGrid, Direction facingDirection) {

        int xPos = startPos[1];
        int yPos = startPos[0];

        boolean firstTraversal = true;
        ArrayList<int[]> visited = new ArrayList<>();

        while (peek(currentGrid, xPos, yPos) != 'E') {

            int[] nextPos = step(xPos, yPos, facingDirection);
            char nextChar = peek(currentGrid, nextPos[0], nextPos[1]);

            switch (nextChar) {
                case 'O':
                    if (!firstTraversal) {
                        return true;
                    } else {
                        firstTraversal = false;
                        facingDirection = facingDirection.next();
                        break;
                    }
                case '#':
                    facingDirection = facingDirection.next();
                    break;
                case '.':
                    currentGrid[yPos][xPos] = 'X';
                case 'X':
                case '^':
                    xPos = nextPos[0];
                    yPos = nextPos[1];
                    if (visited.contains(new int[] { yPos, xPos, facingDirection.toNum() })) {
                        return true;
                    }
                    visited.add(new int[] { yPos, xPos, facingDirection.toNum() });
                    break;
                case 'E':
                    return false;
                default:
                    break;
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
        int i = 0;
        for (char[] row : grid) {
            System.out.print(i + " ");
            i++;
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // ENUM for directions
    enum Direction {
        UP(1), RIGHT(2), DOWN(3), LEFT(4);

        private int dirNum;

        Direction(int dirNum) {
            this.dirNum = dirNum;
        }

        public int toNum() {
            return this.dirNum;
        }

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
