package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 2");

        int countSafe = 0;

        try {
            File inputFile = new File("Day2/input.txt");
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] values = line.split(" ");
                ArrayList<Integer> intValues = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    intValues.add(Integer.parseInt(values[i]));
                }
                if (isSafeWithSave(intValues, true)) {
                    countSafe++;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Safe Count: " + countSafe);
    }

    static boolean isSafeWithSave(ArrayList<Integer> values, boolean saveAvailable) {

        boolean increasing = false;
        boolean decreasing = false;

        int errorCount = 0;
        int errorIndex = -1;

        for (int i = 0; i < values.size() - 1; i++) {

            int diff = Math.abs(values.get(i) - values.get(i + 1));
            if (diff > 3 || diff < 1) {
                if (saveAvailable) {
                    errorCount++;
                    saveAvailable = false;
                    if (i != 0) {
                        int newDiff = Math.abs(values.get(i - 1) - values.get(i + 1));
                        if (newDiff <= 3 && newDiff >= 1) {
                            values.remove(i);
                            return isSafeWithSave(values, false);
                        }
                    }
                    if (values.size() > i + 2) {
                        int newDiff = Math.abs(values.get(i) - values.get(i + 2));
                        if (newDiff <= 3 && newDiff >= 1) {
                            values.remove(i + 1);
                            return isSafeWithSave(values, false);
                        }
                    }
                } else {
                    return false;
                }
            }

            if (values.get(i) < values.get(i + 1)) {
                increasing = true;
                if (decreasing) {
                    if (saveAvailable) {
                        errorCount++;
                        saveAvailable = false;
                        if ((values.get(i - 1) < values.get(i + 1))) {
                            values.remove(i);
                            return isSafeWithSave(values, false);
                        } else {
                            values.remove(i - 1);
                            return isSafeWithSave(values, false);
                        }
                    } else {
                        return false;
                    }
                }
            } else if (values.get(i) > values.get(i + 1)) {
                if (increasing) {
                    if (saveAvailable) {
                        errorCount++;
                        saveAvailable = false;
                        if ((values.get(i - 1) < values.get(i + 1))) {
                            values.remove(i);
                            return isSafeWithSave(values, false);
                        } else {
                            values.remove(i - 1);
                            return isSafeWithSave(values, false);
                        }
                    } else {
                        return false;
                    }
                }
                decreasing = true;
            }
        }

        return ((increasing && !decreasing) || (!increasing && decreasing));

    }

    static boolean isSafeTask1(int[] values) {

        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 0; i < values.length - 1; i++) {

            int diff = Math.abs(values[i] - values[i + 1]);
            if (diff > 3 || diff < 1) {
                return false;
            }
            if (values[i] < values[i + 1]) {
                increasing = true;
                if (decreasing) {
                    return false;
                }
            } else if (values[i] > values[i + 1]) {
                decreasing = true;
                if (increasing) {
                    return false;
                }
            }
        }

        return ((increasing && !decreasing) || (!increasing && decreasing));
    }
}
