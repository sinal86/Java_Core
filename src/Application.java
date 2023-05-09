
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* �������������� �������� � ������� ���������*/
interface Student {
    String getName();
    List<Course> getAllCourses();
}
interface Course {}

/* ���������� �������� ������ */
class CourseClass implements Course {
    String title; // ��������

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
        return "������� \'" + name + '\'' + ", ������� �� ����� = " + courses ;
    }
}

public class Application {

    static Set<String> task1(List<StudentClass> students) {
        // ������� 1
        // �������� ����������� ������ Student � ������������ ������ ���������� ������, �� ������� ��������� ��������.
        return students.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x -> x.stream())
                .map(x -> x.toString())
                .collect(Collectors.toSet());
    }

    static List<Student> task2(List<StudentClass> students) {
        // ������� 2
        // �������� �������, ����������� �� ���� ������ Student � ������������ ������ �� ���� ����� �������������� (���������������� ������������ ����������� ������)
        // (���������������� ������������ ����������� ������)
        List<Student> res = students.stream()
                .sorted((x, y) -> (int) (y.getAllCourses().size() - x.getAllCourses().size()))
                .limit(3)
                .collect(Collectors.toList());
        return res;
    }

    static List<Student> task3(List<StudentClass> students, String toFind) {
        // ������� 3
        // �������� �������, ����������� �� ���� ������ Student � ��������� Course, ������������ ������ ���������,
        // ������� �������� ���� ����.
        List<Student> res = students.stream()
                .filter(x -> x.getAllCourses().toString().contains(toFind))
                .collect(Collectors.toList());
        return res;

    }

    public static void main(String[] args) {
        // �������� ������
        List<StudentClass> students = new ArrayList<>();
        students.add(new StudentClass("����", "����1, ����2, ����3, ����4, ����5, ����6"));
        students.add(new StudentClass("����", "����1, ����2, ����3, ����4, ����5"));
        students.add(new StudentClass("�����", "����1, ����2, ����3, ����4, ����5, ����6, ����7"));
        students.add(new StudentClass("���", "����1, ����2, ����3, ����5, ����6"));
        students.add(new StudentClass("����", "����1, ����2, ����3, ����5"));
        students.add(new StudentClass("����", "����2, ����3, ����5"));
        students.add(new StudentClass("������", "����2, ����3, ����5, ����8, ����9, ����10, ����11"));

        System.out.println("-- ��� �������� -------------------------------");
        students.stream().forEach(System.out::println);

        // ������� 1 ����
        System.out.println("-- ������� �1 ---------------------------------");
        System.out.println(task1(students));

        // ������� 2 ����
        System.out.println("-- ������� �2 ---------------------------------");
        task2(students).stream().forEach(System.out::println);

        // ������� 3 ����
        System.out.println("-- ������� �3 ---------------------------------");
        String COURSE_NAME = "����6";
        task3(students, COURSE_NAME).stream().forEach(System.out::println);
    }
}