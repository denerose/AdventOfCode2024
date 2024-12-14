package Day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import Helpers.Coord;
import Helpers.Grid;

import java.io.File;

public class Day12 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 12");

        final int GRIDSIZE = 140;
        Grid gardenGrid = new Grid(GRIDSIZE);
        ArrayList<Plot> allPlots = new ArrayList<Plot>();
        HashSet<String> counted = new HashSet<String>();

        int totalCost = 0;
        int bulkCost = 0;

        readInput(gardenGrid);

        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                Coord current = new Coord(i, j);
                if (counted.contains(current.toString())) {
                    continue;
                }

                Plot plot = new Plot(current, gardenGrid.getCell(current));
                Set<String> visited = new HashSet<>();
                totalCost += findPlots(gardenGrid, plot, visited, counted);
                allPlots.add(plot);
            }
        }

        System.out.println("Total cost: " + totalCost);

        for (Plot plot : allPlots) {
            bulkCost += plot.getBulkPrice();
        }

        System.out.println("Bulk cost: " + bulkCost);

    }

    static int findPlots(Grid gardenGrid, Plot plot, Set<String> visited, Set<String> counted) {

        Queue<Coord> queue = new LinkedList<>();
        queue.add(plot.startPoint);

        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            if (visited.contains(current.toString())) {
                continue;
            }

            visited.add(current.toString());

            if (gardenGrid.getCell(current) == plot.ch) {
                plot.addCoord(current);
                counted.add(current.toString());

                for (Coord neighbor : current.getNeighbors()) {
                    if (gardenGrid.isSafe(neighbor) && !visited.contains(neighbor.toString())) {
                        queue.add(neighbor);
                    }
                }

            } else {
                plot.incrementPerimeter();
            }
        }

        plot.checkPerimiter();
        return plot.getPrice();
    }

    // Read input from file
    public static void readInput(Grid grid) {
        try (Scanner scanner = new Scanner(new File("Day12/input.txt"))) {

            int i = 0;
            while (scanner.hasNextLine() && i < grid.size()) {
                String line = scanner.nextLine();
                grid.setRow(i, line.trim().toCharArray());
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
