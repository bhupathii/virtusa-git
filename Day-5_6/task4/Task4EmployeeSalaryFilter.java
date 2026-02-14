import java.util.Comparator;
import java.util.Map;

public class Task4EmployeeSalaryFilter {
    public static void main(String[] args) {
        Map<String, Integer> salaries = Map.of(
                "Asha", 72000,
                "Ben", 48000,
                "Chloe", 51000,
                "Dinesh", 50000,
                "Elena", 68000
        );

        salaries.entrySet().stream()
                .filter(e -> e.getValue() > 50000)
                .map(Map.Entry::getKey)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}
