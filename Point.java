package super_project;

// класс описания точки
public class Point {
    double x; // две координаты
    double y;

    int count; // кол-во раз, сколько точка была вершиной правильного треуг-ка

    // конструктор
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Метод для вычисления расстояния между текущей точкой и переданной в параметры
    public double getLength(Point p2) {
        return Math.sqrt(
                Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2) // теорема Пифагора
        );
    }
}
