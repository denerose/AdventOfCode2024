package Day17;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Day17 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 17");

        ArrayList<Byte> commands = new ArrayList<>();

        readInput(commands);

        Computer computer = new Computer(41644071, 16);

        computer.takeCommand(commands);

        int a = 183295722;
        boolean found = false;
        int[] testCommands = commands.stream().mapToInt(Byte::intValue).toArray();

        while (!found && a < Integer.MAX_VALUE) {
            a++;
            Computer testComp = new Computer(a, testCommands);
            found = testComp.testCommands();
        }

        System.out.println("The value of register a is: " + a);

    }

    // Read input from file
    public static void readInput(ArrayList<Byte> commands) {
        try (Scanner scanner = new Scanner(new File("Day17/input.txt"))) {

            scanner.useDelimiter(",");

            while (scanner.hasNextInt()) {
                commands.add((byte) scanner.nextInt());
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
