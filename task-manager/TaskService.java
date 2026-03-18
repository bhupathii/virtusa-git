import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Set<Integer> completedTasks = new HashSet<>();
    private final Object lock = new Object();
    private boolean newTaskAdded = false;

    // Member Inner Class
    public class TaskLogger {
        public void logTaskCount() {
            System.out.println("TaskLogger [Member Inner Class]: Total tasks = " + tasks.size() +
                    ", Completed tasks = " + completedTasks.size());
        }
    }

    public TaskLogger getTaskLogger() {
        return new TaskLogger();
    }

    @LogExecution("Adding a new task")
    public void addTask(Task task) {
        synchronized (lock) {
            tasks.add(task);
            newTaskAdded = true;
            lock.notifyAll(); // Notify waiting threads (like BackupDaemon)
        }
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

    @LogExecution("Displaying summary using Local Inner Class")
    public void displayTaskSummary() {
        // Local Inner Class
        class TaskSummary {
            void print() {
                long highPriorityCount = tasks.stream().filter(t -> t.getPriority() == Priority.HIGH).count();
                System.out.println("TaskSummary [Local Inner Class]: " + highPriorityCount + " High Priority Tasks.");
            }
        }

        TaskSummary summary = new TaskSummary();
        summary.print();
    }

    @LogExecution("Sorting tasks by title using Anonymous Inner Class")
    public void sortTasksByTitle() {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        // Anonymous Inner Class
        sortedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().compareToIgnoreCase(t2.getTitle());
            }
        });

        System.out.println("Tasks sorted by title [Anonymous Inner Class]:");
        sortedTasks.forEach(System.out::println);
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

    // Threading feature: A method for the daemon to wait on
    public void waitForNewTask() throws InterruptedException {
        synchronized (lock) {
            while (!newTaskAdded) {
                lock.wait(); // Wait until notified
            }
            newTaskAdded = false; // Reset flag after processing
        }
    }

    // Arrays & Strings feature
    @LogExecution("Generating formatted report using StringBuilder & StringBuffer")
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- TASK MANAGER REPORT ---\n");
        sb.append("Total Tasks: ").append(tasks.size()).append("\n");
        sb.append("Completed Tasks: ").append(completedTasks.size()).append("\n");
        
        // Using StringBuffer for thread-safe string manipulation (just for demonstration)
        StringBuffer sbf = new StringBuffer();
        sbf.append("--- PENDING TASKS ---\n");
        tasks.stream()
             .filter(t -> !completedTasks.contains(t.getId()))
             .forEach(t -> sbf.append(t.toString()).append("\n"));
             
        return sb.toString() + sbf.toString();
    }

    // Arrays feature: Returning a 1D Array
    @LogExecution("Getting task titles as 1D array")
    public String[] getTaskTitlesArray() {
        return tasks.stream()
                    .map(Task::getTitle)
                    .toArray(String[]::new);
    }

    // Arrays feature: Returning a 2D Array matrix [id][title_and_priority]
    @LogExecution("Getting tasks as 2D matrix")
    public String[][] getTaskMatrix() {
        String[][] matrix = new String[tasks.size()][2];
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            matrix[i][0] = String.valueOf(t.getId());
            matrix[i][1] = t.getTitle() + " (" + t.getPriority() + ")";
        }
        return matrix;
    }
}
