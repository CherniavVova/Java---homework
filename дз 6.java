package com.example.storage;

public class Storage<T> {
    private final T value;
    private final T alternative;

    public Storage(T value, T alternative) {
        this.value = value;
        this.alternative = alternative;
    }

    public T getValue() {
        return value != null ? value : alternative;
    }

    public static void main(String[] args) {
        // Пример с числами
        Storage<Integer> intStorage1 = new Storage<>(null, 0);
        System.out.println("Значение из хранилища: " + intStorage1.getValue()); // 0

        Storage<Integer> intStorage2 = new Storage<>(99, -1);
        System.out.println("Значение из хранилища: " + intStorage2.getValue()); // 99

        // Пример со строками
        Storage<String> stringStorage1 = new Storage<>(null, "default");
        System.out.println("Значение из хранилища: " + stringStorage1.getValue()); // default

        Storage<String> stringStorage2 = new Storage<>("hello", "hello world");
        System.out.println("Значение из хранилища: " + stringStorage2.getValue()); // hello
    }
}
package com.example.shapes;

public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

public class Point3D extends Point2D {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }
}

public class Line<T extends Point2D> {
    private T start;
    private T end;

    public Line(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }

    public static void main(String[] args) {
        // Линия в 2D
        Point2D p1 = new Point2D(1, 3);
        Point2D p2 = new Point2D(23, 8);
        Line<Point2D> line2D = new Line<>(p1, p2);
        System.out.println(line2D);

        // Линия в 3D
        Point3D p3 = new Point3D(1, 3, 5);
        Point3D p4 = new Point3D(23, 8, 10);
        Line<Point3D> line3D = new Line<>(p3, p4);
        System.out.println(line3D);
    }
}

public class LineUtils {
    public static <T extends Point2D> void shiftLineX(Line<T> line, int shift) {
        T start = line.getStart();
        start.setX(start.getX() + shift);
    }

    public static void main(String[] args) {
        Point2D p1 = new Point2D(5, 3);
        Point2D p2 = new Point2D(10, 8);
        Line<Point2D> line = new Line<>(p1, p2);

        System.out.println("До сдвига: " + line);
        shiftLineX(line, 10);
        System.out.println("После сдвига: " + line);
    }
}
package com.example.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionUtils {
    public static <T, P> List<P> applyFunction(List<T> list, Function<T, P> function) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример 1: Длина строк
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        List<Integer> lengths = applyFunction(strings, String::length);
        System.out.println("Длины строк: " + lengths);

        // Пример 2: Абсолютные значения чисел
        List<Integer> numbers = List.of(1, -3, 7);
        List<Integer> absNumbers = applyFunction(numbers, Math::abs);
        System.out.println("Абсолютные значения: " + absNumbers);

        // Пример 3: Максимальные значения массивов
        List<int[]> arrays = List.of(new int[]{1, 2, 3}, new int[]{-1, -2, -3}, new int[]{10});
        List<Integer> maxValues = applyFunction(arrays, arr -> {
            int max = Integer.MIN_VALUE;
            for (int num : arr) {
                if (num > max) max = num;
            }
            return max;
        });
        System.out.println("Максимальные значения: " + maxValues);
    }
}

import java.util.function.Predicate;

public class FilterUtils {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример 1: Фильтрация строк
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        List<String> filteredStrings = filter(strings, s -> s.length() >= 3);
        System.out.println("Отфильтрованные строки: " + filteredStrings);

        // Пример 2: Фильтрация чисел
        List<Integer> numbers = List.of(1, -3, 7);
        List<Integer> negativeNumbers = filter(numbers, n -> n < 0);
        System.out.println("Отрицательные числа: " + negativeNumbers);

        // Пример 3: Фильтрация массивов
        List<int[]> arrays = List.of(new int[]{1, 2, 3}, new int[]{-1, -2, -3}, new int[]{10});
        List<int[]> nonPositiveArrays = filter(arrays, arr -> {
            for (int num : arr) {
                if (num > 0) return false;
            }
            return true;
        });
        System.out.println("Массивы без положительных элементов: " + nonPositiveArrays.size());
    }
}
package com.example.functions;

import java.util.function.BinaryOperator;

public class ReduceUtils {
    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> operator) {
        T result = identity;
        for (T item : list) {
            result = operator.apply(result, item);
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример 1: Конкатенация строк
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        String concatenated = reduce(strings, "", String::concat);
        System.out.println("Конкатенация: " + concatenated);

        // Пример 2: Сумма чисел
        List<Integer> numbers = List.of(1, -3, 7);
        int sum = reduce(numbers, 0, Integer::sum);
        System.out.println("Сумма: " + sum);

        // Пример 3: Общее количество элементов в списке списков
        List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6));
        int totalElements = reduce(listOfLists, 0, (acc, list) -> acc + list.size());
        System.out.println("Общее количество элементов: " + totalElements);
    }
}
package com.example.functions;

import java.util.*;
import java.util.function.Supplier;

public class CollectUtils {
    public static <T, P> P collect(List<T> list, Supplier<P> supplier, Function<T, P> accumulator) {
        P result = supplier.get();
        for (T item : list) {
            accumulator.apply(item);
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример 1: Разделение чисел на положительные и отрицательные
        List<Integer> numbers = List.of(1, -3, 7);
        Map<String, List<Integer>> groupedNumbers = collect(numbers, HashMap::new, item -> {
            String key = item >= 0 ? "positive" : "negative";
            groupedNumbers.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
            return groupedNumbers;
        });
        System.out.println("Группировка чисел: " + groupedNumbers);

        // Пример 2: Группировка строк по длине
        List<String> strings = List.of("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> groupedStrings = collect(strings, HashMap::new, item -> {
            groupedStrings.computeIfAbsent(item.length(), k -> new ArrayList<>()).add(item);
            return groupedStrings;
        });
        System.out.println("Группировка строк: " + groupedStrings);

        // Пример 3: Уникальные строки
        List<String> duplicateStrings = List.of("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniqueStrings = collect(duplicateStrings, HashSet::new, item -> {
            uniqueStrings.add(item);
            return uniqueStrings;
        });
        System.out.println("Уникальные строки: " + uniqueStrings);
    }
}