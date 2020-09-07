import java.util.Scanner;

public class Duke {


    private static final int MAX_LIST_CAPACITY = 100;

    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";

    private static final String PARAM_EVENT = "event";
    private static final String PARAM_DEADLINE = "deadline";
    private static final String PARAM_TODO = "todo";
    private static final String PARAM_DONE = "done";
    private static final String PARAM_LIST = "list";
    private static final String PARAM_BYE = "bye";


    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_LIST_CAPACITY];
        Printer.printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(PARAM_BYE)) {

            if (line.equals(PARAM_LIST)) {
                Printer.printList(tasks);

            } else if (line.startsWith(PARAM_DONE)) {
                try {
                    int taskNumber = markTaskAsDone(tasks, line);
                    Printer.printDoneTaskMessage(tasks[taskNumber]);
                } catch (NumberFormatException e) {
                    Printer.printDoneErrorMessage();
                } catch (NullPointerException e) {
                    Printer.printTaskNumberErrorMessage();
                }

            } else if (line.startsWith(PARAM_TODO)) {
                try {
                    addToDo(tasks, line);
                    Printer.printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (DukeException e) {
                    Printer.printDescriptionErrorMessage(PARAM_TODO);
                }

            } else if (line.startsWith(PARAM_DEADLINE)) {
                try {
                    addDeadline(tasks, line);
                    Printer.printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (StringIndexOutOfBoundsException e) {
                    Printer.printDescriptionErrorMessage(PARAM_DEADLINE);
                }

            } else if (line.startsWith(PARAM_EVENT)) {
                try {
                    addEvent(tasks, line);
                    Printer.printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (StringIndexOutOfBoundsException e) {
                    Printer.printDescriptionErrorMessage(PARAM_EVENT);
                }

            } else {
                Printer.printErrorMessage();
            }

            line = in.nextLine();
        }
        Printer.printByeMessage();
    }

    private static void addDeadline(Task[] tasks, String line) {

        String deadline = line.substring(line.indexOf(PARAM_DEADLINE) + PARAM_DEADLINE.length());
        int firstIndexOfByCommand = deadline.indexOf(PARAM_BY);
        int lastIndexOfByCommand = firstIndexOfByCommand + PARAM_BY.length();

        String description = deadline.substring(0, firstIndexOfByCommand).trim();
        String by = deadline.substring(lastIndexOfByCommand).trim();
        tasks[Task.getTaskCount()] = new Deadline(description, by);
    }

    private static void addEvent(Task[] tasks, String line) {

        String event = line.substring(line.indexOf(PARAM_EVENT) + PARAM_EVENT.length());
        int firstIndexOfAtCommand = event.indexOf(PARAM_AT);
        int lastIndexOfAtCommand = firstIndexOfAtCommand + PARAM_AT.length();

        String description = event.substring(0, firstIndexOfAtCommand).trim();
        String at = event.substring(lastIndexOfAtCommand).trim();
        tasks[Task.getTaskCount()] = new Event(description, at);
    }

    private static void addToDo(Task[] tasks, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < 2) {
            throw new DukeException();
        }

        String todo = line.substring(line.indexOf(PARAM_TODO) + PARAM_TODO.length()).trim();
        tasks[Task.getTaskCount()] = new ToDo(todo);
    }

    private static int markTaskAsDone(Task[] tasks, String line) {

        int taskNumber = Integer.parseInt(line.substring(PARAM_DONE.length()).trim()) - 1;
        tasks[taskNumber].setIsDone(true);
        return taskNumber;
    }


}
