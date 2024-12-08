package Day7;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Day7 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 7");

        ArrayList<CalibrationEquation> equations = new ArrayList<>();
        long totalCalibrated = 0;

        // Read input from file
        try (Scanner scanner = new Scanner(new File("Day7/input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split("\\:");
                long targetValue = Long.parseLong(splitLine[0]);
                ArrayList<Integer> values = new ArrayList<>();
                for (String value : splitLine[1].trim().split(" ")) {
                    values.add(Integer.parseInt(value));
                }
                equations.add(new CalibrationEquation(targetValue, values));
            }
        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        // Check if each equations are calibrated
        for (CalibrationEquation equation : equations) {
            if (equation.isCalibrated()) {
                totalCalibrated += equation.getTargetValue();
            }
        }

        System.out.println("Total calibrated equations: " + totalCalibrated);

    }

}
