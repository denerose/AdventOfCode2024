package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 2");

        int countSafe = 0;

        try {
            File inputFile = new File("Day2/input.txt");
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] values = line.split(" ");
                int[] intValues = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    intValues[i] = Integer.parseInt(values[i]);
                }
                if (isSafe(intValues)) {
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

    static boolean isSafe(int[] values) {

        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 0; i < values.length - 1; i++) {

            int diff = Math.abs(values[i] - values[i + 1]);
            if (diff > 3 || diff < 1) {
                return false;
            }
            if (values[i] < values[i + 1]) {
                increasing = true;
                if (decreasing) {
                    return false;
                }
            } else if (values[i] > values[i + 1]) {
                decreasing = true;
                if (increasing) {
                    return false;
                }
            }
        }

        return ((increasing && !decreasing) || (!increasing && decreasing));
    }
}
