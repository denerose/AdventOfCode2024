package Day3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 3");

        Pattern instruction = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Pattern pattern = Pattern.compile("(do\\(\\))|(don't\\(\\))|(mul\\(\\d+,\\d+\\))");

        ArrayList<String> matches = new ArrayList<String>();

        long total = 0;

        try {
            File inputFile = new File("Day3/input.txt");
            Scanner input = new Scanner(inputFile);

            while (input.hasNext()) {
                String line = input.nextLine();
                Matcher matcher = pattern.matcher(line);
                matcher.results().forEach(matchResult -> {
                    matches.add(matchResult.group());
                });
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean execute = true;

        for (String match : matches) {
            if (match.equals("do()")) {
                execute = true;
            } else if (match.equals("don't()")) {
                execute = false;
            } else if (instruction.matcher(match).matches() && execute) {
                total += extractAndMultiply(match);
            }
        }

        System.out.println("Total: " + total);
    }

    public static int extractAndMultiply(String match) {
        String[] numsFromString = match.substring(4, match.length() - 1).split(",");
        return Integer.parseInt(numsFromString[0]) * Integer.parseInt(numsFromString[1]);
    }
}
