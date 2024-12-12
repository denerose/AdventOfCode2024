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

    public void setRow(int row, char[] rowValues) {
        for (int i = 0; i < gridSize; i++) {
            grid[row][i] = rowValues[i];
        }
    }

    public char[] getRow(int row) {
        return grid[row];
    }

    public char getCell(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return '`';
        }
        return grid[row][col];
    }

    public char getCell(Coord c) {
        if (c.getX() < 0 || c.getX() >= gridSize || c.getY() < 0 || c.getY() >= gridSize) {
            return '`';
        }
        return grid[c.getY()][c.getX()];
    }

    public int getCellInt(Coord c) {
        if (c.getX() < 0 || c.getX() >= gridSize || c.getY() < 0 || c.getY() >= gridSize) {
            return -1;
        } else if (Character.isDigit(getCell(c))) {
            return Integer.valueOf(getCell(c));
        }
        return Character.getNumericValue(getCell(c));
    }

    public Coord[] getNeighbors(Coord c) {
        Coord[] neighbors = new Coord[4];
        neighbors[0] = (c.getY() - 1 > 0) ? new Coord(c.getX(), c.getY() - 1) : null;
        neighbors[1] = (c.getX() + 1 < gridSize) ? new Coord(c.getX() + 1, c.getY()) : null;
        neighbors[2] = (c.getY() + 1 < gridSize) ? new Coord(c.getX(), c.getY() + 1) : null;
        neighbors[3] = (c.getX() - 1 > 0) ? new Coord(c.getX() - 1, c.getY()) : null;
        return neighbors;
    }

    public int size() {
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

    public void printGridWithCoords() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print("[" + i + "," + j + "]" + grid[i][j] + " ");
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

    public boolean isSafe(Coord c) {
        return c.getX() >= 0 && c.getX() < gridSize && c.getY() >= 0 && c.getY() < gridSize;
    }

}