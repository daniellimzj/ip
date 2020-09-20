package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    public LocalDateTime getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = LocalDateTime.parse(at, formatter);
    }

    public String formatAtForPrint() {
        return at.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatAtForPrint() + ")";
    }

    @Override
    public String printToFile() {
        return "E"+ SEPARATOR + super.printToFile() + SEPARATOR + formatAtForPrint();
    }
}
