import java.util.*;
import java.util.stream.*;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Set<Integer> completedTasks = new HashSet<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markCompleted(int id) throws InvalidTaskException {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == id);

        if (!exists) {
            throw new InvalidTaskException("Task ID not found");
        }

        if (completedTasks.contains(id)) {
            throw new InvalidTaskException("Task already completed");
        }

        completedTasks.add(id);
    }

    public void showAll() {
        tasks.forEach(System.out::println);
    }

    public void showCompleted() {
        tasks.stream()
                .filter(t -> completedTasks.contains(t.getId()))
                .forEach(System.out::println);
    }

    public void showPending() {
        tasks.stream()
                .filter(t -> !completedTasks.contains(t.getId()))
                .forEach(System.out::println);
    }

    public void groupByPriority() {
        Map<String, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getPriority));
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
