package Day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.Array;

public class Day9 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 9");

        ArrayList<Integer> data = new ArrayList<Integer>();
        long checksum = 0;

        // Read input from file
        try (Scanner scanner = new Scanner(new File("Day9/input.txt"))) {

            scanner.useDelimiter("");

            int id = 0;
            int i = 0;

            while (scanner.hasNext()) {
                int n = scanner.nextInt();
                int value = -1;
                if (i % 2 == 0) {
                    value = id;
                    id++;
                }
                for (int j = 0; j < n; j++) {
                    data.add(value);
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        part2(data);

        checksum = calcChecksum2(data);

        System.out.println("Checksum: " + checksum);
    }

    static long calcChecksum2(ArrayList<Integer> data) {
        long checksum = 0;
        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            checksum = (value > 0) ? checksum += (value * i) : checksum;
        }
        return checksum;
    }

    static long calcChecksum1(ArrayList<Integer> data) {
        long checksum = 0;
        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            if (value < 0) {
                break;
            }
            checksum += (value * i);
        }
        return checksum;
    }

    static void part1(ArrayList<Integer> data) {
        for (int i = data.size() - 1; i >= 0; i--) {
            int nextGap = data.indexOf(-1);
            if (nextGap >= i) {
                break;
            }
            if (data.get(i) < 0) {
                continue;
            }
            ;
            Collections.swap(data, i, nextGap);
        }
    }

    static void part2(ArrayList<Integer> data) {
        int maxGap = 9;
        int lastBlock = 0;

        for (int i = data.size() - 1; i >= 0; i--) {

            if (data.get(i) == -1) {
                continue;
            }

            int value = data.get(i);
            int blockStart = data.indexOf(value);
            int blockEnd = i;

            int blockSize = blockEnd - blockStart + 1;

            if (blockSize > maxGap) {
                i -= blockSize - 1;
                continue;
            } else if (blockSize == 1) {
                int nextGap = data.indexOf(-1);
                if (nextGap >= i) {
                    continue;
                }
                Collections.swap(data, i, nextGap);
                lastBlock++;
                continue;
            } else {
                int nextGapStart = findNextGapBlock(data, blockSize, lastBlock);
                if (nextGapStart == -1) {
                    maxGap = blockSize - 1;
                    i -= blockSize - 1;
                    continue;
                } else if (nextGapStart >= i) {
                    maxGap = blockSize - 1;
                    i -= blockSize - 1;
                    continue;
                }

                for (int j = 0; j < blockSize; j++) {
                    Collections.swap(data, blockStart + j, nextGapStart + j);
                }
            }

        }
    }

    static int findNextGapBlock(ArrayList<Integer> data, int size, int start) {
        int nextBlock = -1;
        int count = 0;
        for (int i = start; i < data.size(); i++) {
            if (data.get(i) == -1) {
                count++;
            } else {
                count = 0;
            }
            if (count == size) {
                nextBlock = i - size + 1;
                break;
            }
        }
        return nextBlock;
    }

}
