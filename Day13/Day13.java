package Day13;

import java.util.Scanner;
import java.io.File;

public class Day13 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 10");
    }

    // Read input from file
    public static void readInput() {
        try (Scanner scanner = new Scanner(new File("Day10/input.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
