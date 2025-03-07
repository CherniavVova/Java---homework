package ru.ivanov.geometry;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public Point getStart() {
        return new Point(start.getX(), start.getY());
    }

    public Point getEnd() {
        return new Point(end.getX(), end.getY());
    }

    public void setStart(Point start) {
        this.start = new Point(start.getX(), start.getY());
    }

    public void setEnd(Point end) {
        this.end = new Point(end.getX(), end.getY());
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}

public class Square {
    private Point topLeft;
    private int sideLength;

    public Square(Point topLeft, int sideLength) {
        this.topLeft = new Point(topLeft.getX(), topLeft.getY());
        this.sideLength = sideLength;
    }

    public Square(int x, int y, int sideLength) {
        this.topLeft = new Point(x, y);
        this.sideLength = sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + topLeft + " со стороной " + sideLength;
    }

    public Polyline getPolyline() {
        Point p1 = topLeft;
        Point p2 = new Point(topLeft.getX() + sideLength, topLeft.getY());
        Point p3 = new Point(topLeft.getX() + sideLength, topLeft.getY() - sideLength);
        Point p4 = new Point(topLeft.getX(), topLeft.getY() - sideLength);
        return new Polyline(p1, p2, p3, p4);
    }
}

public class Polyline {
    private Point[] points;

    public Polyline(Point... points) {
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = new Point(points[i].getX(), points[i].getY());
        }
    }

    public double getLength() {
        double length = 0;
        for (int i = 0; i < points.length - 1; i++) {
            length += Math.sqrt(Math.pow(points[i + 1].getX() - points[i].getX(), 2) +
                                Math.pow(points[i + 1].getY() - points[i].getY(), 2));
        }
        return length;
    }

    public void setPoint(int index, Point point) {
        if (index >= 0 && index < points.length) {
            points[index] = new Point(point.getX(), point.getY());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            sb.append(p).append(" ");
        }
        return sb.toString();
    }
}
package ru.ivanov.collections;

public class ImmutableList {
    private int[] array;

    public ImmutableList(int... values) {
        this.array = new int[values.length];
        System.arraycopy(values, 0, this.array, 0, values.length);
    }

    public ImmutableList(ImmutableList other) {
        this(other.array);
    }

    public int get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    public ImmutableList set(int index, int value) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[index] = value;
        return new ImmutableList(newArray);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public int size() {
        return array.length;
    }

    public int[] toArray() {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }
}

public class Point3D extends Point {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }
}
import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<City> connectedCities;
    private List<Integer> costs;

    public City(String name) {
        this.name = name;
        this.connectedCities = new ArrayList<>();
        this.costs = new ArrayList<>();
    }

    public void addConnection(City city, int cost) {
        connectedCities.add(city);
        costs.add(cost);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        for (int i = 0; i < connectedCities.size(); i++) {
            sb.append(" -> ").append(connectedCities.get(i).name).append(":").append(costs.get(i));
        }
        return sb.toString();
    }
}

public class Route {
    private City start;
    private City end;

    public Route(City start, City end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end cities must not be null");
        }
        this.start = start;
        this.end = end;
    }

    public List<City> getRoute() {
        // Simplified route calculation (just a direct connection)
        List<City> route = new ArrayList<>();
        route.add(start);
        route.add(end);
        return route;
    }

    @Override
    public String toString() {
        return "Маршрут от " + start + " до " + end;
    }
}

package ru.ivanov.main;

public interface Summable {
    double sum(double... values);
}

public class Summation implements Summable {
    @Override
    public double sum(double... values) {
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total;
    }
}

public interface Summable {
    double sum(double... values);
}

public class Summation implements Summable {
    @Override
    public double sum(double... values) {
        double total = 0;
        for (double value : values) {
            total += value;
        }
        return total;
    }
}

public class Line {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Line line = (Line) obj;
        return start.equals(line.start) && end.equals(line.end);
    }

    @Override
    public int hashCode() {
        return start.hashCode() + end.hashCode();
    }
}