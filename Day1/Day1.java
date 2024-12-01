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

    }
}