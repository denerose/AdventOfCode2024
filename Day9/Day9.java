package Day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

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

        part1(data);

        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            if (value < 0) {
                break;
            }
            checksum += (value * i);
        }

        System.out.println("Checksum: " + checksum);
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
}
