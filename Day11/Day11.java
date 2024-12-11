package Day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        ArrayList<Long> stones = new ArrayList<>(104739000);
        readInput(stones);

        int totalIterations = 0;
        int stoneCount = 0;

        // Part 1
        ArrayList<Long> part1 = blink(stones, 25);
        totalIterations += 25;
        stoneCount = part1.size();
        System.out.println("Part 1: " + part1.size());

        // Part 2
        part1 = blinkBetter(part1, 10);
        totalIterations += 10;
        stoneCount = part1.size();

        List<Long> left = blinkBetter(part1.subList(0, part1.size() / 2), 10);
        List<Long> right = blinkBetter(part1.subList(part1.size() / 2, part1.size()), 10);

        totalIterations += 10;
        stoneCount = left.size() + right.size();

        System.out.println("Part 2: " + stoneCount + "\n Total Iterations: " + totalIterations);

    }

    static ArrayList<Long> blink(ArrayList<Long> stones, int counter) {

        ArrayList<Long> newStones = new ArrayList<>(stones.size() * 2);

        for (long stone : stones) {
            if (stone == 0) {
                newStones.add(1l);
            } else if ((Math.floor(Math.log10(stone)) + 1) % 2 == 0) {
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
            System.out.println("Counter: " + counter + ". Stones: " + newStones.size());
            return newStones;
        } else {
            System.out.println("Counter: " + counter + ". Stones: " + newStones.size());
            return blink(newStones, counter - 1);
        }

    }

    static ArrayList<Long> blinkBetter(List<Long> stones, int counter) {

        ArrayList<Long> newStones = new ArrayList<>(stones.size() * 2);

        for (long stone : stones) {
            if (stone == 0) {
                newStones.add(1l);
            } else if (Math.floor(Math.log10(stone) + 1) % 2 == 0) {
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
            System.out.println("Counter: " + counter + ". Stones: " + newStones.size());
            return newStones;
        } else {
            System.out.println("Counter: " + counter + ". Stones: " + newStones.size());
            stones = newStones;
            return blinkBetter(stones, counter - 1);
        }

    }

    // Read input from file
    static void readInput(ArrayList<Long> stones) {
        try (Scanner scanner = new Scanner(new File("Day11/input.txt"))) {

            while (scanner.hasNextLong()) {
                stones.add(scanner.nextLong());
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
