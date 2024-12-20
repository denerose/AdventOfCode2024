package Day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import Helpers.Coord;
import Helpers.Grid;

public class Day10 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        final int GRIDSZE = 45;
        Grid grid = new Grid(GRIDSZE);
        int[] directions = { 0, 1, 2, 3 };

        try (Scanner scanner = new Scanner(new File("Day10/input.txt"))) {

            int i = 0;
            while (scanner.hasNextLine() && i < GRIDSZE) {
                String line = scanner.nextLine();
                grid.setRow(i, line.toCharArray());
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        System.out.println("Getting all trail heads...");
        ArrayList<Coord> trailHeads = grid.getAllCoords('0');

        int count = 0;
        int rating = 0;

        for (Coord trailHead : trailHeads) {
            int trailScore = countTrails(grid, trailHead, '0', directions, new ArrayList<String>());
            count += trailScore;
            rating += rateTrails(grid, trailHead, '0', directions);
        }

        System.out.println("Number of trails: " + count);
        System.out.println("Rating of trails: " + rating);

    }

    // depth first search for [0-9] in all four directions
    static int countTrails(Grid grid, Coord start, char target, int[] directions, List<String> visited) {
        visited.add(start.toString());

        if (target == '9') {
            return 1;
        }

        int result = 0;

        for (int direction : directions) {
            Coord next = start.getAdjacentCoord(direction);
            if (!visited.contains(next.toString()) && grid.getCell(next) == (char) (target + 1)) {
                result += countTrails(grid, next, (char) (target + 1), directions, visited);
            }
        }

        return result;
    }

    static int rateTrails(Grid grid, Coord start, char target, int[] directions) {

        if (target == '9') {
            return 1;
        }

        int result = 0;

        for (int direction : directions) {
            Coord next = start.getAdjacentCoord(direction);
            if (grid.getCell(next) == (char) (target + 1)) {
                result += rateTrails(grid, next, (char) (target + 1), directions);
            }
        }

        return result;
    }

}
