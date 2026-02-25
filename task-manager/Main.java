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
