public class Task {
    private int id;
    private String title;
    private String priority;

    public Task(int id, String title, String priority) throws InvalidTaskException {
        if (!priority.equals("HIGH") && !priority.equals("MEDIUM") && !priority.equals("LOW")) {
            throw new InvalidTaskException("Invalid priority");
        }
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

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + priority;
    }
}
