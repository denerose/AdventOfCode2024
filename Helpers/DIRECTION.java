package Helpers;

public enum DIRECTION {

    UP(0, -1, 0),
    RIGHT(1, 0, 1),
    DOWN(0, 1, 2),
    LEFT(-1, 0, 3);

    private final int x;
    private final int y;
    private final int d;

    DIRECTION(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getD() {
        return this.d;
    }

    public Coord move(Coord coord) {
        return new Coord(coord.getY() + this.y, coord.getX() + this.x);
    }
}
