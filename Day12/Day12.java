package Day12;

import java.util.Scanner;

import Helpers.Grid;

import java.io.File;

public class Day12 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        final int GRIDSIZE = 10;
        Grid gardenGrid = new Grid(GRIDSIZE);

        readInput(gardenGrid);

    }

    // Read input from file
    public static void readInput(Grid grid) {
        try (Scanner scanner = new Scanner(new File("Day12/input.txt"))) {

            int i = 0;
            while (scanner.hasNextLine() && i < grid.size()) {
                String line = scanner.nextLine();
                grid.setRow(i, line.toCharArray());
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
