import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;

public class Day5 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024 - Day 5!");

        int[][] rules = new int[1176][2];
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        ArrayList<Integer[]> badUpdates = new ArrayList<>();
        ArrayList<Integer> goodMids = new ArrayList<>();

        try (Scanner rulesScan = new Scanner(new File("Day5/input.txt"))) {
            int i = 0;

            while (rulesScan.hasNextLine() && i < rules.length) {
                String[] line = rulesScan.nextLine().split("\\|");
                rules[i][0] = Integer.parseInt(line[0]);
                rules[i][1] = Integer.parseInt(line[1]);
                i++;
            }

            rulesScan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Scanner lineScan = new Scanner(new File("Day5/array.txt"))) {
            while (lineScan.hasNextLine()) {
                updates.add(new ArrayList<>(Arrays.stream(lineScan.nextLine().split(",")).map(Integer::valueOf)
                        .collect(Collectors.toList())));
            }

            lineScan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ArrayList<Integer> update : updates) {
            System.out.println("Update: " + update);
            boolean good = checkRules(rules, update);
            if (good) {
                int midPoint = update.size() / 2;
                goodMids.add(update.get(midPoint));
            } else {
                badUpdates.add(update.toArray(new Integer[update.size()]));
            }
        }

        int total = goodMids.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 1: " + total);

    }

    public static boolean checkRules(int[][] rules, ArrayList<Integer> update) {
        for (int[] rule : rules) {
            if (update.contains(rule[0]) && update.contains(rule[1])) {

                int splitPoint = update.indexOf(rule[0]);
                ArrayList<Integer> left = new ArrayList<>(update.subList(0, splitPoint));
                if (left.contains(rule[1])) {
                    return false;
                }
            }
        }
        System.out.println("Good: " + update);
        return true;
    }

    public static int reOrderBadUpdate(int[][] rules, ArrayList<Integer> update) {

    }

}