package Day16;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

import Helpers.Coord;
import Helpers.Grid;

import java.io.File;

public class Day16 {
    public static void main(String[] args) {
        System.out.println("Hello, Advent of Code 2024: Day 16");

        final int GRIDSIZE = 15;
        Grid maze = new Grid(GRIDSIZE, GRIDSIZE);

        // Read input from file
        readInput(maze);
        maze.printGrid();

        Coord start = maze.getFirstCoord('S');
        start.setD(1);
        Coord end = maze.getFirstCoord('E');

        int shortestPath = shortestPath(maze, start, end);

        System.out.println("Shortest path from S to E: " + shortestPath);

    }

    // Dijkstra’s shortest path algorithm, to find the shortest path from start to
    // end using Coord nodes
    static int shortestPath(Grid maze, Coord start, Coord end) {
        final int INF = Integer.MAX_VALUE; // Infinity

        int n = maze.getMaxY();
        int m = maze.getMaxX();

        // arrays representing the directions for moving in the maze
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        // 2D arrays representing the shortest distances and visited cells
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        // Initialize the distance array, set all to INF apart from the start node
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[start.getY()][start.getX()] = 0;

        // create a priority queue for storing cells to visit
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] { start.getY(), start.getX(), 0 });

        // iterate while there are cells to visit
        while (!pq.isEmpty()) {
            // remove the cell with the smallest distance from the priority queue
            int[] curr = pq.poll();
            int y = curr[0];
            int x = curr[1];
            int score = curr[2];

            // if the cell has already been visited, skip it
            if (visited[y][x]) {
                continue;
            }

            // mark the cell as visited
            visited[y][x] = true;

            // if we've reached the end cell, return the shortest distance
            if (x == end.getX() && y == end.getY()) {
                return score;
            }

            // iterate over the neighboring cells and update their distances if necessary
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze.getCell(ny, nx) != '#' && !visited[nx][ny]) {
                    int nd = score + 1;
                    if (nd < dist[ny][nx]) {
                        dist[ny][nx] = nd;
                        pq.offer(new int[] { ny, nx, nd });
                    }
                }
            }
        }

        // if we couldn't reach the end cell, return -1
        return -1;

    }

    // Read input from file
    public static void readInput(Grid grid) {
        try (Scanner scanner = new Scanner(new File("Day16/test.txt"))) {

            int i = 0;
            while (scanner.hasNextLine() && i < grid.size()) {
                String line = scanner.nextLine();
                grid.setRow(i, line.trim().toCharArray());
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
    }

}
