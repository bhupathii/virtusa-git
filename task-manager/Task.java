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

    // Static Nested Class
    public static class TaskBuilder {
        private int id;
        private String title;
        private Priority priority;

        public TaskBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title, this.priority);
        }
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
