package project;

import java.awt.*;
import java.util.List;

public class Solution {
    List<Triangle> triangles;

    public Solution(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public void drawTriangle(Graphics g, Triangle triangle) {
        g.drawLine((int) triangle.p1.x, (int) triangle.p1.y, (int) triangle.p2.x, (int) triangle.p2.y);
        g.drawLine((int) triangle.p1.x, (int) triangle.p1.y, (int) triangle.p3.x, (int) triangle.p3.y);
        g.drawLine((int) triangle.p2.x, (int) triangle.p2.y, (int) triangle.p3.x, (int) triangle.p3.y);
    }

    public boolean isRightTriangle(Point p1, Point p2, Point p3) {
        return Math.abs(p1.getLength(p2) - p1.getLength(p3)) <= 25
                && Math.abs(p1.getLength(p3) - p2.getLength(p3)) <= 25
                && Math.abs(p1.getLength(p2) - p2.getLength(p3)) <= 25;
    }
}
