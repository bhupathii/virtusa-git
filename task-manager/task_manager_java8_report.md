# Java 8 Features Implementation Report: Task Manager

This report details how Java 8 features—specifically **Functional Interfaces, Lambdas, and the Streams API**—have been integrated into the Task Manager application to make the code more concise, expressive, and robust. It includes the exact changes made to the codebase and the full updated source code.

---

## 1. Summary of Changes Made
To demonstrate Java 8 capabilities, the following specific modifications were introduced to the legacy Task Manager codebase:

1. **Importing Functional Interfaces:** Added `import java.util.function.*;` to `TaskService.java`.
2. **`Predicate<T>` (Filtering):** Added `filterTasks` to `TaskService.java` to filter tasks dynamically.
3. **`Function<T, R>` (Mapping/Transformation):** Added `mapTasks` to `TaskService.java` to allow extracting specific fields (like transforming titles).
4. **`Consumer<T>` (Executing Actions):** Added `processTasks` to `TaskService.java` to iterate through tasks and perform a custom action provided by the caller.
5. **`Supplier<T>` (Providing Fallbacks):** Added `getTaskOrDefault` to `TaskService.java` to fetch a task by ID safely, invoking a fallback supplier instead of returning null.
6. **Stream Reduction:** Added `getCombinedTaskTitles` to `TaskService.java` which maps tasks to their titles and uses `reduce()` to join them into a single string.
7. **Main Method Demonstration:** Updated `Main.java` to invoke all the new functional interface methods, explicitly passing lambda expressions and method references (e.g., `service.filterTasks(t -> t.getPriority() == Priority.HIGH)`).

---

## 2. Explanation of Implemented Features

### A. Functional Interfaces
*   **`Predicate<Task>`:** Evaluates a condition. Used in `filterTasks()` to pass dynamic filtering criteria (e.g., `t -> t.getPriority() == Priority.HIGH`).
*   **`Function<Task, R>`:** Transforms an object. Used in `mapTasks()` to convert or extract task properties dynamically (e.g., `t -> t.getTitle().toUpperCase()`).
*   **`Consumer<Task>`:** Executes an action on an object. Used in `processTasks()` to allow custom behaviors per task without returning a result (e.g., printing custom formatting).
*   **`Supplier<Task>`:** Provides an object. Used in `getTaskOrDefault()` inside `Optional.orElseGet()` for lazy evaluation of a backup task if a search yields no result.

### B. Streams API Operations
*   **Filtering:** Used extensively (`tasks.stream().filter(...)`) to restrict datasets seamlessly in `showCompleted`, `showPending`, `getTaskOrDefault`, and `filterTasks`.
*   **Mapping:** Used in `mapTasks` and `getCombinedTaskTitles` to transform a `Stream<Task>` into strings dynamically, cleanly handling transitions between collection types.
*   **Reducing:** Used in `getCombinedTaskTitles` to execute the aggregate operation: `.reduce((t1, t2) -> t1 + ", " + t2)`, safely consolidating string components inside the stream pipeline.

---

## 3. Full Source Code

Below is the complete, updated source code for the fundamental components of the Task Manager.

### `Task.java`
```java
import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private Priority priority;

    public Task(int id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + priority;
    }
}
```

### `TaskService.java`
```java
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
```

### `Main.java`
```java
import java.lang.reflect.Method;

public class Main {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Repository<Task> repo = new Repository<>();
        TaskService service = new TaskService();
        String filename = "tasks.ser";

        System.out.println("--- Loading from File (Serialization) ---");
        service.loadFromFile(filename);

        System.out.println("\n--- Normal Operations (Enums & Annotations) ---");
        try {
            Task t1 = new Task(1, "Learn Java", Priority.HIGH);
            Task t2 = new Task(2, "Build Project", Priority.MEDIUM);
            Task t3 = new Task(3, "Watch Movie", Priority.LOW);

            repo.add(t1);
            repo.add(t2);
            repo.add(t3);

            service.addTask(t1);
            service.addTask(t2);
            service.addTask(t3);

            service.markCompleted(1);
            System.out.println("Tasks added and task 1 completed successfully.");
        } catch (InvalidTaskException e) {
            System.out.println("Error during normal operations: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }

        System.out.println("\n--- Testing Exception Cases ---");

        System.out.println("Test Case 1: Marking a Missing Task ID as completed (ID 99)");
        try {
            service.markCompleted(99);
        } catch (InvalidTaskException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        System.out.println("\n--- Demonstration of Custom Annotation (@LogExecution) ---");
        try {
            Method m = TaskService.class.getMethod("addTask", Task.class);
            if (m.isAnnotationPresent(LogExecution.class)) {
                LogExecution log = m.getAnnotation(LogExecution.class);
                System.out.println("Method addTask() has @LogExecution: " + log.value());
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Demonstration of Built-in Annotation (@Deprecated) ---");
        System.out.println("Calling deprecated showAll() method:");
        service.showAll();

        System.out.println("\n--- Final application state (using new displayTasks() method) ---");
        service.displayTasks();

        System.out.println("\n--- Java 8 Functional Interfaces & Streams Demo ---");

        System.out.println("\n1. Predicate: Filtering tasks with HIGH priority");
        service.filterTasks(t -> t.getPriority() == Priority.HIGH)
               .forEach(System.out::println);

        System.out.println("\n2. Function: Mapping tasks to their uppercase titles");
        service.mapTasks(t -> t.getTitle().toUpperCase())
               .forEach(System.out::println);

        System.out.println("\n3. Consumer: Printing tasks with custom format");
        service.processTasks(t -> System.out.println("🚀 Processing -> [ID: " + t.getId() + "] " + t.getTitle()));

        System.out.println("\n4. Supplier: Fetching non-existent task with default fallback");
        Task fallbackTask = service.getTaskOrDefault(999, () -> new Task(999, "Default Fallback Task", Priority.LOW));
        System.out.println("Result: " + fallbackTask);

        System.out.println("\n5. Streams (Reduce): Combining all titles into a single string");
        System.out.println("Combined Titles: " + service.getCombinedTaskTitles());

        System.out.println("\n--- Saving to File (Serialization) ---");
        service.saveToFile(filename);
    }
}
```
