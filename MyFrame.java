package super_project;

// импорты
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

// Описание окна
public class MyFrame extends JFrame implements ActionListener {

    // Создаем новое решение, в параметры передаем какой-то новый лист
    Solution solution = new Solution(new ArrayList<>());

    // сюда мы будем закидывать все точки
    List<Point> points = new ArrayList<>();

    // Кнопка
    JButton button_ADD;

    // 2 Текстовых поля
    JTextField textFieldForX;
    JTextField textFieldForY;

    // Конструктор окна
    public MyFrame(String title) {
        super(title); // устанавливаем название нашего окна на строку, переданную в парметрах

        // Инициализация кнопки
        button_ADD = new JButton("Press me to add new points");
        button_ADD.setBounds(50, 10, 200, 50);
        button_ADD.addActionListener(this);

        // Инициализации текстовых полей
        textFieldForX = new JTextField();
        textFieldForX.setBounds(300, 10, 150, 50);

        textFieldForY = new JTextField();
        textFieldForY.setBounds(450, 10, 150, 50);

        // Добавляем к окну кнопку и 2 текстовых поля
        add(textFieldForX);
        add(textFieldForY);
        add(button_ADD);

        getContentPane().setBackground(Color.green);
        setLayout(null); // для правильного расположения кнопок на окне
        setSize(700, 300); // размеры нашего окна 700 * 300
        setVisible(true); // чтобы наше окно было видно
        setDefaultCloseOperation(EXIT_ON_CLOSE); // чтобы можно было выйти из окна нажав крестик и остановить проект
        addMouseListener(new MyMouseListener()); // добавляем поддержку MouseListener
    }

    // рисовалка
    @Override // переопределение
    public void paint(Graphics g) {
        super.paint(g); //

        // рисуем точки
        // у каждой точки из листа points - зануляем ее счетчик
        // (кол-во раз сколько она была вершиной правильного треугольника)
        for (Point point : points) {
            point.count = 0;
            g.drawOval((int) point.x - 5, (int) point.y - 5, 10, 10);
            g.fillOval((int) point.x - 5, (int) point.y - 5, 10, 10);
        }


        // Перебираем всевозможные тройки точек (points[i], points[j], points[k])
        // такая индексация выбрана неслучайно -
        // она помогает избежать повторения учетнных троек точек
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                for (int k = j + 1; k < points.size(); k++) {

                    // если точки из листа points с индексами (i, j, k) образуют правильный треугольник
                    // -> заходим в этот цикл
                    if (solution.isRightTriangle(points.get(i), points.get(j), points.get(k))) {
                        increaseCount(i, j, k); // увеличиваем счетчик каждой точки
                        Triangle newTriangle = new Triangle(points.get(i), points.get(j), points.get(k)); // создаем новый треугольник по этим трем точкам
                        solution.triangles.add(newTriangle); // добавляем его в общий лист треугольников
                        solution.drawTriangle(g, newTriangle); // рисуем этот треугольник черным цветом
                    }

                }
            }
        }

        // Каждый правильный треугольник, имеющий вершину с счетчиком хотя бы 2
        // Будет отрисован розовым
        // i == {0 .. кол-во всех правильных треуг-ов - 1}
        for (int i = 0; i < solution.triangles.size(); i++) {
            g.setColor(Color.MAGENTA); // меняем цвет на розовый

            // смотрим на вершины какого-то правильного i-ого треугольника
            // если каждая из его вершин имеет count >= 2,
            // то рисуем этот треугольник розовым цветом
            if (solution.triangles.get(i).p1.count >= 2
                    || solution.triangles.get(i).p2.count >= 2
                    || solution.triangles.get(i).p3.count >= 2) {
                solution.drawTriangle(g, solution.triangles.get(i));
            }
        }

        // То же самое для точек
        for (Point point : points) {
            if (point.count >= 2) {
                g.setColor(Color.RED);
                g.drawOval((int) point.x - 5, (int) point.y - 5, 10, 10);
                g.fillOval((int) point.x - 5, (int) point.y - 5, 10, 10);
            }
        }
    }

    // фукнция, увеличивающая счетчик точек с индексами i, j, k из листа points на 1
    private void increaseCount(int i, int j, int k) {
        points.get(i).count++;
        points.get(j).count++;
        points.get(k).count++;
    }

    // Метод обработки нажатия на кнопку
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(textFieldForX.getText()); // получаем информацию из 1го текст. поля
        int y = Integer.parseInt(textFieldForY.getText()); // получаем информацию из 2го текст. поля

        if (e.getSource() == button_ADD) { // если мы нажали button_ADD
            Point point = new Point(x, y); // создаем новую точку (x, y)
            points.add(point); // закидываем эту точку в общий лист
            repaint(); // перерисовываем
        }
    }

    // Обрабатываем нажатия мышкой
    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) { // если мы кликнули мышкой
            Point newPoint = new Point(e.getX(), e.getY()); // создали новую точку
            points.add(newPoint); // добавить эту точку в лист points
            repaint(); // перерисовываем
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    // для запуска окна
    public static void main(String[] args) {
        new MyFrame("project"); // создаем наше окно
    }
}
