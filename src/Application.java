
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* предварительно заданная в задании структура*/
interface Student {
    String getName();
    List<Course> getAllCourses();
}
interface Course {}

/* реализация объектов данных */
class CourseClass implements Course {
    String title; // название

    public CourseClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}

class StudentClass implements Student {
    private String name;
    private List<Course> courses;

    public StudentClass(String name, String courses) {
        this.name = name;
        this.courses = new ArrayList<Course>();
        for (String i: courses.split(", ")) {
            this.courses.add(new CourseClass(i));
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return "Студент \'" + name + '\'' + ", записан на курсы = " + courses ;
    }
}

public class Application {

    static Set<String> task1(List<StudentClass> students) {
        // Задание 1
        // Функцияя принимающая список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
        return students.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x -> x.stream())
                .map(x -> x.toString())
                .collect(Collectors.toSet());
    }

    static List<Student> task2(List<StudentClass> students) {
        // Задание 2
        // Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных (любознательность определяется количеством курсов)
        // (любознательность определяется количеством курсов)
        List<Student> res = students.stream()
                .sorted((x, y) -> (int) (y.getAllCourses().size() - x.getAllCourses().size()))
                .limit(3)
                .collect(Collectors.toList());
        return res;
    }

    static List<Student> task3(List<StudentClass> students, String toFind) {
        // Задание 3
        // Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
        // которые посещают этот курс.
        List<Student> res = students.stream()
                .filter(x -> x.getAllCourses().toString().contains(toFind))
                .collect(Collectors.toList());
        return res;

    }

    public static void main(String[] args) {
        // тестовые данные
        List<StudentClass> students = new ArrayList<>();
        students.add(new StudentClass("Толя", "курс1, курс2, курс3, курс4, курс5, курс6"));
        students.add(new StudentClass("Вася", "курс1, курс2, курс3, курс4, курс5"));
        students.add(new StudentClass("Света", "курс1, курс2, курс3, курс4, курс5, курс6, курс7"));
        students.add(new StudentClass("Оля", "курс1, курс2, курс3, курс5, курс6"));
        students.add(new StudentClass("Маша", "курс1, курс2, курс3, курс5"));
        students.add(new StudentClass("Лера", "курс2, курс3, курс5"));
        students.add(new StudentClass("Дарина", "курс2, курс3, курс5, курс8, курс9, курс10, курс11"));

        System.out.println("-- Все студенты -------------------------------");
        students.stream().forEach(System.out::println);

        // Задание 1 тест
        System.out.println("-- Задание №1 ---------------------------------");
        System.out.println(task1(students));

        // Задание 2 тест
        System.out.println("-- Задание №2 ---------------------------------");
        task2(students).stream().forEach(System.out::println);

        // Задание 3 тест
        System.out.println("-- Задание №3 ---------------------------------");
        String COURSE_NAME = "курс6";
        task3(students, COURSE_NAME).stream().forEach(System.out::println);
    }
}