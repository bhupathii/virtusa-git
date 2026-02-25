import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Set<Integer> completedTasks = new HashSet<>();

    @LogExecution("Adding a new task")
    public void addTask(Task task) {
        tasks.add(task);
    }

    @LogExecution("Marking task as completed")
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

    @Deprecated
    public void showAll() {
        tasks.forEach(System.out::println);
    }

    @LogExecution("Displaying all tasks")
    public void displayTasks() {
        tasks.forEach(System.out::println);
    }

    @LogExecution("Displaying completed tasks")
    public void showCompleted() {
        tasks.stream()
                .filter(t -> completedTasks.contains(t.getId()))
                .forEach(System.out::println);
    }

    @LogExecution("Displaying pending tasks")
    public void showPending() {
        tasks.stream()
                .filter(t -> !completedTasks.contains(t.getId()))
                .forEach(System.out::println);
    }

    @LogExecution("Grouping tasks by priority")
    public void groupByPriority() {
        Map<Priority, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getPriority));
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @LogExecution("Filtering tasks with custom Predicate")
    public List<Task> filterTasks(Predicate<Task> predicate) {
        // Stream pipeline: filtering
        return tasks.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @LogExecution("Mapping tasks using custom Function")
    public <R> List<R> mapTasks(Function<Task, R> function) {
        // Stream pipeline: mapping
        return tasks.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    @LogExecution("Applying an action to all tasks using Consumer")
    public void processTasks(Consumer<Task> consumer) {
        // Utilizing Consumer directly or via stream forEach
        tasks.stream().forEach(consumer); // Demonstrating Stream + Consumer
    }

    @LogExecution("Getting task by ID or providing default using Supplier")
    public Task getTaskOrDefault(int id, Supplier<Task> defaultSupplier) {
        // Stream pipeline: filtering and using Supplier as fallback
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseGet(defaultSupplier);
    }

    @LogExecution("Reducing tasks to a single string")
    public String getCombinedTaskTitles() {
        // Stream pipeline: mapping and reducing
        return tasks.stream()
                .map(Task::getTitle)
                .reduce((t1, t2) -> t1 + ", " + t2)
                .orElse("No tasks available");
    }

    @SuppressWarnings("unchecked")
    @LogExecution("Loading tasks from file")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
            completedTasks = (Set<Integer>) ois.readObject();
            System.out.println("Tasks and completion states loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    @LogExecution("Saving tasks to file")
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
            oos.writeObject(completedTasks);
            System.out.println("Tasks and completion states saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
