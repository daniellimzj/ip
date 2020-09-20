package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Returns the 'at' of the Event in LocalDateTime format.
     * @return 'at' of the Event in LocalDateTime format.
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Sets the 'at' of the Event.
     * @param at 'at' in String format.
     */
    public void setAt(String at) {
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Returns the 'at' in a formatted String.
     * @return 'at' as a formatted String.
     */
    public String formatAtForPrint() {
        return at.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatAtForPrint() + ")";
    }

    /**
     * Returns a String to be written to files when the Event is saved.
     * @return Event as a formatted String to be saved to files.
     */
    @Override
    public String printToFile() {
        return "E"+ SEPARATOR + super.printToFile() + SEPARATOR + formatAtForPrint();
    }
}
