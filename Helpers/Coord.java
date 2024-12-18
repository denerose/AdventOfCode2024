package Helpers;

import java.util.Map;

public class Coord {
    private int x;
    private int y;
    private int d;
    Map<String, Integer> directions = Map.of("up", 0, "right", 1, "down", 2, "left", 3, "diagUpRight", 4,
            "diagDownRight", 5,
            "diagDownLeft", 6, "diagUpLeft", 7);

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
        this.d = -1;
    }

    public Coord(int y, int x, int direction) {
        this.y = y;
        this.x = x;
        this.d = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int direction) {
        this.d = direction;
    }

    @Override
    public String toString() {
        if (d < 0) {
            return "[" + y + "," + x + "]";
        }
        return "[" + y + "," + x + "," + d + "]";
    }

    @Override
    public boolean equals(Object object) {
        boolean sameSame = false;

        if (object != null && object instanceof Coord) {
            sameSame = this.x == ((Coord) object).x && this.y == ((Coord) object).y;
        }

        return sameSame;
    }

    public boolean equals(Coord c) {
        return x == c.getX() && y == c.getY();
    }

    public boolean isAdjacent(Coord c) {
        return Math.abs(x - c.getX()) + Math.abs(y - c.getY()) == 1;
    }

    public Coord getAdjacentCoord(int direction) {
        switch (direction) {
            // up
            case 0:
                return new Coord(y - 1, x, 0);

            // right
            case 1:
                return new Coord(y, x + 1, 1);

            // down
            case 2:
                return new Coord(y + 1, x, 2);

            // left
            case 3:
                return new Coord(y, x - 1, 3);

            // diagUpRight
            case 4:
                return new Coord(y - 1, x + 1, 4);

            // diagDownRight
            case 5:
                return new Coord(y + 1, x + 1, 5);

            // diagDownLeft
            case 6:
                return new Coord(y + 1, x - 1, 6);

            // diagUpLeft
            case 7:
                return new Coord(y - 1, x - 1, 7);

            default:
                return new Coord(y, x, -1);
        }
    }

    public Coord getAdjacentCoord(String direction) {
        return getAdjacentCoord(directions.get(direction));
    }

    public Coord[] getNeighbors() {
        Coord[] neighbors = new Coord[4];
        neighbors[0] = new Coord(y - 1, x);
        neighbors[1] = new Coord(y, x + 1);
        neighbors[2] = new Coord(y + 1, x);
        neighbors[3] = new Coord(y, x - 1);
        return neighbors;
    }

    public int[] getDifference(Coord c) {
        return new int[] { y - c.getY(), x - c.getX() };
    }

    public int getTotalDifference(Coord c) {
        return Math.abs(x - c.getX()) + Math.abs(y - c.getY());
    }

    public Coord getMidPoint(Coord c) {
        return new Coord((x + c.getX()) / 2, (y + c.getY()) / 2);
    }

    public Coord addDistance(int[] diff) {
        return new Coord(y + diff[0], x + diff[1]);
    }

    public Coord addDistance(int yDist, int xDist) {
        return new Coord(y + yDist, x + xDist);
    }

    public Coord addDistance(Coord c) {
        return new Coord(y + c.getY(), x + c.getX());
    }

    public Coord step(int yDist, int xDist) {
        return new Coord(y + yDist, x + xDist);
    }

    public int findSlope(Coord c) {
        return (c.getY() - y) / (c.getX() - x);
    }
}
