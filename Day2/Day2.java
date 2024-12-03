package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 2");

        int countSafe = 0;

        try {
            File inputFile = new File("Day2/input.txt");
            Scanner input = new Scanner(inputFile);

            int testCount = 0;

            while (input.hasNextLine()) {
                String line = input.nextLine();
                testCount++;
                String[] values = line.split(" ");
                ArrayList<Integer> intValues = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    intValues.add(Integer.parseInt(values[i]));
                }
                if (isSafeWithSkip(intValues)) {
                    System.out.println(testCount + " Safe: " + intValues);
                    countSafe++;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Safe Count: " + countSafe);
    }

    // brute force solution
    static boolean isSafeWithSkip(ArrayList<Integer> values) {

        if (isSafe(values)) {
            return true;
        }

        for (int i = 0; i < values.size(); i++) {
            ArrayList<Integer> newValues = new ArrayList<>(values);
            newValues.remove(i);
            if (isSafe(newValues)) {
                return true;
            }
        }

        return false;

    }

    static boolean isSafe(ArrayList<Integer> values) {

        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 0; i < values.size() - 1; i++) {

            int diff = Math.abs(values.get(i) - values.get(i + 1));
            if (diff > 3 || diff < 1) {
                return false;
            }
            if (values.get(i) < values.get(i + 1)) {
                increasing = true;
                if (decreasing) {
                    return false;
                }
            } else if (values.get(i) > values.get(i + 1)) {
                decreasing = true;
                if (increasing) {
                    return false;
                }
            }
        }

        return ((increasing && !decreasing) || (!increasing && decreasing));
    }
}
