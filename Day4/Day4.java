package Day4;

import java.io.File;
import java.util.Scanner;

public class Day4 {

    public static void main(String[] args) {

        char[][] dataLines = new char[140][140];

        try (Scanner scanner = new Scanner(new File("Day4/input.txt"))) {
            int i = 0;
            while (scanner.hasNextLine() && i < dataLines.length) {
                dataLines[i] = scanner.nextLine().toCharArray();
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Xmas count: " + findXmas(dataLines));

    }

    // Part 2

    static int findMasX(char[][] rows) {
        int masCount = 0;

        for (int i = 1; i < rows.length - 1; i++) {

            char[] currentRow = rows[i];

            for (int j = 1; j < currentRow.length - 1; j++) {

                char c = currentRow[j];

                if (c == 'A') {

                }
            }
        }

        return masCount;
    }

    // Part 1

    static int findXmas(char[][] rows) {
        int xmasCount = 0;

        for (int i = 0; i < rows.length; i++) {

            char[] currentRow = rows[i];

            for (int j = 0; j < currentRow.length; j++) {

                char c = currentRow[j];

                if (c == 'X') {
                    if (currentRow.length - j >= 4) {
                        if (isXmasForward(rows, i, j)) {
                            System.out.println("XMAS forward at: " + i + " " + j);
                            xmasCount++;
                        }
                        if (rows.length - i >= 4) {
                            if (isXmasDiagonalLeftDown(rows, i, j)) {
                                System.out.println("XMAS diagonal left down at: " + i + " " + j);
                                xmasCount++;
                            }
                        }
                        if (i >= 3) {
                            if (isXmasDiagonalRightUp(rows, i, j)) {
                                System.out.println("XMAS diagonal right up at: " + i + " " + j);
                                xmasCount++;
                            }
                        }
                    }
                    if (j >= 3) {
                        if (isXmasBackward(rows, i, j)) {
                            System.out.println("XMAS backward at: " + i + " " + j);
                            xmasCount++;
                        }
                        if (rows.length - i >= 4) {
                            if (isXmasDiagonalRightDown(rows, i, j)) {
                                System.out.println("XMAS diagonal right down at: " + i + " " + j);
                                xmasCount++;
                            }
                        }
                        if (i >= 3) {
                            if (isXmasDiagonalLeftUp(rows, i, j)) {
                                System.out.println("XMAS diagonal left up at: " + i + " " + j);
                                xmasCount++;
                            }
                        }
                    }
                    if (rows.length - i >= 4) {
                        if (isXmasDown(rows, i, j)) {
                            System.out.println("XMAS down at: " + i + " " + j);
                            xmasCount++;
                        }
                    }
                    if (i >= 3) {
                        if (isXmasUp(rows, i, j)) {
                            System.out.println("XMAS up at: " + i + " " + j);
                            xmasCount++;
                        }
                    }
                }
            }
        }

        return xmasCount;
    }

    static boolean isXmasForward(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i][j + 1] == 'M' && rows[i][j + 2] == 'A' && rows[i][j + 3] == 'S';
    }

    static boolean isXmasBackward(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i][j - 1] == 'M' && rows[i][j - 2] == 'A' && rows[i][j - 3] == 'S';
    }

    static boolean isXmasDown(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i + 1][j] == 'M' && rows[i + 2][j] == 'A' && rows[i + 3][j] == 'S';
    }

    static boolean isXmasUp(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i - 1][j] == 'M' && rows[i - 2][j] == 'A' && rows[i - 3][j] == 'S';
    }

    static boolean isXmasDiagonalLeftDown(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i + 1][j + 1] == 'M' && rows[i + 2][j + 2] == 'A' && rows[i + 3][j + 3] == 'S';
    }

    static boolean isXmasDiagonalLeftUp(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i - 1][j - 1] == 'M' && rows[i - 2][j - 2] == 'A' && rows[i - 3][j - 3] == 'S';
    }

    static boolean isXmasDiagonalRightDown(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i + 1][j - 1] == 'M' && rows[i + 2][j - 2] == 'A' && rows[i + 3][j - 3] == 'S';
    }

    static boolean isXmasDiagonalRightUp(char[][] rows, int i, int j) {
        return rows[i][j] == 'X' && rows[i - 1][j + 1] == 'M' && rows[i - 2][j + 2] == 'A' && rows[i - 3][j + 3] == 'S';
    }

}
