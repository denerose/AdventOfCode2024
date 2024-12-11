package Day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.Array;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");

        ArrayList<Long> stones = new ArrayList<>(104739000);
        readInput(stones);

        // Part 1
        stones = blink(stones, 25);
        System.out.println("Part 1: " + stones.size());

        // Part 2
        HashMap<Long, Long> stoneMap = mapStones(stones);
        HashMap<Long, long[]> calculations = new HashMap<>();
        calculations.put(0l, new long[] { 1 });
        calculations.put(1l, new long[] { 2024 });
        calculations.put(2024l, new long[] { 20, 24 });
        calculations.put(20l, new long[] { 2, 0 });
        calculations.put(24l, new long[] { 2, 4 });
        calculations.put(2l, new long[] { 4048 });
        calculations.put(4l, new long[] { 8096 });
        calculations.put(4048l, new long[] { 40, 48 });
        calculations.put(8096l, new long[] { 80, 96 });
        calculations.put(40l, new long[] { 4, 0 });
        calculations.put(48l, new long[] { 4, 8 });
        calculations.put(80l, new long[] { 8, 0 });
        calculations.put(96l, new long[] { 9, 6 });

        stoneMap = blinkBetter(stoneMap, 50, calculations);
        System.out.println("Part 2: " + stoneMap.values().stream().mapToLong(Long::longValue).sum());

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

    static HashMap<Long, Long> mapStones(ArrayList<Long> stones) {
        HashMap<Long, Long> stoneMap = new HashMap<>(stones.size());
        for (long stone : stones) {
            if (stoneMap.containsKey(stone)) {
                stoneMap.put(stone, stoneMap.get(stone) + 1);
            } else {
                stoneMap.put(stone, 1l);
            }
        }
        return stoneMap;
    }

    static HashMap<Long, Long> blinkBetter(HashMap<Long, Long> stones, int counter,
            HashMap<Long, long[]> calculations) {

        HashMap<Long, Long> newStones = new HashMap<>();

        stones.entrySet().stream().filter(entry -> calculations.containsKey(entry.getKey())).forEach(entry -> {
            long stone = entry.getKey();
            long count = entry.getValue();
            long[] values = calculations.get(stone);
            for (long value : values) {
                if (newStones.containsKey(value)) {
                    newStones.put(value, newStones.get(value) + count);
                } else {
                    newStones.put(value, count);
                }
            }
        });

        stones.entrySet().stream().filter(entry -> !calculations.containsKey(entry.getKey())).forEach(entry -> {
            long stone = entry.getKey();
            long count = entry.getValue();
            if ((Math.floor(Math.log10(stone)) + 1) % 2 == 0) {
                String numString = Long.toString(stone);
                int middle = numString.length() / 2;
                long left = Long.parseLong(numString.substring(0, middle));
                long right = Long.parseLong(numString.substring(middle));
                calculations.put(stone, new long[] { left, right });
                if (newStones.containsKey(left)) {
                    newStones.put(left, newStones.get(left) + count);
                } else {
                    newStones.put(left, count);
                }
                if (newStones.containsKey(right)) {
                    newStones.put(right, newStones.get(right) + count);
                } else {
                    newStones.put(right, count);
                }
            } else {
                calculations.put(stone, new long[] { stone * 2024 });
                newStones.put(stone * 2024, count);
            }
        });

        if (counter == 1) {
            System.out.println("Counter: " + counter + ". Nums: " + newStones.size());
            return newStones;
        } else {
            System.out.println("Counter: " + counter + ". Nums: " + newStones.size());
            return blinkBetter(newStones, counter - 1, calculations);
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
