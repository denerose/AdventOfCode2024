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
        ArrayList<Integer> badMids = new ArrayList<>();

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
            boolean good = checkRules(rules, update);
            if (good) {
                int midPoint = update.size() / 2;
                goodMids.add(update.get(midPoint));
            } else {
                badUpdates.add(update.toArray(new Integer[update.size()]));
            }
        }

        int totalP1 = goodMids.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 1: " + totalP1);

        for (Integer[] badUpdate : badUpdates) {
            ArrayList<Integer> badUpdateList = new ArrayList<>(Arrays.asList(badUpdate));
            int mid = reOrderBadUpdate(rules, badUpdateList);
            badMids.add(mid);
        }

        int totalP2 = badMids.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 2: " + totalP2);

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
        return true;
    }

    public static int reOrderBadUpdate(int[][] rules, ArrayList<Integer> update) {
        for (int[] rule : rules) {
            if (update.contains(rule[0]) && update.contains(rule[1])) {
                int splitPoint = update.indexOf(rule[0]);
                ArrayList<Integer> left = new ArrayList<>(update.subList(0, splitPoint));
                if (left.contains(rule[1])) {
                    int index = left.indexOf(rule[1]);
                    update.remove(index);
                    if (update.size() > splitPoint + 1) {
                        update.add(splitPoint + 1, rule[1]);
                    } else {
                        update.add(rule[1]);
                    }
                    reOrderBadUpdate(rules, update);
                }
            }
        }

        return update.get(update.size() / 2);
    }

}