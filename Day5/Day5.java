import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;

public class Day5 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024 - Day 5!");

        int[][] rules = new int[21][2];
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        ArrayList<Integer[]> goodUpdates = new ArrayList<>();
        ArrayList<Integer> goodMids = new ArrayList<>();

        try (Scanner rulesScan = new Scanner(new File("Day5/test.txt"))) {
            int i = 0;

            while (rulesScan.hasNextLine() && i < rules.length) {
                String[] line = rulesScan.nextLine().split("|");
                rules[i][0] = Integer.parseInt(line[0]);
                rules[i][1] = Integer.parseInt(line[1]);
                System.out.println("Rule: " + rules[i][0] + " " + rules[i][1]);
                i++;
            }

            rulesScan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Scanner lineScan = new Scanner(new File("Day5/arraytest.txt"))) {
            while (lineScan.hasNextLine()) {
                updates.add(new ArrayList<>(Arrays.stream(lineScan.nextLine().split(",")).map(Integer::valueOf).collect(Collectors.toList())));
            }

            lineScan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ArrayList<Integer> update : updates) {
            System.out.println("Update: " + update);
            for (int[] rule : rules) {
                
            }
        }
        
    }

}