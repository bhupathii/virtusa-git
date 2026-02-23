public class Main {
    public static void main(String[] args) {
        Repository<Task> repo = new Repository<>();
        TaskService service = new TaskService();

        System.out.println("--- Normal Operations ---");
        try {
            Task t1 = new Task(1, "Learn Java", "HIGH");
            Task t2 = new Task(2, "Build Project", "MEDIUM");
            Task t3 = new Task(3, "Watch Movie", "LOW");

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

        System.out.println("Test Case 1: Creating task with Invalid Priority ('CRITICAL')");
        try {
            Task invalidTask = new Task(4, "Invalid Task", "CRITICAL");
            service.addTask(invalidTask);
        } catch (InvalidTaskException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        System.out.println("\nTest Case 2: Marking a Missing Task ID as completed (ID 99)");
        try {
            service.markCompleted(99);
        } catch (InvalidTaskException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        System.out.println("\nTest Case 3: Marking an already completed Task as completed again (ID 1)");
        try {
            service.markCompleted(1);
        } catch (InvalidTaskException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        System.out.println("\n--- Final application state ---");
        service.showAll();
    }
}
