import java.util.Scanner;

public class Duke {

    private static final String border = "==================================================";
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
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(PARAM_BYE)) {

            if (line.equals(PARAM_LIST)) {
                printList(tasks);

            } else if (line.startsWith(PARAM_DONE)) {
                try {
                    int taskNumber = markTaskAsDone(tasks, line);
                    printDoneTaskMessage(tasks[taskNumber]);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("Oops! Please input a number after done!");
                    System.out.println(border);
                } catch (NullPointerException e) {
                    System.out.println(border);
                    System.out.println("Oops! Please input a task number that exists!");
                    System.out.println(border);
                }

            } else if (line.startsWith(PARAM_TODO)) {
                try {
                    addToDo(tasks, line);
                    printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (DukeException e) {
                    System.out.println(border);
                    System.out.println("Oops! the description of a todo cannot be empty!");
                    System.out.println(border);
                }

            } else if (line.startsWith(PARAM_DEADLINE)) {
                try {
                    addDeadline(tasks, line);
                    printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(border);
                    System.out.println("Oops! the description of a deadline cannot be empty!");
                    System.out.println(border);
                }

            } else if (line.startsWith(PARAM_EVENT)) {
                try {
                    addEvent(tasks, line);
                    printAddTaskMessage(tasks[Task.getTaskCount() - 1]);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(border);
                    System.out.println("Oops! the description of an event cannot be empty!");
                    System.out.println(border);
                }

            } else {
                printErrorMessage();
            }

            line = in.nextLine();
        }
        printByeMessage();
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

    private static void printAddTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.\n", Task.getTaskCount());
        System.out.println(border);
    }

    private static void printByeMessage() {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    private static void printDoneTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(border);
    }

    private static void printErrorMessage() {
        System.out.println(border);
        System.out.println ("Unknown command!");
        System.out.println(border);
    }

    private static void printList(Task[] tasks) {
        System.out.println(border);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(border);
    }

    private static void printWelcomeMessage() {
        System.out.println(border);
        System.out.println(" _________________________");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);
    }
}
