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

        Coord robotStart = grid.getFirstCoord('@');
        grid.setCell(robotStart, '.');

        traverse(robotStart, grid, robotMoves);

        ArrayList<Coord> boxes = grid.getAllCoords('O');
        long totalLFGPS = 0;

        for (Coord box : boxes) {
            totalLFGPS += calculateBoxes(box);
        }

        grid.printGrid();
        System.out.println("Part 1: " + totalLFGPS);

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

}
