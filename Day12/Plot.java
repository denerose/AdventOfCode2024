package Day12;

import java.util.ArrayList;
import java.util.HashSet;

import Helpers.Coord;

public class Plot {

    private ArrayList<Coord> plotList;
    Coord startPoint;
    private int area, perimeter;
    char ch;

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

    public void setArea() {
        this.area = plotList.size();
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

}
