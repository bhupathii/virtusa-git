import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task1TopScoringStudents {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Asha", 92),
                new Student("Ben", 85),
                new Student("Chloe", 98),
                new Student("Dinesh", 88),
                new Student("Elena", 91)
        );

        List<String> topNames = students.stream()
                .sorted(Comparator.comparingInt(Student::marks).reversed())
                .limit(3)
                .map(Student::name)
                .collect(Collectors.toList());

        System.out.println(topNames);
    }

    static class Student {
        private final String name;
        private final int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        String name() {
            return name;
        }

        int marks() {
            return marks;
        }
    }
}
