package Helpers;

import java.util.ArrayList;

public class Grid {

    private final int gridSize, maxX, maxY;

    private final char[][] grid;
    Coord mid;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.maxX = gridSize;
        this.maxY = gridSize;
        mid = new Coord(gridSize / 2, gridSize / 2);
        grid = new char[gridSize][gridSize];
    }

    public Grid(int maxY, int maxX) {
        this.gridSize = (maxX > maxY) ? maxX : maxY;
        this.maxX = maxX;
        this.maxY = maxY;
        mid = new Coord(maxY / 2, maxX / 2);
        grid = new char[maxY][maxX];
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void setRow(int row, char[] rowValues) {
        for (int i = 0; i < maxX; i++) {
            grid[row][i] = rowValues[i];
        }
    }

    public void setCell(Coord c, char value) {
        grid[c.getY()][c.getX()] = value;
    }

    public char[] getRow(int row) {
        return grid[row];
    }

    public char getCell(int row, int col) {
        if (row < 0 || row >= maxY || col < 0 || col >= maxX) {
            return '`';
        }
        return grid[row][col];
    }

    public char getCell(Coord c) {
        if (c.getX() < 0 || c.getX() >= maxX || c.getY() < 0 || c.getY() >= maxY) {
            return '`';
        }
        return grid[c.getY()][c.getX()];
    }

    public int getCellInt(Coord c) {
        if (c.getX() < 0 || c.getX() >= maxX || c.getY() < 0 || c.getY() >= maxY) {
            return -1;
        } else if (Character.isDigit(getCell(c))) {
            return Integer.valueOf(getCell(c));
        }
        return Character.getNumericValue(getCell(c));
    }

    public Coord[] getNeighbors(Coord c) {
        Coord[] neighbors = new Coord[4];
        neighbors[0] = (c.getY() - 1 > 0) ? new Coord(c.getX(), c.getY() - 1) : null;
        neighbors[1] = (c.getX() + 1 < maxX) ? new Coord(c.getX() + 1, c.getY()) : null;
        neighbors[2] = (c.getY() + 1 < maxX) ? new Coord(c.getX(), c.getY() + 1) : null;
        neighbors[3] = (c.getX() - 1 > 0) ? new Coord(c.getX() - 1, c.getY()) : null;
        return neighbors;
    }

    public char getMidCell() {
        return grid[mid.getY()][mid.getX()];
    }

    public int size() {
        return (maxY > gridSize) ? maxY : gridSize;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void printGrid() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printGridWithCoords() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print("[" + i + "," + j + "]" + grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Coord getFirstCoord(char c) {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (grid[i][j] == c) {
                    return new Coord(i, j);
                }
            }
        }
        return null;
    }

    public ArrayList<Coord> getAllCoords(char c) {
        ArrayList<Coord> coords = new ArrayList<>();
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (grid[i][j] == c) {
                    coords.add(new Coord(i, j));
                }
            }
        }
        return coords;
    }

    public boolean isSafe(Coord c) {
        return c.getX() >= 0 && c.getX() < maxX && c.getY() >= 0 && c.getY() < maxY;
    }

}