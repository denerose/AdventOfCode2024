package Helpers;

import java.util.ArrayList;

public class Grid {

    private final int gridSize;
    private final char[][] grid;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        grid = new char[gridSize][gridSize];
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public void printGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Coord getFirstCoord(char c) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == c) {
                    return new Coord(i, j);
                }
            }
        }
        return null;
    }

    public ArrayList<Coord> getAllCoords(char c) {
        ArrayList<Coord> coords = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == c) {
                    coords.add(new Coord(i, j));
                }
            }
        }
        return coords;
    }

}