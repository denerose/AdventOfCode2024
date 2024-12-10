package Helpers;

public class Coord {
    private int x;
    private int y;
    private int d;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        this.d = -1;
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
        return "[" + y + ", " + x + "]";
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
                return new Coord(x, y - 1);

            // right
            case 1:
                return new Coord(x + 1, y);

            // down
            case 2:
                return new Coord(x, y + 1);

            // left
            case 3:
                return new Coord(x - 1, y);
            default:
                return new Coord(x, y);
        }
    }

    public int[] getDifference(Coord c) {
        return new int[] { x - c.getX(), y - c.getY() };
    }

    public int getTotalDifference(Coord c) {
        return Math.abs(x - c.getX()) + Math.abs(y - c.getY());
    }

    public Coord getMidPoint(Coord c) {
        return new Coord((x + c.getX()) / 2, (y + c.getY()) / 2);
    }

    public Coord addDistance(int[] diff) {
        return new Coord(x + diff[0], y + diff[1]);
    }
}
