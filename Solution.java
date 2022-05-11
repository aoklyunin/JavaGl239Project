package super_project;

// импорты
import java.awt.*;
import java.util.List;

// класс решение
public class Solution {
    // лист треугольников
    List<Triangle> triangles;

    // конструктор решения
    // принимает на вход лист треугольников и по нему создает новое решение
    public Solution(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    // метод отрисовки треугольника
    public void drawTriangle(Graphics g, Triangle triangle) {
        g.drawLine((int) triangle.p1.x, (int) triangle.p1.y, (int) triangle.p2.x, (int) triangle.p2.y);
        g.drawLine((int) triangle.p1.x, (int) triangle.p1.y, (int) triangle.p3.x, (int) triangle.p3.y);
        g.drawLine((int) triangle.p2.x, (int) triangle.p2.y, (int) triangle.p3.x, (int) triangle.p3.y);
    }

    // метод, который возвращает true, если 3 точки из параметров являются вершинами правильного треуг-ка
    // false - иначе
    public boolean isRightTriangle(Point p1, Point p2, Point p3) {
        if (Math.abs(p1.getLength(p2) - p1.getLength(p3)) <= 25) {
            if (Math.abs(p1.getLength(p3) - p2.getLength(p3)) <= 25) {
                if (Math.abs(p1.getLength(p2) - p2.getLength(p3)) <= 25) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}
