import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task2UniqueEmailValidator {
    public static void main(String[] args) {
        List<String> emails = List.of(
                "a@ex.com",
                "b@ex.com",
                "a@ex.com",
                "c@ex.com",
                "b@ex.com",
                "d@ex.com"
        );

        Set<String> unique = new LinkedHashSet<>();
        Set<String> duplicates = new LinkedHashSet<>();

        emails.forEach(email -> {
            if (!unique.add(email)) {
                duplicates.add(email);
            }
        });

        System.out.println(unique.size());
        System.out.println(duplicates);
    }
}
