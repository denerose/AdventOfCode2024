package Day14;

import Helpers.Coord;

public class Robot {

    private Coord position, startCoord, velocity;
    private int maxX = 101, maxY = 103;

    public Robot(Coord startCoord, Coord velocity) {
        this.position = startCoord;
        this.startCoord = startCoord;
        this.velocity = velocity;
    }

    public Coord getStartCoord() {
        return startCoord;
    }

    public void reset() {
        position = startCoord;
    }

    public Coord getVelocity() {
        return velocity;
    }

    public void setVelocity(Coord velocity) {
        this.velocity = velocity;
    }

    public void setWalls(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Coord move(int seconds) {

        int x = position.getX();
        int y = position.getY();

        for (int i = 0; i < seconds; i++) {
            x += velocity.getX();
            y += velocity.getY();

            if (x < 0) {
                x = x + maxX;
            } else if (x >= maxX) {
                x = x - maxX;
            }

            if (y < 0) {
                y = y + maxY;
            } else if (y >= maxY) {
                y = y - maxY;
            }
        }

        position = new Coord(y, x);
        return position;
    }

    public Coord step() {
        int x = position.getX() + velocity.getX();
        int y = position.getY() + velocity.getY();

        if (x < 0) {
            x = x + maxX;
        } else if (x >= maxX) {
            x = x - maxX;
        }

        if (y < 0) {
            y = y + maxY;
        } else if (y >= maxY) {
            y = y - maxY;
        }
        position = new Coord(y, x);
        return position;
    }

    public Coord getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Robot [position=" + position + ", startCoord=" + startCoord + ", velocity=" + velocity + "]";
    }
}
