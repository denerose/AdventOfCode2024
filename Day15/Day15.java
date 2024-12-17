package Day15;

import java.util.ArrayList;
import java.util.Scanner;

import Helpers.Coord;
import Helpers.Grid;

import java.io.File;

public class Day15 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 15");

        final int GRIDSIZE = 50;
        Grid grid = new Grid(GRIDSIZE, GRIDSIZE);
        ArrayList<Integer> robotMoves = new ArrayList<Integer>();

        // Read input from file
        readInput(grid, robotMoves);

        Grid bigGrid = new Grid(GRIDSIZE, (GRIDSIZE * 2));
        upgradeGrid(grid, bigGrid);

        // Part 1

        Coord robotStart = grid.getFirstCoord('@');
        grid.setCell(robotStart, '.');

        traverse(robotStart, grid, robotMoves);

        ArrayList<Coord> boxes = grid.getAllCoords('O');
        long totalLFGPS = 0;

        for (Coord box : boxes) {
            totalLFGPS += calculateBoxes(box);
        }

        System.out.println("Part 1: " + totalLFGPS);

        // Part 2

        Coord bigRobotStart = bigGrid.getFirstCoord('@');
        bigGrid.setCell(bigRobotStart, '.');

        traverse(bigRobotStart, bigGrid, robotMoves);

        ArrayList<Coord> bigBoxes = bigGrid.getAllCoords('[');
        long totalBigBoxGPS = 0;

        for (Coord box : bigBoxes) {
            totalBigBoxGPS += calculateBoxes(box);
        }

        bigGrid.printGrid();

        System.out.println("Part 2: " + totalBigBoxGPS);

    }

    static void traverse(Coord robotStart, Grid grid, ArrayList<Integer> robotMoves) {
        Coord robot = robotStart;
        for (int move : robotMoves) {
            Coord next = robot.getAdjacentCoord(move);
            if (grid.isSafe(next)) {
                if (grid.getCell(next) == '.') {
                    grid.setCell(robot, '.');
                    grid.setCell(next, '@');
                    robot = next;
                } else if (grid.getCell(next) == 'O' && moveBox(next, grid, move)) {
                    grid.setCell(robot, '.');
                    grid.setCell(next, '@');
                    robot = next;
                } else if ((move == 1 || move == 3) && (grid.getCell(next) == '[' || grid.getCell(next) == ']')
                        && canMoveBigBox(next, grid, move)) {
                    moveBigBox(next, grid, move);
                    grid.setCell(robot, '.');
                    grid.setCell(next, '@');
                    robot = next;
                } else if ((move == 0 || move == 2) && (grid.getCell(next) == '[' || grid.getCell(next) == ']')
                        && canMoveBigBox(next, grid, move)) {
                    moveBigBox(next, grid, move);
                    grid.setCell(robot, '.');
                    grid.setCell(next, '@');
                    robot = next;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }

    static boolean moveBox(Coord box, Grid grid, int direction) {
        Coord next = box.getAdjacentCoord(direction);
        if (grid.isSafe(next)) {
            if (grid.getCell(next) == '.') {
                grid.setCell(box, '.');
                grid.setCell(next, 'O');
                return true;
            } else if (grid.getCell(next) == 'O' && moveBox(next, grid, direction)) {
                grid.setCell(box, '.');
                grid.setCell(next, 'O');
                return true;
            }
        }
        return false;
    }

    static void moveBigBox(Coord box1, Grid grid, int direction) {
        if (direction == 3) {
            // box is moving left
            Coord box2 = box1.getAdjacentCoord(direction);
            Coord next = box2.getAdjacentCoord(direction);
            if (grid.isSafe(next)) {
                if (grid.getCell(next) == '.') {
                    grid.setCell(box1, '.');
                    grid.setCell(box2, ']');
                    grid.setCell(next, '[');
                } else if (grid.getCell(next) == ']' && canMoveBigBox(next, grid, direction)) {
                    moveBigBox(next, grid, direction);
                    grid.setCell(next, grid.getCell(box2));
                    grid.setCell(box2, grid.getCell(box1));
                    grid.setCell(box1, '.');
                }
            }

        } else if (direction == 1) {
            // box is moving right
            Coord box2 = box1.getAdjacentCoord(direction);
            Coord next = box2.getAdjacentCoord(direction);
            if (grid.isSafe(next)) {
                if (grid.getCell(next) == '.') {
                    grid.setCell(box1, '.');
                    grid.setCell(box2, '[');
                    grid.setCell(next, ']');
                } else if (grid.getCell(next) == '[' && canMoveBigBox(next, grid, direction)) {
                    moveBigBox(next, grid, direction);
                    grid.setCell(next, grid.getCell(box2));
                    grid.setCell(box2, grid.getCell(box1));
                    grid.setCell(box1, '.');
                }
            }

        } else if (direction == 0 || direction == 2) {
            // box is moving up or down
            Coord box2 = (grid.getCell(box1) == '[') ? box1.getAdjacentCoord(1) : box1.getAdjacentCoord(3);
            Coord next1 = box1.getAdjacentCoord(direction);
            Coord next2 = box2.getAdjacentCoord(direction);

            if (grid.isSafe(next1) && grid.isSafe(next2)) {
                if (grid.getCell(next1) == '#' || grid.getCell(next2) == '#') {
                    return;
                }

                if (grid.getCell(next1) == '.' && grid.getCell(next2) == '.') {
                    grid.setCell(next1, grid.getCell(box1));
                    grid.setCell(next2, grid.getCell(box2));
                    grid.setCell(box1, '.');
                    grid.setCell(box2, '.');
                } else if (grid.getCell(next1) == ']' && grid.getCell(next2) == '['
                        && canMoveBigBox(next1, grid, direction) && canMoveBigBox(next2, grid, direction)) {
                    moveBigBox(next1, grid, direction);
                    moveBigBox(next2, grid, direction);
                    grid.setCell(next1, grid.getCell(box1));
                    grid.setCell(next2, grid.getCell(box2));
                    grid.setCell(box1, '.');
                    grid.setCell(box2, '.');
                } else if (grid.getCell(next1) == '[' && grid.getCell(next2) == ']'
                        && canMoveBigBox(next1, grid, direction) && canMoveBigBox(next2, grid, direction)) {
                    moveBigBox(next1, grid, direction);
                    moveBigBox(next2, grid, direction);
                    grid.setCell(next1, grid.getCell(box1));
                    grid.setCell(next2, grid.getCell(box2));
                    grid.setCell(box1, '.');
                    grid.setCell(box2, '.');
                }
            }

        }
    }

    static boolean canMoveBigBox(Coord box, Grid grid, int direction) {
        if (direction == 3) {
            // box is moving left
            Coord box2 = box.getAdjacentCoord(direction);
            Coord next = box2.getAdjacentCoord(direction);
            if (grid.isSafe(next)) {
                if (grid.getCell(next) == '#') {
                    return false;
                }

                if (grid.getCell(next) == '.') {
                    return true;
                } else if (grid.getCell(next) == ']' && canMoveBigBox(next, grid, direction)) {
                    return true;
                }
            }
        } else if (direction == 1) {
            // box is moving right
            Coord box2 = box.getAdjacentCoord(direction);
            Coord next = box2.getAdjacentCoord(direction);
            if (grid.isSafe(next)) {
                if (grid.getCell(next) == '#') {
                    return false;
                }

                if (grid.getCell(next) == '.') {
                    return true;
                } else if (grid.getCell(next) == '[' && canMoveBigBox(next, grid, direction)) {
                    return true;
                }
            }

        } else if (direction == 0 || direction == 2) {
            // box is moving up or down
            Coord box2 = (grid.getCell(box) == '[') ? box.getAdjacentCoord(1) : box.getAdjacentCoord(3);
            Coord next1 = box.getAdjacentCoord(direction);
            Coord next2 = box2.getAdjacentCoord(direction);

            if (grid.isSafe(next1) && grid.isSafe(next2)) {
                if (grid.getCell(next1) == '#' || grid.getCell(next2) == '#') {
                    return false;
                }

                if (grid.getCell(next1) == '.' && grid.getCell(next2) == '.') {
                    return true;
                } else if (grid.getCell(next1) == ']' && grid.getCell(next2) == '['
                        && canMoveBigBox(next1, grid, direction) && canMoveBigBox(next2, grid, direction)) {
                    return true;
                } else if (grid.getCell(next1) == '[' && grid.getCell(next2) == ']'
                        && canMoveBigBox(next1, grid, direction)) {
                    return true;
                }
            }

        }
        return false;
    }

    static long calculateBoxes(Coord box) {
        return 100 * box.getY() + box.getX();
    }

    // Read input from file
    public static void readInput(Grid grid, ArrayList<Integer> robotMoves) {

        // Read the map
        try (Scanner scanner = new Scanner(new File("Day15/map.txt"))) {
            int i = 0;
            while (scanner.hasNextLine() && i < grid.getMaxY()) {
                String line = scanner.nextLine();
                grid.setRow(i, line.toCharArray());
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        // Read the robot moves
        try (Scanner scanner = new Scanner(new File("Day15/input.txt"))) {

            scanner.useDelimiter("");
            while (scanner.hasNext()) {
                char next = scanner.next().charAt(0);
                switch (next) {
                    case '^':
                        robotMoves.add(0);
                        break;
                    case '>':
                        robotMoves.add(1);
                        break;
                    case 'v':
                        robotMoves.add(2);
                        break;
                    case '<':
                        robotMoves.add(3);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

    static void upgradeGrid(Grid grid, Grid bigGrid) {

        for (int i = 0; i < grid.getMaxY(); i++) {

            char[] row = grid.getRow(i);
            StringBuilder sb = new StringBuilder();

            for (char cell : row) {
                switch (cell) {
                    case '.':
                        sb.append("..");
                        break;
                    case 'O':
                        sb.append("[]");
                        break;
                    case '#':
                        sb.append("##");
                        break;
                    case '@':
                        sb.append("@.");
                        break;
                    default:
                        break;
                }
            }

            char[] newRow = sb.toString().toCharArray();
            bigGrid.setRow(i, newRow);
        }

    }

}
