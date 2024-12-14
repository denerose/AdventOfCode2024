package Day13;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Helpers.Coord;

import java.io.File;

public class Day13 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 13");

        HashMap<String, Coord[]> machines = new HashMap<String, Coord[]>();

        readInput(machines);

        long totalPart1 = 0;
        long totalPart2 = 0;

        for (String key : machines.keySet()) {
            Coord[] coords = machines.get(key);
            long result = solve(coords[0], coords[1], coords[2]);
            totalPart1 += result;
        }

        System.out.println("Part 1: " + totalPart1);

        for (String key : machines.keySet()) {
            Coord[] coords = machines.get(key);
            long result = solve2(coords[0], coords[1], coords[2]);
            totalPart2 += result;
        }

        System.out.println("Part 2: " + totalPart2);

    }

    static long solve(Coord a, Coord b, Coord p) {

        long a1 = a.getX();
        long a2 = a.getY();
        long b1 = b.getX();
        long b2 = b.getY();
        long p1 = p.getX();
        long p2 = p.getY();

        // find the denominator, and work out slopes of A and B with respect to prize

        long den = a1 * b2 - b1 * a2;
        long A = b2 * p1 - b1 * p2;
        long B = a1 * p2 - a2 * p1;

        // if A and B are divisible by the denominator, then the point is on the line

        if (A % den == 0 && B % den == 0) {
            return (3 * A + B) / den;
        }

        return 0;
    }

    static long solve2(Coord a, Coord b, Coord p) {

        long a1 = a.getX();
        long a2 = a.getY();
        long b1 = b.getX();
        long b2 = b.getY();
        long p1 = p.getX() + 10000000000000l;
        long p2 = p.getY() + 10000000000000l;

        long den = a1 * b2 - b1 * a2;
        long A = b2 * p1 - b1 * p2;
        long B = a1 * p2 - a2 * p1;

        if (A % den == 0 && B % den == 0) {
            return (3 * A + B) / den;
        }

        return 0;
    }

    // Read input from file
    public static void readInput(HashMap<String, Coord[]> machines) {

        try (Scanner scanner = new Scanner(new File("Day13/input.txt"))) {

            int i = 0;

            while (scanner.hasNextLine()) {
                Pattern aPattern = Pattern.compile("Button A: X([+-]\\d+), Y([+-]\\d+)");
                Pattern bPattern = Pattern.compile("Button B: X([+-]\\d+), Y([+-]\\d+)");
                Pattern prizePattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

                int ax = -1;
                int ay = -1;
                int bx = -1;
                int by = -1;
                int px = -1;
                int py = -1;

                Matcher bmA = aPattern.matcher(scanner.nextLine());
                if (bmA.matches()) {
                    ax = Integer.parseInt(bmA.group(1));
                    ay = Integer.parseInt(bmA.group(2));
                }

                Matcher bmB = bPattern.matcher(scanner.nextLine());
                if (bmB.matches()) {
                    bx = Integer.parseInt(bmB.group(1));
                    by = Integer.parseInt(bmB.group(2));
                }

                Matcher pm = prizePattern.matcher(scanner.nextLine());
                if (pm.matches()) {
                    px = Integer.parseInt(pm.group(1));
                    py = Integer.parseInt(pm.group(2));
                }

                Coord[] coords = new Coord[3];
                coords[0] = new Coord(ay, ax);
                coords[1] = new Coord(by, bx);
                coords[2] = new Coord(py, px);

                machines.put("Machine-" + i, coords);
                i++;

                // consume empty line
                scanner.nextLine();
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
