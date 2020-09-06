public class Task {

    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2718";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getStatusIcon() {
        return (isDone ? TICK : CROSS);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}