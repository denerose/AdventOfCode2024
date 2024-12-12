package Day12;

import java.util.ArrayList;
import java.util.HashSet;

import Helpers.Coord;

public class Plot {

    private ArrayList<Coord> plotList;
    Coord startPoint;
    private int area, perimeter, sides;
    public char ch;

    public Plot(Coord startPoint, char ch) {
        plotList = new ArrayList<Coord>();
        plotList.add(startPoint);
        area = 1;
        perimeter = 0;
        this.startPoint = startPoint;
        this.ch = ch;
    }

    Plot() {
        this(new Coord(0, 0), '.');
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Plot)) {
            return false;
        }

        Plot plot = (Plot) obj;

        return plot.startPoint.equals(this.startPoint) && plot.ch == this.ch;
    }

    public int getArea() {
        return plotList.size();
    }

    public int setArea() {
        this.area = plotList.size();
        return area;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void incrementPerimeter() {
        this.perimeter++;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public int checkPerimiter() {

        HashSet<String> visited = new HashSet<>();
        plotList.stream().forEach((c) -> {
            visited.add(c.toString());
        });

        int testValue = 0;

        for (Coord c : plotList) {
            for (Coord neighbor : c.getNeighbors()) {
                if (!visited.contains(neighbor.toString())) {
                    testValue++;
                }
            }
        }

        this.perimeter = testValue;

        return perimeter;
    }

    public ArrayList<Coord> getPlotList() {
        return plotList;
    }

    public void addCoord(Coord coord) {
        if (!plotList.contains(coord)) {
            plotList.add(coord);
            setArea();
        }
    }

    public Coord getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Coord startPoint) {
        this.startPoint = startPoint;
    }

    public int getPrice() {
        return plotList.size() * perimeter;
    }

    public int getBulkPrice() {
        return getSides() * setArea();
    }

    public int getSides() {
        HashSet<String> allPlots = new HashSet<>();

        sides = 0;

        plotList.stream().forEach((c) -> {
            allPlots.add(c.toString());
        });

        for (Coord c : plotList) {
            Coord up = c.getAdjacentCoord(0);
            Coord right = c.getAdjacentCoord(1);
            Coord down = c.getAdjacentCoord(2);
            Coord left = c.getAdjacentCoord(3);
            Coord upLeft = c.step(-1, -1);
            Coord upRight = c.step(-1, 1);
            Coord downLeft = c.step(1, -1);
            Coord downRight = c.step(1, 1);

            // find sides by finding corners
            if (!allPlots.contains(up.toString()) && !allPlots.contains(left.toString())) {
                sides++;
            }

            if (!allPlots.contains(up.toString()) && !allPlots.contains(right.toString())) {
                sides++;
            }

            if (!allPlots.contains(down.toString()) && !allPlots.contains(right.toString())) {
                sides++;
            }

            if (!allPlots.contains(down.toString()) && !allPlots.contains(left.toString())) {
                sides++;
            }

            if (allPlots.contains(up.toString())) {
                if (allPlots.contains(left.toString()) && !allPlots.contains(upLeft.toString())) {
                    sides++;
                }

                if (allPlots.contains(right.toString()) && !allPlots.contains(upRight.toString())) {
                    sides++;
                }
            }

            if (allPlots.contains(down.toString())) {
                if (allPlots.contains(left.toString()) && !allPlots.contains(downLeft.toString())) {
                    sides++;
                }

                if (allPlots.contains(right.toString()) && !allPlots.contains(downRight.toString())) {
                    sides++;
                }
            }
        }

        return sides;
    }

}
