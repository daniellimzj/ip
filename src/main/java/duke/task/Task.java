package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2718";
    protected static final String SEPARATOR = "~#~";

    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String printToFile() {
        return getStatusIcon() + SEPARATOR + description;
    }

    public static String getSeparator() {
        return SEPARATOR;
    }

    public static String getTick() {
        return TICK;
    }
}