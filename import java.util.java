import java.util.Scanner;

public class TaskSolver {

    // Задание 1. Методы

    // 1. Дробная часть
    public static double fraction(double x) {
        return x - (int) x;
    }

    // 3. Букву в число
    public static int charToNum(char x) {
        return x - '0';
    }

    public static boolean is2Digits(int x) {
        return x >= 10 && x <= 99;
    }

    // 7. Диапазон
    public static boolean isInRange(int a, int b, int num) {
        return num >= Math.min(a, b) && num <= Math.max(a, b);
    }

    // 9. Равенство
    public static boolean isEqual(int a, int b, int c) {
        return a == b && b == c;
    }

    // Задание 2. Условия

    // 1. Модуль числа
    public static int abs(int x) {
        return x < 0 ? -x : x;
    }

    // 3. Тридцать пять
    public static boolean is35(int x) {
        return (x % 3 == 0 || x % 5 == 0) && !(x % 3 == 0 && x % 5 == 0);
    }

    // 5. Тройной максимум
    public static int max3(int x, int y, int z) {
        int max = x;
        if (y > max) max = y;
        if (z > max) max = z;
        return max;
    }

    // 7. Двойная сумма
    public static int sum2(int x, int y) {
        int sum = x + y;
        return sum >= 10 && sum <= 19 ? 20 : sum;
    }

    // 9. День недели
    public static String day(int x) {
        switch (x) {
            case 1: return "понедельник";
            case 2: return "вторник";
            case 3: return "среда";
            case 4: return "четверг";
            case 5: return "пятница";
            case 6: return "суббота";
            case 7: return "воскресенье";
            default: return "это не день недели";
        }
    }

    // Задание 3. Циклы

    // 1. Числа подряд
    public static String listNums(int x) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= x; i++) {
            result.append(i).append(" ");
        }
        return result.toString().trim();
    }

    // 3. Четные числа
    public static String chet(int x) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= x; i += 2) {
            result.append(i).append(" ");
        }
        return result.toString().trim();
    }

    // 5. Длина числа
    public static int numLen(long x) {
        return String.valueOf(x).length();
    }

    // 7. Квадрат
    public static void square(int x) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 9. Правый треугольник
    public static void rightTriangle(int x) {
        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < x - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // Задание 4. Массивы

    // 1. Поиск первого значения
    public static int findFirst(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // 3. Поиск максимального
    public static int maxAbs(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (Math.abs(num) > Math.abs(max)) {
                max = num;
            }
        }
        return max;
    }

    // 5. Добавление массива в массив
    public static int[] add(int[] arr, int[] ins, int pos) {
        int[] result = new int[arr.length + ins.length];
        System.arraycopy(arr, 0, result, 0, pos);
        System.arraycopy(ins, 0, result, pos, ins.length);
        System.arraycopy(arr, pos, result, pos + ins.length, arr.length - pos);
        return result;
    }

    // 7. Возвратный реверс
    public static int[] reverseBack(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }

    // 9. Все вхождения
    public static int[] findAll(int[] arr, int x) {
        int count = 0;
        for (int num : arr) {
            if (num == x) {
                count++;
            }
        }
        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                result[index++] = i;
            }
        }
        return result;
    }

    // Вспомогательные методы

    private static int[] inputArray(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String[] input = scanner.nextLine().split(" ");
        int[] arr = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задание 1. Методы
        System.out.println("Задание 1. Методы");
        System.out.print("1. Введите число для вычисления дробной части: ");
        double num1 = scanner.nextDouble();
        System.out.println("Дробная часть: " + fraction(num1));

        System.out.print("3. Введите символ для преобразования в число: ");
        char char1 = scanner.next().charAt(0);
        System.out.println("Число: " + charToNum(char1));

        System.out.print("5. Введите число для проверки на двузначность: ");
        int num2 = scanner.nextInt();
        System.out.println("Двузначное: " + is2Digits(num2));

        System.out.print("7. Введите левую границу диапазона: ");
        int a = scanner.nextInt();
        System.out.print("Введите правую границу диапазона: ");
        int b = scanner.nextInt();
        System.out.print("Введите число для проверки: ");
        int num3 = scanner.nextInt();
        System.out.println("В диапазоне: " + isInRange(a, b, num3));

        System.out.print("9. Введите три числа для проверки на равенство: ");
        int num4 = scanner.nextInt();
        int num5 = scanner.nextInt();
        int num6 = scanner.nextInt();
        System.out.println("Равны: " + isEqual(num4, num5, num6));

        // Задание 2. Условия
        System.out.println("\nЗадание 2. Условия");
        System.out.print("1. Введите число для вычисления модуля: ");
        int num7 = scanner.nextInt();
        System.out.println("Модуль: " + abs(num7));

        System.out.print("3. Введите число для проверки на делимость на 3 или 5: ");
        int num8 = scanner.nextInt();
        System.out.println("Результат: " + is35(num8));

        System.out.print("5. Введите три числа для поиска максимума: ");
        int num9 = scanner.nextInt();
        int num10 = scanner.nextInt();
        int num11 = scanner.nextInt();
        System.out.println("Максимум: " + max3(num9, num10, num11));

        System.out.print("7. Введите два числа для вычисления суммы: ");
        int num12 = scanner.nextInt();
        int num13 = scanner.nextInt();
        System.out.println("Сумма: " + sum2(num12, num13));

        System.out.print("9. Введите номер дня недели (1-7): ");
        int dayNum = scanner.nextInt();
        System.out.println("День недели: " + day(dayNum));

        // Задание 3. Циклы
        System.out.println("\nЗадание 3. Циклы");
        System.out.print("1. Введите число для вывода чисел подряд: ");
        int num14 = scanner.nextInt();
        System.out.println("Числа подряд: " + listNums(num14));

        System.out.print("3. Введите число для вывода четных чисел: ");
        int num15 = scanner.nextInt();
        System.out.println("Четные числа: " + chet(num15));

        System.out.print("5. Введите число для вычисления длины: ");
        long num16 = scanner.nextLong();
        System.out.println("Длина числа: " + numLen(num16));

        System.out.print("7. Введите размер квадрата: ");
        int size1 = scanner.nextInt();
        System.out.println("Квадрат:");
        square(size1);

        System.out.print("9. Введите высоту треугольника: ");
        int size2 = scanner.nextInt();
        System.out.println("Правый треугольник:");
        rightTriangle(size2);

        // Задание 4. Массивы
        System.out.println("\nЗадание 4. Массивы");
        scanner.nextLine();
        int[] arr = inputArray(scanner, "Введите массив чисел через пробел: ");
        System.out.print("1. Введите число для поиска в массиве: ");
        int num17 = scanner.nextInt();
        System.out.println("Индекс первого вхождения: " + findFirst(arr, num17));

        System.out.println("3. Наибольшее по модулю значение: " + maxAbs(arr));

        scanner.nextLine();
        int[] ins = inputArray(scanner, "Введите массив для вставки через пробел: ");
        System.out.print("Введите позицию для вставки: ");
        int pos = scanner.nextInt();
        System.out.println("5. Результат вставки:");
        printArray(add(arr, ins, pos));

        System.out.println("7. Реверс массива:");
        printArray(reverseBack(arr));

        System.out.print("9. Введите число для поиска всех вхождений: ");
        int num18 = scanner.nextInt();
        System.out.println("Индексы всех вхождений:");
        printArray(findAll(arr, num18));

        scanner.close();
    }
}