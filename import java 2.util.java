import java.util.Arrays;
import java.util.Scanner;

class Point {
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

    // Сеттеры
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

class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }

    public int getLength() {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}

class Student {
    private String name;
    private int[] grades;

    public Student(String name, int[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int[] getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(grades);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задание 1.1: Точка
        System.out.println("Задание 1.1: Точка");
        Point point1 = new Point(1, 3);
        Point point2 = new Point(23, 8);
        Point point3 = new Point(5, 10);
        System.out.println("Точка 1: " + point1);
        System.out.println("Точка 2: " + point2);
        System.out.println("Точка 3: " + point3);

        // Задание 2.1: Линия
        System.out.println("\nЗадание 2.1: Линия");
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(5, 10, 25, 10);
        Line line3 = new Line(line1.getStart(), line2.getEnd());
        System.out.println("Линия 1: " + line1);
        System.out.println("Линия 2: " + line2);
        System.out.println("Линия 3: " + line3);

        // Изменение координат первой и второй линий
        System.out.println("\nИзменение координат первой и второй линий:");
        point1.setX(2);
        point1.setY(4);
        point2.setX(24);
        point2.setY(9);
        System.out.println("Линия 1 после изменения: " + line1);
        System.out.println("Линия 3 после изменения: " + line3);

        // Изменение координат первой линии без изменения третьей
        System.out.println("\nИзменение координат первой линии без изменения третьей:");
        Point newStart = new Point(3, 5);
        line1 = new Line(newStart, line1.getEnd());
        System.out.println("Линия 1 после изменения: " + line1);
        System.out.println("Линия 3 после изменения: " + line3);

        // Задание 3.1: Студент
        System.out.println("\nЗадание 3.1: Студент");
        int[] vasyaGrades = {3, 4, 5};
        Student vasya = new Student("Вася", vasyaGrades);
        Student petya = new Student("Петя", vasyaGrades.clone());
        petya.getGrades()[0] = 5;
        System.out.println("Вася: " + vasya);
        System.out.println("Петя: " + petya);

        // Создание студента Андрея с независимой копией оценок
        int[] andreyGrades = Arrays.copyOf(vasyaGrades, vasyaGrades.length);
        Student andrey = new Student("Андрей", andreyGrades);
        System.out.println("Андрей: " + andrey);

        // Задание 4.1: Создаем Точку
        System.out.println("\nЗадание 4.1: Создаем Точку");
        Point point4 = new Point(3, 5);
        Point point5 = new Point(25, 6);
        Point point6 = new Point(7, 8);
        System.out.println("Точка 4: " + point4);
        System.out.println("Точка 5: " + point5);
        System.out.println("Точка 6: " + point6);

        // Задание 4.2: Создаем Линию
        System.out.println("\nЗадание 4.2: Создаем Линию");
        Line line4 = new Line(point4, point5);
        Line line5 = new Line(5, 10, 25, 10);
        Line line6 = new Line(line4.getStart(), line5.getEnd());
        System.out.println("Линия 4: " + line4);
        System.out.println("Линия 5: " + line5);
        System.out.println("Линия 6: " + line6);

        // Задание 5.3: Длина Линии
        System.out.println("\nЗадание 5.3: Длина Линии");
        System.out.print("Введите координаты начала линии (x1 y1): ");
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        System.out.print("Введите координаты конца линии (x2 y2): ");
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        Line line7 = new Line(x1, y1, x2, y2);
        System.out.println("Длина линии: " + line7.getLength());

        scanner.close();
    }
}