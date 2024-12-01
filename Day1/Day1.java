package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024!");

        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();

        try {
            File inputFile = new File("Day1/input.txt");
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                Integer n1 = input.nextInt();
                list1.add(n1);
                Integer n2 = input.nextInt();
                list2.add(n2);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list1.sort(null);
        list2.sort(null);

        int total = task1(list1, list2);
        System.out.println("Task 1 Total: " + total);

        int similarity = 0;
        for (int i = 0; i < list1.size(); i++) {
            similarity += getSimilarity(list1.get(i), list2);
        }
        System.out.println("Task 2 Similarity: " + similarity);

    }

    static int task1(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int total = 0;

        for (int i = 0; i < list1.size(); i++) {
            total += getDifference(list1.get(i), list2.get(i));
        }

        return total;
    }

    static int getSimilarity(int target, ArrayList<Integer> list) {
        int similarity = 0;

        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);

            if (num > target) {
                break;
            }
            if (num == target) {
                similarity++;
            }
        }

        return target * similarity;
    }

    static int getDifference(int n1, int n2) {
        return Math.abs(n1 - n2);
    }
}