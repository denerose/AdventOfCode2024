package Helpers;

public class Coord {
    private int x;
    private int y;
    private int d;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
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
        return "[" + y + "," + x + "]";
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
                return new Coord(y - 1, x);

            // right
            case 1:
                return new Coord(y, x + 1);

            // down
            case 2:
                return new Coord(y + 1, x);

            // left
            case 3:
                return new Coord(y, x - 1);

            default:
                return new Coord(y, x);
        }
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
}
