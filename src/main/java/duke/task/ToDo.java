package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String to be written to files when the ToDo is saved.
     *
     * @return ToDo as a formatted String to be saved to files.
     */
    @Override
    public String printToFile() {
        return "T"+ SEPARATOR + super.printToFile();
    }
}
