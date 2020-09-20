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

    public Parser() {
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

    public int getIndexToMarkAsDone(String nextLine) {
        return Integer.parseInt(nextLine.substring(PARAM_DONE.length()).trim()) - 1;
    }

    public int getIndexToDelete(String nextLine) {
        return Integer.parseInt(nextLine.substring(PARAM_DELETE.length()).trim()) - 1;
    }

    public String getToDoParams(String nextLine) throws DukeException {
        String[] input = nextLine.split(" ");
        if (input.length < 2) {
            throw new DukeException();
        }
        return nextLine.substring(nextLine.indexOf(PARAM_TODO) + PARAM_TODO.length()).trim();
    }

    public String[] getDeadlineParams(String nextLine) {
        String[] params = new String[2];
        String deadline = nextLine.substring(PARAM_DEADLINE.length());

        int firstIndexOfByCommand = deadline.indexOf(PARAM_BY);
        int lastIndexOfByCommand = firstIndexOfByCommand + PARAM_BY.length();

        params[0] = deadline.substring(0, firstIndexOfByCommand).trim();
        params[1] = deadline.substring(lastIndexOfByCommand).trim();

        return params;
    }

    public String[] getEventParams(String nextLine) {
        String[] params = new String[2];
        String event = nextLine.substring(PARAM_EVENT.length());

        int firstIndexOfAtCommand = event.indexOf(PARAM_AT);
        int lastIndexOfAtCommand = firstIndexOfAtCommand + PARAM_AT.length();

        params[0] = event.substring(0, firstIndexOfAtCommand).trim();
        params[1] = event.substring(lastIndexOfAtCommand).trim();

        return params;
    }
}
