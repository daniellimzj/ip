package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), formatter);
    }

    /**
     * Returns the 'by' of the Deadline in LocalDateTime format.
     *
     * @return 'by' of the Deadline in LocalDateTimeFormat.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets the 'by' of the Deadline.
     *
     * @param by 'by' in String format.
     */
    public void setBy(String by) {
        this.by = LocalDateTime.parse(by.trim(), formatter);
    }

    /**
     * Returns the 'by' in a formatted String.
     *
     * @return 'by' as a formatted String.
     */
    public String formatByForPrint() {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatByForPrint() + ")";
    }

    /**
     * Returns a String to be written to files when the Deadline is saved.
     *
     * @return Deadline as a formatted String to be saved to files.
     */
    @Override
    public String printToFile() {
        return "D"+ SEPARATOR + super.printToFile() + SEPARATOR + formatByForPrint();
    }
}
