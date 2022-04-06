package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFrame extends JFrame {

    /*
        Данный метод помогает читать точки из файла
    */
    public void getPointsFromFile() {
        points.clear();
        try {
            File file = new File("in.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                Point point = new Point(x, y);
                points.add(point);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("U did something wrong: " + e);
        }
    }

    Solution solution = new Solution(new ArrayList<>());
    List<Point> points = new ArrayList<>();

    public MyFrame(String title) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(new MyMouseListener());
        setSize(300, 300);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);

        for (Point value : points) {
            value.count = 0;
            g.drawOval((int) value.x - 5, (int) value.y - 5, 10, 10);
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                for (int k = j + 1; k < points.size(); k++) {
                    if (solution.isRightTriangle(points.get(i), points.get(j), points.get(k))) {
                        increaseCount(i, j, k);
                        Triangle newTriangle = new Triangle(points.get(i), points.get(j), points.get(k));
                        solution.triangles.add(newTriangle);
                        solution.drawTriangle(g, newTriangle);
                    }
                }
            }
        }

        for (int i = 0; i < solution.triangles.size(); i++) {
            g.setColor(Color.MAGENTA);

            if (solution.triangles.get(i).p1.count >= 2
                    || solution.triangles.get(i).p2.count >= 2
                    || solution.triangles.get(i).p3.count >= 2) {
                solution.drawTriangle(g, solution.triangles.get(i));
            }
        }

        for (Point point : points) {
            if (point.count >= 2) {
                g.setColor(Color.RED);
                g.drawOval((int) point.x - 5, (int) point.y - 5, 10, 10);
                g.fillOval((int) point.x - 5, (int) point.y - 5, 10, 10);
            }
        }
    }

    private void increaseCount(int i, int j, int k) {
        points.get(i).count++;
        points.get(j).count++;
        points.get(k).count++;
    }

    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point newPoint = new Point(e.getX(), e.getY());
            points.add(newPoint);
            repaint();
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
}
