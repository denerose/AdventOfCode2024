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
        try (Scanner scanner = new Scanner(new File("Day9/test.txt"))) {

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

    static int calcChecksum2(ArrayList<Integer> data) {
        int checksum = 0;
        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            checksum += (value * i);
        }
        return checksum;
    }

    static int calcChecksum1(ArrayList<Integer> data) {
        int checksum = 0;
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
        int largestGap = 9;
        int lastStart = 0;
        int i = data.size() - 1;

        while (i >= 0 && largestGap > 0) {
            if (data.get(i) < 0) {
                i--;
                continue;
            }
            int blockStart = data.indexOf(data.get(i));
            int blockEnd = i;

            int blockSize = (blockEnd != blockStart) ? blockEnd - blockStart : 1;
            if (blockSize > largestGap) {
                largestGap--;
                i -= blockSize;
                continue;
            }

            int[] gap = findGap(data, blockSize, lastStart);
            if (gap.length <= 0 || gap[0] < 0) {
                largestGap--;
                i -= blockSize;
                continue;
            }

            for (int index : gap) {
                Collections.swap(data, index, blockStart);
                blockStart++;
            }
            i -= blockSize;
        }

    }

    static int[] findGap(ArrayList<Integer> data, int size, int start) {
        int[] gap = new int[size];
        if (start >= data.size()) {
            return new int[] { -1 };
        }
        if (gap.length > 0 && gap[size - 1] > 0) {
            return gap;
        } else {
            for (int i = start; i < data.size(); i++) {
                if (data.get(i) < 0) {
                    gap[0] = i;
                    if (i + size >= data.size() || data.get(i + size) != -1) {
                        i += size;
                        continue;
                    }
                    for (int j = 1; j < size; j++) {
                        if (data.get(i + j) < 0) {
                            gap[j] = i + j;
                        } else {
                            i += j;
                            break;
                        }
                    }
                    if (gap[size - 1] > 0) {
                        return gap;
                    }
                }
            }
        }
        return new int[] { -1 };
    }

}
