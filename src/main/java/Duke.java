import java.util.Scanner;

public class Duke {

    private static final String border = "==================================================";
    private static final int MAX_LIST_CAPACITY = 100;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_LIST_CAPACITY];
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {

            if (line.equals("list")) {
                printList(tasks);

            } else if (line.startsWith("done ")) {
                int taskNumber = markTaskAsDone(tasks, line);
                printDoneTaskMessage(tasks[taskNumber]);

            } else if (line.startsWith("todo")) {
                addToDo(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1]);

            } else if (line.startsWith("deadline")) {
                addDeadline(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1]);


            } else if (line.startsWith("event")) {
                addEvent(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1]);

            } else {
                printErrorMessage();
            }

            line = in.nextLine();
        }
        printByeMessage();
    }

    private static void addDeadline(Task[] tasks, String line) {

        String deadline = line.substring(line.lastIndexOf("deadline "));
        int firstIndexOfByCommand = deadline.indexOf("/by ");
        int lastIndexOfByCommand = deadline.lastIndexOf("/by ");

        String description = deadline.substring(0, firstIndexOfByCommand).trim();
        String by = deadline.substring(lastIndexOfByCommand + 4).trim();
        tasks[Task.taskCount] = new Deadline(description, by);
    }

    private static void addEvent(Task[] tasks, String line) {

        String event = line.substring(line.lastIndexOf("event "));
        int firstIndexOfAtCommand = event.indexOf("/at ");
        int lastIndexOfAtCommand = event.lastIndexOf("/at ");

        String description = event.substring(0, firstIndexOfAtCommand).trim();
        String at = event.substring(lastIndexOfAtCommand).trim();
        tasks[Task.taskCount] = new Event(description, at);
    }

    private static void addToDo(Task[] tasks, String line) {
        String todo = line.substring(line.lastIndexOf("todo "));
        tasks[Task.taskCount] = new ToDo(todo);
    }

    private static int markTaskAsDone(Task[] tasks, String line) {
        int taskNumber = Integer.parseInt(line.substring(5)) - 1;
        tasks[taskNumber].setIsDone(true);
        return taskNumber;
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.\n", Task.taskCount);
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
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(border);
    }

    private static void printWelcomeMessage() {
        System.out.println(border);
        System.out.println(" _________________________ ");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);
    }
}
