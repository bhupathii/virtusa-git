import java.time.LocalDate;

public class DeadlineTask extends Task implements Schedulable {
    private static final long serialVersionUID = 1L;
    private LocalDate deadline;

    public DeadlineTask(int id, String title, Priority priority, LocalDate deadline) {
        super(id, title, priority);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline);
    }

    @Override
    public void delay(int days) {
        this.deadline = this.deadline.plusDays(days);
    }

    @Override
    public String toString() {
        return super.toString() + " [Deadline: " + deadline + (isOverdue() ? " (OVERDUE!)" : "") + "]";
    }
}
