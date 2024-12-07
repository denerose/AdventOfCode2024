package Day7;

import java.util.Scanner;
import java.io.File;

public class Day7 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 7");

        try (Scanner scanner = new Scanner(new File("Day7/test.txt"))) {
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
