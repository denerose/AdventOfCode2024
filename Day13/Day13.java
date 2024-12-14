package Day13;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Helpers.Coord;

import java.io.File;
import java.math.BigInteger;

public class Day13 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        HashMap<String, Coord[]> machines = new HashMap<String, Coord[]>();

        readInput(machines);

        long totalTokens = 0;

        for (String key : machines.keySet()) {
            Coord[] coords = machines.get(key);
            long result = solve(coords[0], coords[1], coords[2]);
            totalTokens += result;
            System.out.println("Machine: " + key + " Result: " + result);
        }

        System.out.println("Total Tokens: " + totalTokens);

    }

    static long solve(Coord a, Coord b, Coord p) {

        // Find denominator in inverse of 2x2 Matrix

        long a1 = a.getX();
        long a2 = a.getY();
        long b1 = b.getX();
        long b2 = b.getY();
        long p1 = p.getX();
        long p2 = p.getY();

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
