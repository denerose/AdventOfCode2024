package Day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

import Helpers.Coord;
import Helpers.Grid;

public class Day10 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        final int GRIDSZE = 8;
        Grid grid = new Grid(GRIDSZE);
        int[] directions = { 0, 1, 2, 3 };

        try (Scanner scanner = new Scanner(new File("Day10/test.txt"))) {

            int i = 0;
            while (scanner.hasNextLine() && i < GRIDSZE) {
                String line = scanner.nextLine();
                grid.setRow(i, line.toCharArray());
                System.out.println(grid.getRow(i));
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        System.out.println("Getting all trail heads...");
        ArrayList<Coord> trailHeads = grid.getAllCoords('0');
        System.out.println("Trail heads: " + trailHeads);

        int count = 0;

        for (Coord trailHead : trailHeads) {
            System.out.println("Trail head: " + trailHead);
            count += countTrails(grid, trailHead, '0', directions, new HashSet<Coord>());
        }

        System.out.println("Number of trails: " + count);

    }

    // depth first search for [0-9] in all four directions
    static int countTrails(Grid grid, Coord start, char target, int[] directions, Set<Coord> visited) {
        visited.add(start);

        if (target == '9') {
            System.out.println("Reached end");
            return 1;
        }

        int result = 0;

        for (int direction : directions) {
            Coord next = start.getAdjacentCoord(direction);
            if (grid.getCell(next) == (char) (target + 1)) {
                System.out.println("Found: " + grid.getCell(next) + " at " + next);
                result += countTrails(grid, next, (char) (target + 1), directions, visited);
            }
        }

        return result;
    }

}
