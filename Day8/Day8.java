package Day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Day8 {

    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 8");

        final int GRIDSIZE = 50;
        HashMap<Character, ArrayList<int[]>> antennaLocations = new HashMap<>();
        ArrayList<String> antinodeLocations = new ArrayList<>();
        ArrayList<int[]> additonalAntinodes = new ArrayList<>();

        char[][] grid = new char[GRIDSIZE][GRIDSIZE];

        // Read input from file
        try (Scanner scanner = new Scanner(new File("Day8/input.txt"))) {
            int i = 0;
            while (scanner.hasNextLine() && i < GRIDSIZE) {
                String line = scanner.nextLine();
                grid[i] = line.toCharArray();
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }

        // Traverse the grid and store the antenna locations
        for (int y = 0; y < GRIDSIZE; y++) {
            for (int x = 0; x < GRIDSIZE; x++) {
                char item = grid[y][x];
                if (item != '.') {
                    if (antennaLocations.containsKey(item)) {
                        ArrayList<int[]> locations = antennaLocations.get(item);
                        int[] location = { y, x };
                        locations.add(location);
                        antennaLocations.put(item, locations);
                    } else {
                        ArrayList<int[]> locations = new ArrayList<>();
                        int[] location = { y, x };
                        locations.add(location);
                        antennaLocations.put(item, locations);
                    }
                }
            }
        }

        // Find the antinode locations
        for (char key : antennaLocations.keySet()) {
            ArrayList<int[]> locations = antennaLocations.get(key);
            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0 + 1; j < locations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int[] location1 = locations.get(i);
                    int[] location2 = locations.get(j);
                    int[][] nodes = findSignalNodes(location1[0], location1[1], location2[0], location2[1]);
                    addNodes(nodes, antinodeLocations, GRIDSIZE);
                }
            }
        }

        System.out.println("Part 1: " + antinodeLocations.size());

        // Find additional nodes for Part 2
        for (char key : antennaLocations.keySet()) {
            ArrayList<int[]> locations = antennaLocations.get(key);
            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0 + 1; j < locations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int[] location1 = locations.get(i);
                    int[] location2 = locations.get(j);
                    ArrayList<int[]> newNodes = findSignalNodesRecursive(location1, location2, additonalAntinodes,
                            GRIDSIZE);
                    addNodes(newNodes, antinodeLocations, GRIDSIZE);
                }
            }
        }

        System.out.println("Total locations: " + antinodeLocations.size());

    }

    static int[][] findSignalNodes(int y1, int x1, int y2, int x2) {
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        int[][] nodes = new int[2][2];
        nodes[0][1] = (x1 + xDiff);
        nodes[0][0] = (y1 + yDiff);
        nodes[1][1] = (x2 - xDiff);
        nodes[1][0] = (y2 - yDiff);
        return nodes;
    }

    static ArrayList<int[]> findSignalNodesRecursive(int[] loc1, int[] loc2, ArrayList<int[]> nodes, int gridSize) {
        int xDiff = loc1[1] - loc2[1];
        int yDiff = loc1[0] - loc2[0];
        int[] node1 = { loc1[0] + yDiff, loc1[1] + xDiff };
        int[] node2 = { loc2[0] - yDiff, loc2[1] - xDiff };
        nodes.add(loc1);
        nodes.add(loc2);
        nodes.add(node1);
        nodes.add(node2);
        if ((node1[0] >= 0 && node1[0] < gridSize) || (node1[1] >= 0 && node1[1] < gridSize)) {
            findSignalNodesRecursive(node1, node2, nodes, xDiff, yDiff, gridSize);
        }
        return nodes;
    }

    static ArrayList<int[]> findSignalNodesRecursive(int[] loc1, int[] loc2, ArrayList<int[]> nodes, int xDiff,
            int yDiff, int gridSize) {
        int[] node1 = { loc1[0] + yDiff, loc1[1] + xDiff };
        int[] node2 = { loc2[0] - yDiff, loc2[1] - xDiff };
        nodes.add(node1);
        nodes.add(node2);
        if ((node1[0] >= 0 && node1[0] < gridSize && node1[1] >= 0 && node1[1] < gridSize) || (node2[0] >= 0
                && node2[0] < gridSize && node2[1] >= 0 && node2[1] < gridSize)) {
            findSignalNodesRecursive(node1, node2, nodes, xDiff, yDiff, gridSize);
        }
        return nodes;
    }

    static void addNodes(int[][] nodes, ArrayList<String> antinodeLocations, int gridSize) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i][0] < 0 || nodes[i][0] >= gridSize || nodes[i][1] < 0 || nodes[i][1] >= gridSize) {
                continue;
            }
            String nodeString = nodes[i][0] + "-" + nodes[i][1];
            if (!antinodeLocations.contains(nodeString)) {
                antinodeLocations.add(nodeString);
            }
        }
    }

    static void addNodes(ArrayList<int[]> nodes, ArrayList<String> antinodeLocations, int gridSize) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i)[0] < 0 || nodes.get(i)[0] >= gridSize || nodes.get(i)[1] < 0
                    || nodes.get(i)[1] >= gridSize) {
                continue;
            }
            String nodeString = nodes.get(i)[0] + "-" + nodes.get(i)[1];
            if (!antinodeLocations.contains(nodeString)) {
                antinodeLocations.add(nodeString);
            }
        }
    }
}
