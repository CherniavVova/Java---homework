import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.*;

public class Fraction {
    private int numerator;
    private int denominator;
    private Double cachedValue;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double getDecimalValue() {
        if (cachedValue == null) {
            cachedValue = (double) numerator / denominator;
        }
        return cachedValue;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        this.cachedValue = null;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        this.denominator = denominator;
        this.cachedValue = null;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
public class Cat implements Meowable {
    private String name;
    private int meowCount;

    public Cat(String name) {
        this.name = name;
        this.meowCount = 0;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
        meowCount++;
    }

    public int getMeowCount() {
        return meowCount;
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }
}

interface Meowable {
    void meow();
}

public class ListUtils {
    public static <E> void removeAllOccurrences(List<E> list, E element) {
        list.removeIf(e -> e.equals(element));
    }
}

public class StudentLoginGenerator {
    public static Map<String, String> generateLogins(String[] students) {
        Map<String, String> logins = new HashMap<>();
        Map<String, Integer> surnameCount = new HashMap<>();

        for (String student : students) {
            String surname = student.split(" ")[0];
            int count = surnameCount.getOrDefault(surname, 0) + 1;
            surnameCount.put(surname, count);

            String login = (count == 1) ? surname : surname + count;
            logins.put(student, login);
        }

        return logins;
    }
}

public class ConsonantFinder {
    private static final Set<Character> VOICED_CONSONANTS = Set.of('б', 'в', 'г', 'д', 'ж', 'з', 'й', 'л', 'м', 'н', 'р');

    public static Set<Character> findVoicedConsonants(String text) {
        Set<Character> result = new TreeSet<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (VOICED_CONSONANTS.contains(c)) {
                result.add(c);
            }
        }
        return result;
    }
}

public class QueueReverser {
    public static <E> void printReversed(Queue<E> queue) {
        Stack<E> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}

public class PolylineProcessor {
    public static Polyline processPoints(List<Point> points) {
        List<Point> processedPoints = points.stream()
                .distinct()
                .sorted((p1, p2) -> Double.compare(p1.getX(), p2.getX()))
                .map(p -> new Point(p.getX(), Math.abs(p.getY())))
                .collect(Collectors.toList());
        return new Polyline(processedPoints);
    }
}

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

class Polyline {
    private List<Point> points;

    public Polyline(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Линия " + points;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задание 1: Дробь
        System.out.println("Задание 1: Дробь");
        System.out.print("Введите числитель: ");
        int numerator = scanner.nextInt();
        System.out.print("Введите знаменатель: ");
        int denominator = scanner.nextInt();
        Fraction fraction = new Fraction(numerator, denominator);
        System.out.println("Дробь: " + fraction);
        System.out.println("Вещественное значение: " + fraction.getDecimalValue());

        // Задание 2: Кот
        System.out.println("\nЗадание 2: Кот");
        System.out.print("Введите имя кота: ");
        String catName = scanner.next();
        Cat cat = new Cat(catName);
        System.out.print("Сколько раз кот должен мяукнуть? ");
        int meowCount = scanner.nextInt();
        for (int i = 0; i < meowCount; i++) {
            cat.meow();
        }
        System.out.println("Количество мяуканий: " + cat.getMeowCount());

        // Задание 3: Удаление элементов из списка
        System.out.println("\nЗадание 3: Удаление элементов из списка");
        System.out.print("Введите элементы списка через пробел: ");
        scanner.nextLine(); // Очистка буфера
        String[] listInput = scanner.nextLine().split(" ");
        List<Integer> list = new ArrayList<>();
        for (String s : listInput) {
            list.add(Integer.parseInt(s));
        }
        System.out.print("Введите элемент для удаления: ");
        int elementToRemove = scanner.nextInt();
        ListUtils.removeAllOccurrences(list, elementToRemove);
        System.out.println("Список после удаления: " + list);

        // Задание 4: Генерация логинов
        System.out.println("\nЗадание 4: Генерация логинов");
        System.out.print("Введите количество учеников: ");
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        String[] students = new String[studentCount];
        for (int i = 0; i < studentCount; i++) {
            System.out.print("Введите Фамилию и Имя ученика " + (i + 1) + ": ");
            students[i] = scanner.nextLine();
        }
        Map<String, String> logins = StudentLoginGenerator.generateLogins(students);
        System.out.println("Логины: " + logins);

        // Задание 5: Звонкие согласные
        System.out.println("\nЗадание 5: Звонкие согласные");
        System.out.print("Введите текст для поиска звонких согласных: ");
        scanner.nextLine(); // Очистка буфера
        String text = scanner.nextLine();
        Set<Character> consonants = ConsonantFinder.findVoicedConsonants(text);
        System.out.println("Звонкие согласные: " + consonants);

        // Задание 6: Очередь
        System.out.println("\nЗадание 6: Очередь");
        System.out.print("Введите элементы очереди через пробел: ");
        String[] queueInput = scanner.nextLine().split(" ");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : queueInput) {
            queue.add(Integer.parseInt(s));
        }
        System.out.print("Очередь в обратном порядке: ");
        QueueReverser.printReversed(queue);

        // Задание 7: Ломаная линия
        System.out.println("\nЗадание 7: Ломаная линия");
        System.out.print("Введите количество точек: ");
        int pointCount = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
            System.out.print("Введите координаты точки " + (i + 1) + " (x y): ");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points.add(new Point(x, y));
        }
        Polyline polyline = PolylineProcessor.processPoints(points);
        System.out.println("Ломаная линия: " + polyline);

        scanner.close();
    }
}