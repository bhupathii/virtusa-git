public class BackupDaemon implements Runnable {
    private final TaskService taskService;
    private volatile boolean running = true;

    public BackupDaemon(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run() {
        System.out.println("[BackupDaemon] Background thread started.");
        try {
            while (running) {
                // Wait for a new task to be added
                taskService.waitForNewTask();
                
                System.out.println("[BackupDaemon] New task detected. Simulating background backup...");
                // Simulate time taken to backup
                Thread.sleep(1000); 
                System.out.println("[BackupDaemon] Backup completed.");
            }
        } catch (InterruptedException e) {
            System.out.println("[BackupDaemon] Thread interrupted. Shutting down.");
            Thread.currentThread().interrupt();
        }
    }

    public void stopDaemon() {
        running = false;
        // In a real scenario, you might want to interrupt the thread here if it's blocked in wait()
        // Thread needs to be managed appropriately
    }
}
