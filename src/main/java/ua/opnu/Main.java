package ua.opnu;

import java.util.*;
import java.util.function.*;

class Student {
    private String name;
    private String group;
    private int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    public boolean hasDebts() {
        for (int mark : marks) {
            if (mark < 60) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + group + ")";
    }
}

public class Main {
    public static void main(String[] args) {

        // ---------- Завдання 1 ----------
        System.out.println("=== Завдання 1: Перевірка простих чисел ===");
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        System.out.println("Прості числа від 1 до 20:");
        for (int i = 1; i <= 20; i++) {
            if (isPrime.test(i)) System.out.print(i + " ");
        }
        System.out.println("\n");


        // ---------- Завдання 2 ----------
        System.out.println("=== Завдання 2: Фільтрація студентів ===");
        Student[] students = {
                new Student("Олександр", "CS-21", new int[]{80, 75, 90}),
                new Student("Марія", "CS-21", new int[]{45, 60, 70}),
                new Student("Іван", "CS-22", new int[]{59, 58, 100}),
                new Student("Олена", "CS-23", new int[]{100, 100, 95})
        };

        Predicate<Student> noDebts = s -> !s.hasDebts();

        Student[] filtered = Arrays.stream(students)
                .filter(noDebts)
                .toArray(Student[]::new);

        System.out.println("Студенти без заборгованостей:");
        for (Student s : filtered) System.out.println(s);
        System.out.println();


        // ---------- Завдання 3 ----------
        System.out.println("=== Завдання 3: Два предикати ===");
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> greaterThan3 = n -> n > 3;

        List<Integer> filteredNums = new ArrayList<>();
        for (Integer n : nums) {
            if (even.and(greaterThan3).test(n)) filteredNums.add(n);
        }
        System.out.println("Числа, які парні і більші за 3: " + filteredNums + "\n");


        // ---------- Завдання 4 ----------
        System.out.println("=== Завдання 4: Consumer для студентів ===");
        Consumer<Student> printStudent = s ->
                System.out.println("Студент: " + s.getName() + " (" + s.getGroup() + ")");
        System.out.println("Список студентів:");
        Arrays.stream(students).forEach(printStudent);
        System.out.println();


        // ---------- Завдання 5 ----------
        System.out.println("=== Завдання 5: Predicate + Consumer ===");
        Predicate<Integer> greaterThan5 = n -> n > 5;
        Consumer<Integer> printIfTrue = n -> System.out.println("Число " + n + " > 5");

        // використовуємо stream і boxed() для конвертації int → Integer
        int[] primitiveNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(primitiveNums)
                .boxed()
                .filter(greaterThan5)
                .forEach(printIfTrue);
        System.out.println();


        // ---------- Завдання 6 ----------
        System.out.println("=== Завдання 6: Function 2^n ===");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] base = {0, 1, 2, 3, 4, 5, 6};
        List<Integer> powers = Arrays.stream(base)
                .boxed()
                .map(powerOfTwo)
                .toList();

        System.out.println("2^n для кожного елемента: " + powers);
        System.out.println();


        // ---------- Завдання 7 ----------
        System.out.println("=== Завдання 7: Function stringify() ===");
        Function<Integer, String> toWord = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п’ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев’ять";
            default -> "невідомо";
        };

        int[] digits = {0,1,2,3,4,5,6,7,8,9};
        List<String> words = Arrays.stream(digits)
                .boxed()
                .map(toWord)
                .toList();

        System.out.println("Перетворення чисел у слова:");
        System.out.println(words);
    }
}

