package Day11;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        ArrayList<Long> stones = new ArrayList<>();
        readInput(stones);

        // Part 1
        System.out.println("Stones: " + stones);
        ArrayList<Long> part1 = blink(stones, 25);
        System.out.println("Part 1: " + part1.size());

    }

    static ArrayList<Long> blink(ArrayList<Long> stones, int counter) {

        ArrayList<Long> newStones = new ArrayList<>();

        for (long stone : stones) {
            if (stone == 0) {
                newStones.add(1l);
            } else if (stone != 0 && (Math.floor(Math.log10(stone)) + 1) % 2 == 0) {
                String numString = Long.toString(stone);
                int middle = numString.length() / 2;
                long left = Long.parseLong(numString.substring(0, middle));
                long right = Long.parseLong(numString.substring(middle));
                newStones.add(left);
                newStones.add(right);
            } else {
                newStones.add(stone * 2024);
            }
        }
        if (counter == 1) {
            return newStones;
        } else {
            return blink(newStones, counter - 1);
        }

    }

    // Read input from file
    static void readInput(ArrayList<Long> stones) {
        try (Scanner scanner = new Scanner(new File("Day11/input.txt"))) {

            while (scanner.hasNextLong()) {
                stones.add(scanner.nextLong());
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
