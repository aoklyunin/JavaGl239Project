package project;

public class Point {
    double x;
    double y;

    int count;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getLength(Point p2) {
        return Math.sqrt(
                Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2)
        );
    }
}
