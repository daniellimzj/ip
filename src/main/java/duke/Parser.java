package duke;

public class Parser {

    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";
    private static final String PARAM_EVENT = "event";
    private static final String PARAM_DEADLINE = "deadline";
    private static final String PARAM_TODO = "todo";
    private static final String PARAM_DONE = "done";
    private static final String PARAM_LIST = "list";
    private static final String PARAM_BYE = "bye";
    private static final String PARAM_DELETE = "delete";
    private static final String PARAM_FIND = "find";

    public Parser() {
    }

    /**
     * Returns the parameter taken as the command word for adding a type of Task.
     * @param param The type of Task whose parameter is desired.
     * @return Command word for adding a type of Task.
     */
    public static String getParam(String param) {
        switch(param) {
        case ("todo"):
            return PARAM_TODO;
        case ("event"):
            return PARAM_EVENT;
        case ("deadline"):
            return PARAM_DEADLINE;
        default:
            return "command";
        }
    }

    public boolean isListCommand(String nextLine) {
        return nextLine.equals(PARAM_LIST);
    }

    public boolean isByeCommand(String nextLine) {
        return nextLine.equals(PARAM_BYE);
    }

    public boolean isDoneCommand(String nextLine) {
        return nextLine.startsWith(PARAM_DONE);
    }

    public boolean isDeleteCommand(String nextLine) {
        return nextLine.startsWith(PARAM_DELETE);
    }

    public boolean isAddToDoCommand(String nextLine) {
        return nextLine.startsWith(PARAM_TODO);
    }

    public boolean isAddDeadlineCommand(String nextLine) {
        return nextLine.startsWith(PARAM_DEADLINE);
    }

    public boolean isAddEventCommand(String nextLine) {
        return nextLine.startsWith(PARAM_EVENT);
    }

    public boolean isFindCommand(String nextLine) {
        return nextLine.startsWith(PARAM_FIND);
    }

    /**
     * Returns the index at which the desired Task should be marked done.
     * @param nextLine Command inputted by user.
     * @return Index at which desired Task should be marked done.
     */
    public int getIndexToMarkAsDone(String nextLine) {
        return Integer.parseInt(nextLine.substring(PARAM_DONE.length()).trim()) - 1;
    }

    /**
     * Returns the index at which the desired Task should be deleted.
     * @param nextLine Command inputted by user.
     * @return Index at which desired Task should be deleted.
     */
    public int getIndexToDelete(String nextLine) {
        return Integer.parseInt(nextLine.substring(PARAM_DELETE.length()).trim()) - 1;
    }

    /**
     * Returns description of ToDo.
     * @param nextLine Command inputted by user.
     * @return Description of the ToDo as a String.
     * @throws DukeException If no description is provided.
     */
    public String getToDoParams(String nextLine) throws DukeException {
        String[] input = nextLine.split(" ");
        if (input.length < 2) {
            throw new DukeException();
        }
        return nextLine.substring(nextLine.indexOf(PARAM_TODO) + PARAM_TODO.length()).trim();
    }

    /**
     * Returns description and 'by' of Deadline.
     * @param nextLine Command inputted by user.
     * @return Description and 'by' of Deadline as Strings.
     */
    public String[] getDeadlineParams(String nextLine) {
        String[] params = new String[2];
        String deadline = nextLine.substring(PARAM_DEADLINE.length());

        int firstIndexOfByCommand = deadline.indexOf(PARAM_BY);
        int lastIndexOfByCommand = firstIndexOfByCommand + PARAM_BY.length();

        params[0] = deadline.substring(0, firstIndexOfByCommand).trim();
        params[1] = deadline.substring(lastIndexOfByCommand).trim();

        return params;
    }

    /**
     * Returns description and 'at' of Event.
     * @param nextLine Command inputted by user.
     * @return Description and 'at' of Event as Strings.
     */
    public String[] getEventParams(String nextLine) {
        String[] params = new String[2];
        String event = nextLine.substring(PARAM_EVENT.length());

        int firstIndexOfAtCommand = event.indexOf(PARAM_AT);
        int lastIndexOfAtCommand = firstIndexOfAtCommand + PARAM_AT.length();

        params[0] = event.substring(0, firstIndexOfAtCommand).trim();
        params[1] = event.substring(lastIndexOfAtCommand).trim();

        return params;
    }

    /**
     * Returns the keyword used to find Tasks in the TaskList.
     * @param nextLine Command inputted by user.
     * @return Keyword to search for.
     */
    public String getFindParams(String nextLine) {
        return nextLine.substring(PARAM_FIND.length());
    }
}
