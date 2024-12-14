package Day14;

import java.util.ArrayList;
import java.util.Scanner;

import Helpers.Coord;
import Helpers.Grid;

import java.io.File;

public class Day14 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 14");

        ArrayList<Robot> robots = new ArrayList<>();

        long Q1 = 0, Q2 = 0, Q3 = 0, Q4 = 0;

        int maxX = 101, maxY = 103, midX = maxX / 2, midY = maxY / 2;

        readInput(robots);

        for (Robot robot : robots) {
            Coord loc = robot.move(100);
            if (loc.getX() > midX && loc.getY() > midY) {
                Q4++;
            } else if (loc.getX() > midX && loc.getY() < midY) {
                Q2++;
            } else if (loc.getX() < midX && loc.getY() > midY) {
                Q3++;
            } else if (loc.getX() < midX && loc.getY() < midY) {
                Q1++;
            }

        }

        System.out.println("\nPart 1 = " + (Q1 * Q2 * Q3 * Q4));

        System.out.println("\nResetting robots...");
        for (Robot robot : robots) {
            robot.reset();
        }

        Grid grid = new Grid(maxY, maxX);
        for (Robot robot : robots) {
            grid.setCell(robot.getStartCoord(), '#');
        }

        System.out.println("Starting simulation...");
        Scanner sysIn = new Scanner(System.in);

        int seconds = findTree(robots, grid, maxX, maxY);

        System.out.println("Simulation complete... Keep this grid? (y/n)");
        String input = sysIn.nextLine();

        while (!input.equals("y") && !input.equals("Y")) {
            seconds += findTree(robots, grid, maxX, maxY);
            System.out.println("Simulation complete... Keep this grid? (y/n)");
            input = sysIn.nextLine();
        }

        System.out.println("\nPart 2 = " + seconds);
    }

    static int findTree(ArrayList<Robot> robots, Grid grid, int maxX, int maxY) {
        int seconds = 0;

        while (true) {
            for (Robot robot : robots) {
                Coord pos = robot.getPosition();
                Coord loc = robot.move(1);
                grid.setCell(loc, '#');
                grid.setCell(pos, ' ');
            }

            seconds++;

            // Check for a block of robots is in the middle position, if found break
            if (grid.getMidCell() == '#' && grid.getCell((maxY / 2), (maxX / 2) + 2) == '#'
                    && grid.getCell((maxY / 2) + 2, (maxX / 2)) == '#'
                    && grid.getCell((maxY / 2) - 2, (maxX / 2)) == '#'
                    && grid.getCell((maxY / 2), (maxX / 2) - 2) == '#') {
                grid.printGrid();
                break;
            }
        }

        return seconds;
    }

    // Read input from file
    public static void readInput(ArrayList<Robot> robots) {
        try (Scanner scanner = new Scanner(new File("Day14/input.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");

                int x = Integer.parseInt(split[0].substring(split[0].indexOf("=") + 1, split[0].indexOf(",")));
                int y = Integer.parseInt(split[0].substring(split[0].indexOf(",") + 1));
                int vX = Integer.parseInt(split[1].substring(split[1].indexOf("=") + 1, split[1].indexOf(",")));
                int vY = Integer.parseInt(split[1].substring(split[1].indexOf(",") + 1));

                Coord startCoord = new Coord(y, x);
                Coord velocity = new Coord(vY, vX);

                robots.add(new Robot(startCoord, velocity));
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
