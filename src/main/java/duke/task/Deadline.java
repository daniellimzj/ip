package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = LocalDateTime.parse(by, formatter);
    }

    public String formatByForPrint() {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatByForPrint() + ")";
    }

    @Override
    public String printToFile() {
        return "D"+ SEPARATOR + super.printToFile() + SEPARATOR + formatByForPrint();
    }
}
