import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String border = "==================================================";
        printWelcomeMessage(border);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {

            if (line.equals("list")) {
                printList(tasks, border);

            } else if (line.startsWith("done ")) {
                int taskNumber = markTaskDone(tasks, line);
                printDoneTaskMessage(tasks[taskNumber], border);

            } else if (line.startsWith("todo")) {
                addToDo(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1], border);

            } else if (line.startsWith("deadline")) {
                addDeadline(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1], border);


            } else if (line.startsWith("event")) {
                addEvent(tasks, line);
                printAddTaskMessage(tasks[Task.taskCount - 1], border);

            } else {
                printErrorMessage(border);
            }

            line = in.nextLine();
        }
        printByeMessage(border);
    }

    private static int markTaskDone(Task[] tasks, String line) {
        int taskNumber = Integer.parseInt(line.substring(5)) - 1;
        tasks[taskNumber].setIsDone(true);
        return taskNumber;
    }

    private static void addEvent(Task[] tasks, String line) {
        String event = line.substring(6);
        int indexOfSlash = event.indexOf("/at");
        String description = event.substring(0, indexOfSlash).trim();
        String at = event.substring(indexOfSlash + 4).trim();
        tasks[Task.taskCount] = new Event(description, at);
    }

    private static void addDeadline(Task[] tasks, String line) {
        String deadline = line.substring(9);
        int indexOfSlash = deadline.indexOf("/by");
        String description = deadline.substring(0, indexOfSlash).trim();
        String by = deadline.substring(indexOfSlash + 4).trim();
        tasks[Task.taskCount] = new Deadline(description, by);
    }

    private static void addToDo(Task[] tasks, String line) {
        String todo = line.substring(5);
        tasks[Task.taskCount] = new ToDo(todo);
    }

    private static void printDoneTaskMessage(Task task, String border) {
        System.out.println(border);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(border);
    }

    private static void printErrorMessage(String border) {
        System.out.println(border);
        System.out.println ("Unknown command!");
        System.out.println(border);
    }

    private static void printAddTaskMessage(Task task, String border) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.\n", Task.taskCount);
        System.out.println(border);
    }

    private static void printWelcomeMessage(String border) {
        System.out.println(border);
        System.out.println(" _________________________ ");
        System.out.println("| Hello! I'm Duke         |");
        System.out.println("| What can I do for you?  |");
        System.out.println("| ________________________|");
        System.out.println("|/");
        System.out.println(border);
    }

    private static void printByeMessage(String border) {
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }

    private static void printList(Task[] tasks, String border) {
        System.out.println(border);
        for (int i = 1; i <= Task.taskCount; i++) {
            System.out.print((i) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(border);
    }
}
