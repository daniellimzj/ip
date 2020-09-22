package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    protected static final String SEPARATOR = "~#~";

    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon to be printed that represents if the Task is done.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? TICK : CROSS);
    }

    /**
     * Sets if the Task is done or not.
     *
     * @param isDone Boolean of whether the Task is done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a String to be written to files when the Task is saved.
     *
     * @return Task in format to be saved to files.
     */
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